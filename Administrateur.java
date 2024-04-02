package com.cytech.data;

import Projet.Facture;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Administrateur {
  private String id;
  private String mdp;
  

  public Administrateur(String id, String mdp, String auteur, LocalDate date, Set<Commentaire> commentaires, float note) {
    this.id=id;
    this.mdp=mdp;
    
  }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id=id;
    }
    public String getMdp() {
      return mdp;
    }
    public void setMdp(String mdp) {
      this.mdp=mdp;
    }



    public double geneStats(Utilisateur user){
      System.out.println("1) récupérer moyenne des film et des commentaires");
      System.out.println("2) Prix moyen de tous vos achats");
      System.out.println("Entrez votre option");
      Scanner sc = new Scanner(System.in);
      int option = sc.nextInt();
      while (option !=2 || option !=1){
        System.out.println("Entrez votre option");
        option = sc.nextInt();
      }
      if(option ==1){
        List<Film> film = Panier.lireJSON("listFilm.json");
        int compteur=0;
        float total =0;
        if (film != null) {
          System.out.println("Liste des films lus à partir du fichier JSON :");
          // Affichage de chaque film
          for (Film films : film) {
            System.out.println(films.getCommentaires());
            float note = films.getMoyenne();
            compteur = compteur + 1 ;
            total = note + total;

          }
        } else {
          System.out.println("Erreur lors de la lecture du fichier JSON.");
        }
        System.out.println(total/compteur + "voici");

        //  récupérer tous les film puis faire une boucle pour calculer leur moyenne

      }
      if (option == 2) {

        List<Film> hist = user.getHistorique();
        Panier panier = new Panier(user);
        panier.setSelection(hist);
        Facture facture = new Facture("id_facture"); // Création d'une instance de Facture
        double prixTot = facture.getPrixTotalFilm(panier); // Appel de la méthode getPrixTotalFilm()
        return prixTot; // Convertit le double en float pour correspondre au type de retour de la méthode

      }
      return 0.0f; // Valeur par défaut si l'option n'est pas valide ou si aucune valeur n'est trouvée
    }
}
