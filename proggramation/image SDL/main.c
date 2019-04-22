#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>

void pause();

int main(int argc, char *argv[])
{
    SDL_Surface *ecran = NULL, *imageDeFond = NULL;
    SDL_Rect positionFond;

    positionFond.x = 0;
    positionFond.y = 0;

    SDL_Init(SDL_INIT_VIDEO);

    ecran = SDL_SetVideoMode(800, 600, 32, SDL_HWSURFACE);
    SDL_WM_SetCaption("Chargement d'images en SDL", NULL);

    /* Chargement d'une image Bitmap dans une surface */
    imageDeFond = SDL_LoadBMP("lac_en_montagne.bmp");
    /* On blitte par-dessus l'écran */
    SDL_BlitSurface(imageDeFond, NULL, ecran, &positionFond);

    SDL_Flip(ecran);
    pause();

    SDL_FreeSurface(imageDeFond); /* On libère la surface */
    SDL_Quit();

    return EXIT_SUCCESS;
}
void pause()
{
    int continuer = 1;
    SDL_Event event;

    while (continuer)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
        }
    }
}
