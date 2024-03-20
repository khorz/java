package projetFilms;
import java.util.List;
//import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Panier {
	private List<Film> selection;
	//private List<Film> catalogue;
	
	public Panier(List<Film> selection) {
		this.selection = selection;
		//this.catalogue = catalogue;
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
	
	List<Film> catalogue;
	
	
	//int tailleCatalogue = 10;
	
	public void ajouterAuCatalogue(Film film) {
		if (catalogue.contains(film)){
			System.out.println("Deja ajouté");
		}
		else {
			catalogue.add(film);
			System.out.println("Nouveau film disponible");
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
		
	}
	
	public void filtrerCatalogue(int option) {
		
	}
	
	
	public void accesResume(Utilisateur user, Film film) {
		boolean statut = user.getStatutAbonnement();
		String titre = film.getTitre();
		String resume;
		if (statut ==true) {
			resume = film.getResume();
			System.out.println("Résumé de "+titre+" : "+resume);
		}
		else {
			System.out.println("Vous n'êtes pas abonné");
		}
		
	
	}
	
}
