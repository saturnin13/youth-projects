#include <iostream>
#include <string>
#include "Personnage.h"

using namespace std;


Personnage::Personnage():vie(100), mana(100),nomArme("epee rouillee"),degatArme(10),nomPersonnage("anonyme"){}

Personnage::Personnage(std::string nom):vie(100), mana(100),nomArme("epee rouillee"),degatArme(10),nomPersonnage(nom){}

void Personnage::attaque(Personnage* cible)
{
    (*cible).recevoirDegat(degatArme);
    cout<<(*cible).nomPersonnage<<" a etait attaque par "<<nomPersonnage<<" avec l'arme "<<nomArme<<" et a recu "<<degatArme<<" degats"<<endl<<endl;
}

void Personnage::afficherStat()
{
    cout<<nomPersonnage<<endl<<endl;
    cout<<"Vie: "<<vie<<endl;
    cout<<"Mana: "<<mana<<endl;
    cout<<"Arme: "<<nomArme<<" ("<<degatArme<<")"<<endl<<endl;
}

void Personnage::recevoirDegat(int degat)
{
    vie-=degat;
    if(vie<0)
    {
        vie=0;
    }
}

void Personnage::changerArme(string nom,int degat)
{
    nomArme=nom;
    degatArme=degat;
}

int Personnage::estVivant()
{
    if(vie>0)
    {
        return 1;
    }
    else if(vie==0)
    {
        return 0;
    }
}
