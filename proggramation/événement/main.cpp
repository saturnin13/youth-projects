#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

int main(int argc, char *argv[])
{
g    SDL_Surface *ecran = NULL, *zozor = NULL;
    SDL_Rect positionZozor;
    SDL_Event event;
    int continuer = 1;

    SDL_Init(SDL_INIT_VIDEO);

    ecran = SDL_SetVideoMode(640, 480, 32, SDL_HWSURFACE|SDL_DOUBLEBUF|SDL_RESIZABLE);
    SDL_WM_SetCaption("Gestion des événements en SDL", NULL);

    /* Chargement de Zozor */
    zozor = SDL_LoadBMP("zozor.bmp");
    SDL_SetColorKey(zozor, SDL_SRCCOLORKEY, SDL_MapRGB(zozor->format, 0, 0, 255));

    /* On centre Zozor à l'écran */
    positionZozor.x=ecran->w/2-zozor->w/2;
    positionZozor.y=ecran->h/2-zozor->h/2;

    SDL_EnableKeyRepeat(10, 10);
    SDL_ShowCursor(SDL_DISABLE);
    SDL_WarpMouse(ecran->w/2,ecran->h/2);

    while (continuer)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
                break;
            case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_UP: // Flèche haut
                        positionZozor.y-=5;
                        break;
                    case SDLK_DOWN: // Flèche bas
                        positionZozor.y+=5;
                        break;
                    case SDLK_RIGHT: // Flèche droite
                        positionZozor.x+=5;
                        break;
                    case SDLK_LEFT: // Flèche gauche
                        positionZozor.x-=5;
                        break;
                }
                break;
                case SDL_MOUSEBUTTONUP:
                    positionZozor.x = event.button.x;
                    positionZozor.y = event.button.y;
                    break;
                case SDL_MOUSEMOTION:
                    positionZozor.x = event.motion.x;
                    positionZozor.y = event.motion.y;
                    break;
        }

        /* On efface l'écran */
        SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 255, 255, 255));
        /* On place Zozor à sa nouvelle position */
        SDL_BlitSurface(zozor, NULL, ecran, &positionZozor);
        /* On met à jour l'affichage */
        SDL_Flip(ecran);
    }

    SDL_FreeSurface(zozor);
    SDL_Quit();

    return EXIT_SUCCESS;
}
