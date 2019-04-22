/*
jeux
*/
#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

#include "constantes.h"
#include "jeu.h"
#include "fichiers.h"

void jouer(SDL_Surface* ecran)
{
    SDL_Surface *mario[4]={NULL}, *mur=NULL, *objectif=NULL, *marioactuel=NULL, *caisse_ok=NULL, *caisse=NULL;
    SDL_Rect position, positionJoueur;
    SDL_Event event;
    int gagner=0, continuer=0, niveau=0, nombreDeNiveaux=0;
    int carte[NB_BLOC_LARGEUR][NB_BLOC_HAUTEUR]={0};
    int i=0, j=0;
    int ctrlEnCour=0;


    mario[DROITE]=IMG_Load("mario_droite.gif");
    mario[GAUCHE]=IMG_Load("mario_gauche.gif");
    mario[HAUT]=IMG_Load("mario_haut.gif");
    mario[BAS]=IMG_Load("mario_bas.gif");
    mur=IMG_Load("mur.jpg");
    objectif=IMG_Load("objectif.png");
    caisse=IMG_Load("caisse.jpg");
    caisse_ok=IMG_Load("caisse_ok.jpg");
    nombreDeNiveaux=nombreDeLigne();


    while(continuer==0)
    {
        if(niveau>nombreDeNiveaux)
            niveau=0;
        else if(niveau<0)
            niveau=nombreDeNiveaux;
        marioactuel=mario[BAS];
        SDL_FillRect(ecran,NULL,SDL_MapRGB(ecran->format,255,255,255));
        charger(carte,niveau);
        niveau++;


    for(i=0;i<NB_BLOC_HAUTEUR;i++)
        {
            for(j=0;j<NB_BLOC_LARGEUR;j++)
            {
                if(carte[i][j]==MARIO)
                {
                    positionJoueur.x=j;
                    positionJoueur.y=i;
                    carte[i][j]=VIDE;
                    break;
                }
            }
        }
    SDL_EnableKeyRepeat(100, 100);

    while(gagner==0)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                gagner=1;
                continuer=1;
                break;
            case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_ESCAPE:
                        gagner=1;
                        continuer=1;
                        break;
                    case SDLK_UP:
                        marioactuel=mario[HAUT];
                        deplacerMario(carte,&positionJoueur,HAUT);
                        break;
                    case SDLK_DOWN:
                        marioactuel=mario[BAS];
                        deplacerMario(carte,&positionJoueur,BAS);
                        break;
                    case SDLK_RIGHT:
                        if(ctrlEnCour==0)
                        {
                            marioactuel=mario[DROITE];
                            deplacerMario(carte,&positionJoueur,DROITE);
                        }
                        else if(ctrlEnCour==1)
                        {
                            gagner=1;
                        }
                        break;
                    case SDLK_LEFT:
                        if(ctrlEnCour==0)
                        {
                            marioactuel=mario[GAUCHE];
                            deplacerMario(carte,&positionJoueur,GAUCHE);
                        }
                        else if(ctrlEnCour==1)
                        {
                            gagner=1;
                            niveau=niveau-2;
                        }
                        break;
                    case SDLK_LCTRL:
                        ctrlEnCour=1;
                        break;
                    case SDLK_r:
                            gagner=1;
                            niveau=niveau-1;
                        break;
                }
                break;

            case SDL_KEYUP:
                switch(event.key.keysym.sym)
                {
                    case SDLK_LCTRL:
                        ctrlEnCour=0;
                        break;
                };
                break;
            }

        SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 255, 255, 255));
        int objectifRestant=0;

        for(i=0;i<NB_BLOC_HAUTEUR;i++)
        {
            for(j=0;j<NB_BLOC_LARGEUR;j++)
            {
                position.x=j*TAILLE_BLOC;
                position.y=i*TAILLE_BLOC;
                switch(carte[i][j])
                {
                    case MUR:
                        SDL_BlitSurface(mur,NULL,ecran,&position);
                        break;
                    case CAISSE:
                        SDL_BlitSurface(caisse,NULL,ecran,&position);
                        break;
                    case OBJECTIF:
                        SDL_BlitSurface(objectif,NULL,ecran,&position);
                        objectifRestant=1;
                        break;
                    case CAISSE_OK:
                        SDL_BlitSurface(caisse_ok,NULL,ecran,&position);
                        break;
                }
            }
        }
        position.x=positionJoueur.x*TAILLE_BLOC;
        position.y=positionJoueur.y*TAILLE_BLOC;
        SDL_BlitSurface(marioactuel,NULL,ecran,&position);

        SDL_Flip(ecran);
        if(objectifRestant!=1)
        {
            gagner=1;
        }
    }
    gagner=0;
    }
    SDL_EnableKeyRepeat(0, 0);

    SDL_FreeSurface(mur);
    SDL_FreeSurface(caisse);
    SDL_FreeSurface(caisse_ok);
    SDL_FreeSurface(objectif);
    for (i = 0 ; i < 4 ; i++)
        SDL_FreeSurface(mario[i]);
}




