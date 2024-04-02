package Projet;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PanierTest {



    @Test
    public void getUtilisateur(){
        LocalDate fl = LocalDate.of(2024,03,06);
        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        Panier panier = new Panier(user1);
        assertEquals(user1, panier.getUtilisateur());
    }

    @Test
    public void viderPanier() {
        LocalDate fl = LocalDate.of(2024,03,06);
        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        Panier panier = new Panier(user1);
        List<Personne> acteur = new ArrayList<>();
        Film film1= new Film("test","t1",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test2","t2",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        List<Film> selection = new ArrayList<>();
        selection.add(film1);
        selection.add(film1);

        panier.setSelection(selection);
        assertEquals(null,panier.viderPanier());
    }

    @Test
    public void ajouterFilmSelection() {
        LocalDate fl = LocalDate.of(2024,03,06);
        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        Panier panier = new Panier(user1);
        List<Personne> acteur = new ArrayList<>();
        Film film1= new Film("test","t1",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test2","t2",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        List<Film> selection = new ArrayList<>();
        panier.ajouterFilmSelection(film1);
        panier.ajouterFilmSelection(film1);


        assertTrue(panier.getSelection().contains(film1));
    }

    @Test
    public void supprimerFilmSelection() {
        LocalDate fl = LocalDate.of(2024,03,06);
        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        Panier panier = new Panier(user1);
        List<Personne> acteur = new ArrayList<>();
        Film film1= new Film("test","t1",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test2","t2",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        List<Film> selection = new ArrayList<>();
        panier.ajouterFilmSelection(film1);
        panier.ajouterFilmSelection(film2);
        panier.supprimerFilmSelection(film1);
        assertTrue(panier.getSelection().contains(film2));
        assertFalse(panier.getSelection().contains(film1));


    }

}