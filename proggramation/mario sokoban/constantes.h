/*
    constante
*/
#ifndef DEF
#define DEF

    #define TAILLE_BLOC      34
    #define NB_BLOC_LARGEUR  12
    #define NB_BLOC_HAUTEUR  12
    #define LARGEUR_FENETRE  TAILLE_BLOC*NB_BLOC_LARGEUR
    #define HAUTEUR_FENETRE  TAILLE_BLOC*NB_BLOC_HAUTEUR

    enum{HAUT,BAS,GAUCHE,DROITE};
    enum{VIDE, MUR, CAISSE, OBJECTIF, MARIO, CAISSE_OK};

#endif // DEF
