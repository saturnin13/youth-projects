#include <string>

#ifndef DEF_jeu
#define DEF_jeu

    class Personnage
    {
        public:

        Personnage();
        Personnage(std::string nom);
        void attaque(Personnage* cible);
        void afficherStat();
        void recevoirDegat(int degat);
        void changerArme(std::string nom,int degat);
        int estVivant();


        private:

        int vie;
        int mana;
        std::string nomArme;
        int degatArme;
        std::string nomPersonnage;
    };

#endif
