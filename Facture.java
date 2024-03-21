package projetFilms;

import java.util.List; //je sais pas si elles sont nécéssaires mais voila
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Facture {
    private String id;

    public Facture(String id) {
        this.id = id;
    }

    //Getters
    public String getId() {
        return id;
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }
    //méthode 

    public double getPrixTotalFilm(List<Film> selection){  //méthode pour avoir le prix total des film
        double prixTotal = 0;                              // mettre les prix en double parce que 2 décimales
        for (Film film : selection) {
            prixTotal += film.getPrix();
        }
        return prixTotal;
    }

    public void payer(List <Film> selection, boolean abonnement) {
        if (abonnement) {
            System.out.println("Paiement de " + getPrixTotalFilm(selection) * 0.95 + " € effectué.");
        } else {
            System.out.println("Paiement de " + getPrixTotalFilm(selection) + " € effectué.");
        }
    }
}
