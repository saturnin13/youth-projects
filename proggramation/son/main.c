#include <stdlib.h>
#include <stdio.h>
#include <SDL.h>
#include <SDL_image.h>
#include <fmod.h>
#include <SDL_ttf.h>

int main(int argc, char *argv[])
{
    SDL_Surface *ecran=NULL, *viseur=NULL, *boite=NULL, *explosion1=NULL, *texte=NULL, *texte2=NULL;
    SDL_Event event;
    SDL_Rect position, positionboite, positiontexte, positionexplosion, positionexplosion2, positiontexte2;
    int continuer=1, i=0, tempsactuel=0, tempsderniertir=0, cliqueencours=0;
    int largeur=640, hauteur=480, tempsboite=0, toucher=0, tempstir=100;
    int touchepourgagner=10000, explosion[23]={0}, tempschangementboite=1000;
    int tempsprecedent=0, compteur=0, tailleboite=100, tempsagrandissement=0;
    int taillemax=120, tempspouragrandir=500, arme=0;
    FMOD_SYSTEM *system;
    FMOD_SOUND *tir;
    FMOD_RESULT resultat;
    TTF_Font *police = NULL;
    char scoreafficher[20]="", nomimage[20]="", temps[20]="0";
    SDL_Color couleurbleu = {0, 0, 255}, couleurnoir = {0, 0, 0};

    SDL_Init(SDL_INIT_VIDEO);
    ecran = SDL_SetVideoMode(largeur, hauteur, 32, SDL_HWSURFACE | SDL_DOUBLEBUF);
    FMOD_System_Create(&system);
    FMOD_System_Init(system, 1, FMOD_INIT_NORMAL, NULL);
    TTF_Init();//initialisation

    resultat=FMOD_System_CreateSound(system,"son1.wav",FMOD_CREATESAMPLE,0,&tir);
    if(resultat!=FMOD_OK)
    {
        fprintf(stderr, "Impossible de lire pan.wav\n");
        exit(EXIT_FAILURE);
    }

    for(i=1;i<23;i++)
    {
        sprintf(nomimage,"explosion%d.png",i);
        explosion[i]=IMG_Load(nomimage);//creation de toutes les explosions
    }
    viseur=IMG_Load("viseur.png");
    boite=SDL_CreateRGBSurface(SDL_HWSURFACE,tailleboite,tailleboite,32,0,0,0,0);
    SDL_FillRect(boite,NULL,SDL_MapRGB(ecran->format,200,0,0));//définition surfaces

    police=TTF_OpenFont("arial.ttf",45);
    sprintf(scoreafficher,"Score: %d",toucher);
    texte=TTF_RenderText_Shaded(police, scoreafficher, couleurbleu, couleurnoir);
    texte2=TTF_RenderText_Shaded(police, temps, couleurbleu, couleurnoir);

    SDL_ShowCursor(SDL_DISABLE);
    srand(time(NULL));

    positionboite.x=ecran->w/2;
    positionboite.y=ecran->h/2;//init position

    positiontexte.x=0;
    positiontexte.y=0;//init position

    positiontexte2.x=0;
    positiontexte2.y=0;//init position

    while(continuer)
    {
        tempsactuel=SDL_GetTicks();
        SDL_PollEvent(&event);
            switch(event.type)
            {
                case SDL_QUIT:
                    continuer=0;
                break;
                case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_ESCAPE:
                        continuer=0;
                    break;

                };
                break;
                case SDL_MOUSEBUTTONDOWN:
                    if(event.button.x>=positionboite.x && event.button.x<=positionboite.x+boite->w && event.button.y>=positionboite.y && event.button.y<=positionboite.y+boite->h)
                    {
                        if(tempsactuel-tempsderniertir>tempstir)//doit etre avant l'autre if pk le tempsdernier est redefini sinon
                        {
                            toucher++;
                            tailleboite-=5;
                            if(tailleboite<20)
                                tailleboite=0;
                            SDL_FreeSurface(boite);
                            boite=SDL_CreateRGBSurface(SDL_HWSURFACE,tailleboite,tailleboite,32,0,0,0,0);
                            SDL_FillRect(boite,NULL,SDL_MapRGB(ecran->format,200,0,0));
                        }
                        if(toucher==touchepourgagner)
                            continuer=0;//code lorsque l'on clique dessus apres "toucher"
                        sprintf(scoreafficher,"score: %d",toucher);
                        SDL_FreeSurface(texte);
                        texte = TTF_RenderText_Shaded(police, scoreafficher, couleurbleu, couleurnoir);//score
                    }
                    if(tempsactuel-tempsderniertir>tempstir)
                    {
                        FMOD_System_PlaySound(system, FMOD_CHANNEL_FREE, tir, 0, NULL);//son tir
                        tempsderniertir=SDL_GetTicks();
                    }
                    cliqueencours=1;
                break;
                case SDL_MOUSEMOTION:

                    if(!(event.button.x>positiontexte.x-viseur->w/2 && event.button.x<positiontexte.x+texte->w+viseur->w/2 && event.button.y>positiontexte.y-viseur->h/2 && event.button.y<positiontexte.y+texte->h+viseur->h/2) && !(event.button.x>positiontexte2.x-viseur->w/2 && event.button.x<positiontexte2.x+texte2->w+viseur->w/2 && event.button.y>positiontexte2.y-viseur->h/2 && event.button.y<positiontexte2.y+texte2->h+viseur->h/2))// ne pas mettre le viseur sur texte
                    {
                        position.x=event.button.x-viseur->w/2;
                        position.y=event.button.y-viseur->h/2;
                        if(tempsactuel-tempsderniertir>tempstir)
                        {
                            positionexplosion.x=event.button.x-59;
                            positionexplosion.y=event.button.y-99;//mettre à jour position
                        }
                    }

                    if(cliqueencours)
                    {
                        if(event.button.x>=positionboite.x && event.button.x<=positionboite.x+boite->w && event.button.y>=positionboite.y && event.button.y<=positionboite.y+boite->h)
                        {
                            if(tempsactuel-tempsderniertir>tempstir)//doit etre avant l'autre if pk le tempsdernier est redefini sinon
                            {
                                toucher++;
                                tailleboite-=7;
                                if(tailleboite<20)
                                    tailleboite=0;
                                SDL_FreeSurface(boite);
                                boite=SDL_CreateRGBSurface(SDL_HWSURFACE,tailleboite,tailleboite,32,0,0,0,0);
                                SDL_FillRect(boite,NULL,SDL_MapRGB(ecran->format,200,0,0));
                            }
                            if(toucher==touchepourgagner)//nmbre de touche
                                continuer=0;//code lorsque l'on clique dessus
                            sprintf(scoreafficher,"Score: %d",toucher);
                            SDL_FreeSurface(texte);
                            texte = TTF_RenderText_Shaded(police, scoreafficher, couleurbleu, couleurnoir);//score
                        }
                        if(tempsactuel-tempsderniertir>tempstir)
                        {
                            FMOD_System_PlaySound(system, FMOD_CHANNEL_FREE, tir, 0, NULL);//son tir
                            tempsderniertir=SDL_GetTicks();
                        }
                    }
                break;
                case SDL_MOUSEBUTTONUP:
                    cliqueencours=0;
                break;
            }

        if (tempsactuel - tempsprecedent >= 100) /* Si 100 ms au moins se sont écoulées */
        {
            compteur += 100; /* On rajoute 100 ms au compteur */
            sprintf(temps, "Temps : %d", compteur); /* On écrit dans la chaîne "temps" le nouveau temps */
            SDL_FreeSurface(texte2); /* On supprime la surface précédente */
            texte2=TTF_RenderText_Shaded(police, temps, couleurbleu, couleurnoir); /* On écrit la chaîne temps dans la SDL_Surface */
            tempsprecedent = tempsactuel; /* On met à jour le tempsPrecedent */
            positiontexte2.x=ecran->w-texte2->w;
        }
        if(tempsactuel-tempsboite>tempschangementboite)//temps changemet boite
        {
            do
            {
                positionboite.x=(rand()%(largeur-boite->w));
                positionboite.y=(rand()%(hauteur-boite->h));//aleatoire position boite
            }
            while((positionboite.x<positiontexte.x+texte->w && positionboite.y<positiontexte.y+texte->h && positionboite.x>positiontexte.x-boite->w && positionboite.y>positiontexte.y-boite->h) || (positionboite.x<positiontexte2.x+texte2->w && positionboite.y<positiontexte2.y+texte2->h && positionboite.x>positiontexte2.x-boite->w && positionboite.y>positiontexte2.y-boite->h));
            tempsboite=SDL_GetTicks();
        }
        if(tempsactuel-tempsagrandissement>tempspouragrandir && !(tailleboite<=0) && !(tailleboite>=taillemax))//temps changemet taille de boite {
        {
            tailleboite+=1;
            SDL_FreeSurface(boite);
            boite=SDL_CreateRGBSurface(SDL_HWSURFACE,tailleboite,tailleboite,32,0,0,0,0);
            SDL_FillRect(boite,NULL,SDL_MapRGB(ecran->format,200,0,0));
            tempsagrandissement=SDL_GetTicks();
        }
        positionexplosion2=positionexplosion;//le blit redéfini la position à zero lorsque l'on lui donne des positions négatives

        SDL_FillRect(ecran,NULL,SDL_MapRGB(ecran->format,0,0,0));
        SDL_BlitSurface(boite,NULL,ecran,&positionboite);
        for(i=1;i<23;i++)
        {
            if(tempsactuel-tempsderniertir>tempstir/23*(i-1) && tempsactuel-tempsderniertir<tempstir/23*i && tempsderniertir!=0)
            {
                SDL_BlitSurface(explosion[i],NULL,ecran,&positionexplosion2);
            }
        }
        SDL_BlitSurface(viseur,NULL,ecran,&position);
        SDL_BlitSurface(texte,NULL,ecran,&positiontexte);
        SDL_BlitSurface(texte2,NULL,ecran,&positiontexte2);
        SDL_Flip(ecran);
    }

    for(i=1;i<23;i++)
    {
        SDL_FreeSurface(explosion[i]);
    }
    SDL_FreeSurface(boite);
    SDL_FreeSurface(viseur);
    SDL_FreeSurface(texte);
    SDL_FreeSurface(texte2);
    SDL_Quit();

    FMOD_Sound_Release(tir);
    FMOD_System_Close(system);
    FMOD_System_Release(system);

    TTF_Quit();

    return EXIT_SUCCESS;
}
