/*
menu du jeu
*/
#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

#include "constantes.h"
#include "jeu.h"
#include "editeur.h"

int main(int argc, char *argv[])
{
    SDL_Surface *ecran=NULL,*menu=NULL;
    SDL_Rect positionMenu;
    int continuer = 1;
    SDL_Event event;

    positionMenu.x=0;
    positionMenu.y=0;

    SDL_Init(SDL_INIT_VIDEO);

    SDL_WM_SetIcon(IMG_Load("caisse.jpg"), NULL);
    SDL_WM_SetCaption("Mario Sokoban",NULL);

    ecran=SDL_SetVideoMode(LARGEUR_FENETRE,HAUTEUR_FENETRE,32,SDL_HWSURFACE|SDL_DOUBLEBUF);
    menu=IMG_Load("menu.jpg");

    while(continuer)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer=0;
            break;
            case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_KP1:
                        jouer(ecran);
                    break;
                    case SDLK_KP2:
                        editerNiveau(ecran);
                    break;
                    case SDLK_ESCAPE:
                        continuer=0;
                    break;
                };
            break;
        }
    SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 0, 0, 0));
    SDL_BlitSurface(menu,NULL,ecran,&positionMenu);
    SDL_Flip(ecran);
    }
    SDL_FreeSurface(menu);
    SDL_Quit();

    return 0;
}
