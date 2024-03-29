package projet;

import java.util.List;
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

    public double getPrixTotalFilm(Panier panier){  //méthode pour avoir le prix total des film
        List<Film> selection= new ArrayList<Film>();
        selection=panier.getSelection();
        double prixTotal = 0;                              // mettre les prix en double parce que 2 décimales
        for (Film film : selection) {
            prixTotal += film.getPrix();
        }
        return prixTotal;
    }

    public void payer(Panier panier) {
        Utilisateur user=panier.getUtilisateur();
        boolean abonnement = user.getStatutAbonnement();
        if (abonnement) {
            System.out.println("Paiement de " + getPrixTotalFilm(panier) * 0.95 + " € effectué.");
        } else {
            System.out.println("Paiement de " + getPrixTotalFilm(panier) + " € effectué.");
        }
        panier.viderPanier();
    }
}