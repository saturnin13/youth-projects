#include <iostream>
#include "Duree.h"

using namespace std;

int main()
{
    Duree duree1(10, 10, 42), duree2(0, 53, 27);
    Duree resultat;

    duree1.afficher();
    cout << "+" << endl;
    duree2.afficher();

    resultat = duree1 + duree2 + 2000;

    cout << "=" << endl;
    resultat.afficher();

    return 0;
}



