package projetFilms;
import java.util.List;
//import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
//import java.util.List;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;




public class Panier {
	

	// Recuperation de la liste des films a partir du fichier JSON
	public static List<Film> lireJSON(String fichierJSON) {
		try {
			JsonReader reader = new JsonReader(new FileReader(fichierJSON));
			// On peut lire un seul objet ou un tableau avec []
			Film[] tabCat = new Gson().fromJson(reader, Film[].class);
			return Arrays.asList(tabCat);
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace() + " : File Not Found");
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
		}
		return null;
	}
	
	private static List<Film> catalogue = lireJSON("fichier");
	private List<Film> selection;
	private List<Film> catalogueAffiche;
	
	public Panier(Utilisateur user) {
		this.selection = new ArrayList<Film>();
		this.catalogue = new ArrayList<Film>();
		this.catalogueAffiche = afficherCatalogue(user);
	}
	
	 
	public List<Film> getSelection() {
		return selection;
	}


	public void setSelection(List<Film> selection) {
		this.selection = selection;
	}


	


	
	// Methodes UML
	
	public void ajouterFilm(List<String> selection, String codeFilm) {
		
		if (selection.contains(codeFilm)) {
			System.out.println("Le film est déjà séléctionné");
		}
		else {
			selection.add(codeFilm);
		}
	}
	
	public void supprimerFilm(List<String> selection, String codeFilm) {
		if (selection.contains(codeFilm)) {
			selection.remove(codeFilm);
		}
		else {
			System.out.println("Le film n'est pas dans la sélection");
		}
	}
	
	public static void trierFilmsParDate(List<Film> listeFilms) {
		
        // Utiliser la méthode sort de la classe Collections avec un Comparator personnalisé
        Collections.sort(listeFilms, new Comparator<Film>() {
            public int compare(Film film1, Film film2) {
                return film1.getDateProd().compareTo(film2.getDateProd());
            }
        });
	}
	
	
	
	
	public static void ajouterAuCatalogue(Film film) {
		try {
			if (catalogue.contains(film)){
				System.out.println("Deja ajouté");
			}
			else {
				catalogue.add(film);
				System.out.println("Nouveau film disponible");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	int tailleCatalogueAffiche = 10;
	
	public List<Film> afficherCatalogue(Utilisateur user){
		
		int pourcentageFilmRecent = tailleCatalogueAffiche*70/100;
		int pourcentageAutre = tailleCatalogueAffiche - pourcentageFilmRecent;
		
		List<Film> catalogueAffiche = new ArrayList<Film>();
		Genre genrePrefere = user.genrePrefere();
		for (Film elt : catalogue) {
			if (!catalogue.contains(elt) && catalogueAffiche.size()<=pourcentageFilmRecent && genrePrefere==elt.getGenre()) {
				catalogueAffiche.add(elt);
			}
		}
		
		trierFilmsParDate(catalogue);
		for (Film elt : catalogue) {
			if (!catalogue.contains(elt) && catalogueAffiche.size()<= pourcentageAutre) {
				catalogueAffiche.add(elt);
			}
		}
		
		
		return catalogueAffiche;
	}
	
	
	public void rafraichirCatalogue() {
		List<Film> tmp = new ArrayList<Film>();
		Film recupJson[] = new Film[100];//constituer la liste depuis le json
		int n = recupJson.length;
		Random rand = new Random();
		for (int i=0;i<catalogue.size();i++) {
            int randomIndex = rand.nextInt(n);
            if (!catalogue.contains(recupJson[randomIndex])) {
            	tmp.add(recupJson[randomIndex]);
            }
		}
		catalogueAffiche = tmp;
	}
	
	public static boolean EcrireJsonDirecte(Film lstCat, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonStr = gson.toJson(lstCat);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
			return false;
		}
		return true;
	}
	
	
	
	public static void accesResume(Utilisateur user, Film film) {
		boolean statut = user.getStatutAbonnement();
		String titre = film.getTitre();
		String resume;
		if (statut == true) {
			resume = film.getResume();
			System.out.println("Résumé de "+titre+" : "+resume);
		}
		else {
			System.out.println("Vous n'êtes pas abonné");
		}
		
	
	}
	
	
	
	


	public static void main(String[] args) {
		
		Film film1 = new Film("code1","titanic",LocalDate.parse("2024-03-26"),"theme1",12,"resume1",10,Genre.values()[0]);
		Film film2 = new Film("code2","nic",LocalDate.parse("2021-03-26"),"theme2",23,"resume2",10,Genre.values()[3]);
		
		ajouterAuCatalogue(film1);
		ajouterAuCatalogue(film2);
		
		for (Film elt : catalogue) {
			System.out.println(elt.getTitre());
		}
		Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom","naissance","19 rue ","phrase secrete",20);
		
		accesResume(user1,film1);
		user1.sAbonner();
		accesResume(user1,film1);
		
	}
	
}
