package projet;

import java.time.LocalDate;


public class Main {

  public static void main(String[] args) {
	LocalDate fl = LocalDate.of(2023, 5, 1);
	Genre genre = Genre.values()[2];
  
    
    Film film= new Film("azerty","Lady",fl,"horreur",54,"le film est wooow",4,genre);
    Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom","naissance","19 rue ","phrase secrete",20);
    Utilisateur user2 = new Utilisateur("lolo@gmail.com","mdp123","nom1","prenom1","naissance1","19 rue ","phrase secrete",22);
    //Utilisateur user3 = new Utilisateur("lala@gmail.com","mdp123","nom1","prenom1","naissance1","19 rue ","phrase secrete",22);
   	user1.ajouterFilmHistorique(film);
   	user2.ajouterFilmHistorique(film);
	//user3.ajouterFilmHistorique(film);
    film.addCom(user1);
    film.addCom(user2);
    //film.addCom(user3);
    //film.visuComFilm();
    film.visuFilm(user2);
    //film.trierParNote();
    
  }
  

}