#ifndef DEF_DUREE
#define DEF_DUREE

class Duree
{
    public:

    Duree(int heures = 0, int minutes = 0, int secondes = 0);
    Duree& operator+=(const Duree &duree2);
    void afficher() const;
    Duree& operator+=(int &duree);

    private:

    int m_heures;
    int m_minutes;
    int m_secondes;
};

Duree operator+(Duree const& a, Duree const& b);
Duree operator+(Duree const& duree, int temps);

#endif
