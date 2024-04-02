package projet.src;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilmTest {



    @Test
    public void addCom() {
        LocalDate fl = LocalDate.of(2023, 5, 1);

        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        List<Personne> acteur = new ArrayList<>();
        Personne personne = new Personne("dicap", "leo");

        Panier panier = new Panier(user1);
        Film film1= new Film("test","Ladffdfsfey",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test","Ladffdfsfey",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        user1.ajouterFilmHisto(film1);
        assertEquals(true,film1.addCom(user1));


    }

    @Test
    public void rmCom() {
        LocalDate fl = LocalDate.of(2023, 5, 1);

        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        List<Personne> acteur = new ArrayList<>();
        Personne personne = new Personne("dicap", "leo");

        Panier panier = new Panier(user1);
        Film film1= new Film("test","Ladffdfsfey",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test","Ladffdfsfey",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        user1.ajouterFilmHisto(film1);
        film1.addCom(user1);
        assertEquals(true,film1.rmCom(user1));
    }

    @Test
    public void modifCom() {
        LocalDate fl = LocalDate.of(2023, 5, 1);

        Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom",fl,"19 rue ","phrase secrete",20);
        List<Personne> acteur = new ArrayList<>();
        Personne personne = new Personne("dicap", "leo");

        Panier panier = new Panier(user1);
        Film film1= new Film("test","Ladffdfsfey",fl,"horreur",54,"le film est wooow",4,Genre.horreur, acteur, acteur);
        Film film2= new Film("test","Ladffdfsfey",fl,"horreur",20,"le film est wooow",4,Genre.horreur, acteur, acteur);
        user1.ajouterFilmHisto(film1);
        film1.addCom(user1);
        assertEquals(true,film1.modifCom(user1));
    }


}
