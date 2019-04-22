#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>
#include <SDL_ttf.h>

void pause();

int main(int argc, char *argv[])
{
    SDL_Surface *ecran = NULL, *texte = NULL, *fond = NULL;
    SDL_Rect position;
    SDL_Event event;
    TTF_Font *police = NULL;
    SDL_Color couleurNoire = {0, 0, 0};
    int continuer = 1;

    SDL_Init(SDL_INIT_VIDEO);
    TTF_Init();

    ecran = SDL_SetVideoMode(640, 480, 32, SDL_HWSURFACE | SDL_DOUBLEBUF);
    SDL_WM_SetCaption("Gestion du texte avec SDL_ttf", NULL);

    fond = IMG_Load("lac_en_montagne.bmp");

    /* Chargement de la police */
    police = TTF_OpenFont("arial.ttf", 65);
    /* Le texte sera écrit en italique et souligné */
    TTF_SetFontStyle(police, TTF_STYLE_ITALIC | TTF_STYLE_UNDERLINE);
    /* Écriture du texte en italique et souligné */
    /* Écriture du texte dans la SDL_Surface texte en mode Blended (optimal) */
    texte = TTF_RenderText_Blended(police, "avé moi !", couleurNoire);

    while (continuer)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
                break;
        }

        SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 255, 255, 255));

        position.x = 0;
        position.y = 0;
        SDL_BlitSurface(fond, NULL, ecran, &position); /* Blit du fond */

        position.x = 0;
        position.y = 350;
        SDL_BlitSurface(texte, NULL, ecran, &position); /* Blit du texte */
        SDL_Flip(ecran);
    }

    TTF_CloseFont(police);
    TTF_Quit();

    SDL_FreeSurface(texte);
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
