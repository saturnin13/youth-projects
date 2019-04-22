#include <string>

#ifndef DEF_jeu
#define DEF_jeu

    class Fraction
    {
        public:

        Fraction();
        Fraction(int numerateur);
        Fraction(int numerateur,int denominateur);
        void afficher(std::ostream &flux) const;
        Fraction& operator+=(Fraction const& fraction2);
        Fraction& operator+=(int const& number);
        Fraction& operator*=(Fraction const& fraction);
        void simplifier();

        private:

        int num;
        int den;
    };

    std::ostream &operator<<(std::ostream &flux, Fraction const& fraction);
    int pgcd(int a, int b);
    Fraction operator+(Fraction const& a, Fraction const& b);
    Fraction operator*(Fraction const& a,Fraction const& b);

#endif