void deplacerMario(int carte[][NB_BLOC_HAUTEUR],SDL_Rect *pos,int direction)
{
    switch(direction)
    {
        case HAUT:
            if(pos->y-1<0)
                break;
            if(carte[pos->y-1][pos->x]==MUR)
                break;
            if((carte[pos->y-1][pos->x]==CAISSE||carte[pos->y-1][pos->x]==CAISSE_OK) && (carte[pos->y-2][pos->x]==MUR||carte[pos->y-2][pos->x]==CAISSE||carte[pos->y-2][pos->x]==CAISSE_OK))
                break;
            deplacerCaisse(&carte[pos->y-1][pos->x],&carte[pos->y-2][pos->x]);
            pos->y--;
            break;

        case BAS:
            if(pos->y+2>NB_BLOC_HAUTEUR)
                break;
            if(carte[pos->y+1][pos->x]==MUR)
                break;
            if((carte[pos->y+1][pos->x]==CAISSE||carte[pos->y+1][pos->x]==CAISSE_OK) && (carte[pos->y+2][pos->x]==MUR||carte[pos->y+2][pos->x]==CAISSE||carte[pos->y+2][pos->x]==CAISSE_OK))
                break;
            deplacerCaisse(&carte[pos->y+1][pos->x],&carte[pos->y+2][pos->x]);
            pos->y++;
            break;

        case DROITE:
            if(pos->x+2>NB_BLOC_LARGEUR)
                break;
            if(carte[pos->y][pos->x+1]==MUR)
                break;
            if((carte[pos->y][pos->x+1]==CAISSE||carte[pos->y][pos->x+1]==CAISSE_OK) && (carte[pos->y][pos->x+2]==MUR||carte[pos->y][pos->x+2]==CAISSE||carte[pos->y][pos->x+2]==CAISSE_OK))
                break;
            deplacerCaisse(&carte[pos->y][pos->x+1],&carte[pos->y][pos->x+2]);
            pos->x++;
            break;

        case GAUCHE:
            if(pos->x-1<0)
                break;
            if(carte[pos->y][pos->x-1]==MUR)
                break;
            if((carte[pos->y][pos->x-1]==CAISSE||carte[pos->y][pos->x-1]==CAISSE_OK) && (carte[pos->y][pos->x-2]==MUR||carte[pos->y][pos->x-2]==CAISSE||carte[pos->y][pos->x-2]==CAISSE_OK))
                break;
            deplacerCaisse(&carte[pos->y][pos->x-1],&carte[pos->y][pos->x-2]);
            pos->x--;
            break;
    }
}

void deplacerCaisse(int *caisse1,int *caisse2)
{
    if(*caisse1==CAISSE||*caisse1==CAISSE_OK)
    {
        if(*caisse2==OBJECTIF)
        {
            *caisse2=CAISSE_OK;
        }
        else
        {
            *caisse2=CAISSE;
        }
        if(*caisse1==CAISSE_OK)
        {
            *caisse1=OBJECTIF;
        }
        else
        {
            *caisse1=VIDE;
        }
    }
}
