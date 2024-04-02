package com.cytech.testsUnitaires;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdministrateurTest {

    @Test
    public void geneStats1() {

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
        film1.addCom(user1);
        Administrateur administrateur = new Administrateur("iiid","mmdp","aauter",fl,20);
        assertEquals(film1.getMoyenne(),administrateur.geneStats1(user1),0.00);
        
    }
    

    @Test
    public void geneStats2() {
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
        film1.addCom(user1);
        List<Film> histoUser = user1.getHistorique();
        double p = 0;
        for (Film f : histoUser){
            p+= f.getPrix();
        }
        
        Administrateur administrateur = new Administrateur("iiid","mmdp","aauter",fl,20);
        assertEquals(p,administrateur.geneStats2(user1),0.00);

    }
}
