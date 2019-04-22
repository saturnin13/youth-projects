#ifndef DEF_jeu
#define DEF_jeu

    void jouer(SDL_Surface* ecran);
    void deplacerMario(int carte[][NB_BLOC_HAUTEUR],SDL_Rect *pos,int direction);
    void deplacerCaisse(int *caisse1,int *caisse2);

#endif // DEF_jeu
