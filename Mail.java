package projet;

 

import java.util.Date;


public class Main {

  public static void main(String[] args) {
	long nbMilliSecondes = System.currentTimeMillis();
	long moins = 1011478305;
	nbMilliSecondes=nbMilliSecondes-moins;
	Genre genre = Genre.values()[2];
    Date aujourdhui = new Date(nbMilliSecondes); // crée une date au jour et à l'heure d'aujourd'hui
    
    Film film= new Film("azerty","Lady",aujourdhui,"horreur",54,"le film est wooow",4,genre);
    Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom","naissance","19 rue ","phrase secrete",20);
   	user1.ajouterFilmHistorique(film);
    film.addCom(user1);
    
  }
  

}
