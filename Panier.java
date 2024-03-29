package projet;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;

import java.io.FileReader;

import java.io.FileNotFoundException;

import java.util.Arrays;

import com.google.gson.Gson;

import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;





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

	List<Film> catalogue ;
	private List<Film> selection;
	private Utilisateur user;

	public Panier(Utilisateur user) {
		this.user = user;
		this.selection = new ArrayList<Film>();

	}


	public List<Film> getSelection() {
		return selection;
	}


	public void setSelection(List<Film> selection) {
		this.selection = selection;
	}


	public Utilisateur getUtilisateur() {
		return user;
	}




	// Methodes UML

	public void ajouterFilmSelection(Film film) {

		if (this.selection.contains(film)) {
			System.out.println("Le film est déjà séléctionné");
		}
		else {
			selection.add(film);
		}
	}

	public void supprimerFilmSelection(Film film) {
		if (this.selection.contains(film)) {
			this.selection.remove(film);
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


	int tailleCatalogueAffiche = 10;


	List<Film> catalogueAffiche = new ArrayList<Film>();

	public void afficherCatalogue(Utilisateur user){

		this.catalogue = lireJSON("fichier");
		//List <Film> catalogueAffiche = new ArrayList<Film>();

		int pourcentageFilmRecent = tailleCatalogueAffiche*70/100;
		int pourcentageAutre = tailleCatalogueAffiche - pourcentageFilmRecent;

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

		for (Film elt : catalogueAffiche) {
			elt.visuFilm(user);
		}
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


}