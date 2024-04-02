package com.cytech.testsUnitaires;

import Projet.*;
import Projet.Panier;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FactureTest {


    @Test
    public void getId() {
        Facture facture = new Facture("AAA1");
        assertEquals("AAA1", facture.getId());
    }

    @Test
    public void getPrixTotalFilm(){

        LocalDate fl = LocalDate.of(2023, 5, 1);

        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        List<Personne> acteur = new ArrayList<>();
        Personne personne = new Personne("dicap", "leo");
        Panier panier = new Panier(user1);
        Film film1= new Film("test","Ladffdfsfey",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test","Ladffdfsfey",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);

        Facture facture = new Facture("AAA1");
        panier.ajouterFilmSelection(film1);
        panier.ajouterFilmSelection(film2);
        assertEquals(film1.getPrix()+film2.getPrix(),(float) facture.getPrixTotalFilm(panier), 0.00);
    }
}
