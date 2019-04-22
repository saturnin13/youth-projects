#include <iostream>
#include "Personnage.h"
using namespace std;

int main()
{
   Personnage auguste("Auguste"),saturnin("Saturnin");
   auguste.attaque(&saturnin);
   saturnin.afficherStat();
   saturnin.changerArme("demolisseur",50);
   saturnin.attaque(&auguste);
   auguste.afficherStat();
   if(auguste.estVivant()==1)
   {
       cout<<"il vit encore"<<endl<<endl;
   }
   saturnin.attaque(&auguste);
   auguste.afficherStat();
   if(auguste.estVivant()==0)
   {
       cout<<"il est mort..."<<endl<<endl;
   }

    return 0;
}
