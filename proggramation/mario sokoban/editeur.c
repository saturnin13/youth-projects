#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

#include "constantes.h"
#include "editeur.h"
#include "fichiers.h"

void editerNiveau(SDL_Surface* ecran)
{
    SDL_Surface *mur=NULL, *objectif=NULL, *mario=NULL, *caisse_ok=NULL, *caisse=NULL;
    SDL_Rect position;
    SDL_Event event;
    int objetActuel=MUR;
    int carte[NB_BLOC_LARGEUR][NB_BLOC_HAUTEUR]={0};
    int continuer=0, i=0, j=0;
    int clicGaucheEnCour=0, clicDroitEnCour=0, ctrlEnCour=0;

    mario=IMG_Load("mario_bas.gif");
    mur=IMG_Load("mur.jpg");
    objectif=IMG_Load("objectif.png");
    caisse=IMG_Load("caisse.jpg");
    caisse_ok=IMG_Load("caisse_ok.jpg");

    charger(carte,6);

    while(continuer==0)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer=1;
                break;

            case SDL_MOUSEBUTTONDOWN:
                if(event.button.button==SDL_BUTTON_LEFT)
                {
                    carte[event.button.y/TAILLE_BLOC][event.button.x/TAILLE_BLOC]=objetActuel;
                    clicGaucheEnCour=1;
                }
                else if(event.button.button==SDL_BUTTON_RIGHT)
                {
                    carte[event.button.y/TAILLE_BLOC][event.button.x/TAILLE_BLOC]=VIDE;
                    clicDroitEnCour=1;
                }
                break;

            case SDL_MOUSEMOTION:
                if(clicGaucheEnCour==1)
                    carte[event.button.y/TAILLE_BLOC][event.button.x/TAILLE_BLOC]=objetActuel;
                else if(clicDroitEnCour==1)
                    carte[event.button.y/TAILLE_BLOC][event.button.x/TAILLE_BLOC]=VIDE;
                break;

            case SDL_MOUSEBUTTONUP:
                if(event.button.button==SDL_BUTTON_LEFT)
                {
                    clicGaucheEnCour=0;
                }
                else if(event.button.button==SDL_BUTTON_RIGHT)
                {
                    clicDroitEnCour=0;
                }
                break;

            case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_ESCAPE:
                        continuer=1;
                        break;
                    case SDLK_s:
                        if(ctrlEnCour==1)
                            sauvegarder(carte);
                        break;
                    case SDLK_c:
                        charger(carte,1);
                        break;
                    case SDLK_KP1:
                        objetActuel=MUR;
                        break;
                    case SDLK_KP2:
                        objetActuel=CAISSE;
                        break;
                    case SDLK_KP3:
                        objetActuel=OBJECTIF;
                        break;
                    case SDLK_KP4:
                        objetActuel=MARIO;
                        break;
                    case SDLK_KP5:
                        objetActuel=CAISSE_OK;
                        break;
                    case SDLK_LCTRL:
                        ctrlEnCour=1;
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
                        break;
                    case CAISSE_OK:
                        SDL_BlitSurface(caisse_ok,NULL,ecran,&position);
                        break;
                    case MARIO:
                        SDL_BlitSurface(mario,NULL,ecran,&position);
                        break;
                }
            }
        }
        SDL_Flip(ecran);
    }
    SDL_FreeSurface(mur);
    SDL_FreeSurface(caisse);
    SDL_FreeSurface(objectif);
    SDL_FreeSurface(mario);
}
