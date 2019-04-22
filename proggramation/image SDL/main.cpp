#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

void pause();

int main(int argc, char *argv[])
{
    SDL_Surface *ecran = NULL, *imageDeFond = NULL, *zozor = NULL, *sapin=NULL;
    SDL_Rect positionFond, positionZozor, positionSapin;

    positionFond.x = 0;
    positionFond.y = 0;
    positionZozor.x = 500;
    positionZozor.y = 260;
    positionSapin.x=610;
    positionSapin.y=240;

    SDL_Init(SDL_INIT_VIDEO);

    SDL_WM_SetIcon(SDL_LoadBMP("sdl_icone.bmp"), NULL);

    ecran = SDL_SetVideoMode(800, 600, 32, SDL_HWSURFACE);
    SDL_WM_SetCaption("Chargement d'images en SDL", NULL);

    imageDeFond = SDL_LoadBMP("lac_en_montagne.bmp");
    SDL_BlitSurface(imageDeFond, NULL, ecran, &positionFond);

    zozor = SDL_LoadBMP("zozor.bmp");
    SDL_SetColorKey(zozor, SDL_SRCCOLORKEY, SDL_MapRGB(zozor->format, 0, 0, 255));
    /* Transparence Alpha moyenne (128) : */
    SDL_SetAlpha(zozor, SDL_SRCALPHA, 128);
    SDL_BlitSurface(zozor, NULL, ecran, &positionZozor);

    sapin = IMG_Load("sapin.png");
    SDL_BlitSurface(sapin, NULL, ecran, &positionSapin);

    SDL_Flip(ecran);
    pause();

    SDL_FreeSurface(imageDeFond);
    SDL_FreeSurface(zozor);
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
