package com.cytech.data;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
	LocalDate fl = LocalDate.of(1997, 6, 10);
    LocalDate fp = LocalDate.of(2020, 9,23);
	Genre genre = Genre.values()[2];
    Utilisateur user1 = new Utilisateur("user3@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
    Utilisateur user2 = new Utilisateur("user4@gmail.com","mdp1234","nom1","prenom1",fl,"20 rue ","dofus !",22);

    List<Personne> acteur = new ArrayList<>();
    Personne personne = new Personne("dicaprio", "leo");
    acteur.add(personne);
    List<Personne> real = new ArrayList<>();
    Personne real1 = new Personne("Scorcese", "Martin");


    List<Personne> acteur2 = new ArrayList<>();
    Personne personne2 = new Personne("Chalamet", "Timothee");
    acteur2.add(personne);
    List<Personne> real2 = new ArrayList<>();
    Personne reali = new Personne("Villeneuve", "Denis");

    Film film= new Film("AAA3","Titanic 2",fl,"drame",11.90F,"Un film d'un bateau", 4.3F,genre, acteur, real);
    Film film2= new Film("AAA4","Dune 2",fp,"scienceFiction",9.99F,"Tres beau sable",4.8F,genre, acteur2, real2);

   //Utilisateur user3 = new Utilisateur("lala@gmail.com","mdp123","nom1","prenom1","naissance1","19 rue ","phrase secrete",22);
      user1.ajouterFilmHistorique(film);
   	user2.ajouterFilmHistorique(film);

    
  }
  

}
