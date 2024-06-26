package projetFilms;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.HashMap;
//import java.util.ArrayList
import java.util.ArrayList;
import java.util.Scanner;

public class Utilisateur {
	private String mail;
	private String motDePasse;
	private String nom;
	private String prenom;
	private String naissance;
	private String adresse;
	private String phraseSecrete;
	private boolean statutAbonnement;
	private int age;
	
	
	private Genre preferences;
	
	
	
	
	private Map<String,Commentaire> listeCommentairesPublies;
	private List<Film> historique;
	



	public Utilisateur(String mail, String motDePasse, String nom, String prenom, String naissance, String adresse, String phraseSecrete, int age) {
		
		this.mail = mail;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
		this.adresse = adresse;
		this.phraseSecrete = phraseSecrete;
		this.statutAbonnement = false;
		this.age = age;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Entrez numero de preference");
		System.out.println("1)animation, 2)horreur, 3)drame, 4)comédie, 5)scienceFiction, 6)action, 7)documentaire, 8)thriller");
		int index = scanner.nextInt();
		while (index -1<0 || index-1>8) {
			System.out.println("Entrez numero de preference");
			index = scanner.nextInt();

		}
        scanner.close();
		this.preferences = Genre.values()[index-1];
		this.historique = new ArrayList<Film>();
		this.listeCommentairesPublies = new HashMap<String,Commentaire>();

	}
	
	public List<Film> getHistorique() {
		return historique;
	}
	
	
	public void setHistorique(List<Film> historique) {
		this.historique = historique;
	}
	public void ajouterFilmHistorique(Film film) {
		this.historique.add(film);
	}
	

	
	
	
	

	
 

	// Getters
    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPhraseSecrete() {
        return phraseSecrete;
    }

    public boolean getStatutAbonnement() {
        return statutAbonnement;
    }

    public int getAge() {
        return age;
    }

    public Genre getPreferences() {
        return preferences;
    }

	public Map<String, Commentaire> getListeCommentairesPublies() {
		return listeCommentairesPublies;
	}
	
    // Setters
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPhraseSecrete(String phraseSecrete) {
        this.phraseSecrete = phraseSecrete;
    }

    public void setStatutAbonnement(boolean statutAbonnement) {
        this.statutAbonnement = statutAbonnement;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPreferences(Genre preferences) {
        this.preferences = preferences;
    }
    public void setListeCommentairesPublies(Map<String, Commentaire> listeCommentairesPublies) {
		this.listeCommentairesPublies = listeCommentairesPublies;
	}

    
    //Méthode abonnement
    
	public void sAbonner() {
		if (this.statutAbonnement == true) {
			System.out.println("Déjà abonné");
		}
		else {
			this.statutAbonnement = true;
			System.out.println("Abonné");
		}
	}
	
	// Methode ajout de commentaires
	public boolean ajouterCommentaire(Commentaire commentaire, String codeFilm) {
		this.listeCommentairesPublies.put(codeFilm, commentaire);
		return true;
	}
    

	public static Genre valeurLaPlusFrequente(List<Genre> liste) {
        // Créer une map pour stocker le nombre d'occurrences de chaque valeur
        Map<Genre, Integer> occurrences = new HashMap<>();
        /*List<Genre> listeGenre = new ArrayList<Genre>();
        for (Film film : liste) {
        	Genre g = film.getGenre();
        	listeGenre.add(g);
        }*/
        // Parcourir la liste et compter les occurrences de chaque valeur
        for (Genre valeur : liste) {
            if (occurrences.containsKey(valeur)) {
                occurrences.put(valeur, occurrences.get(valeur) + 1);
            } else {
                occurrences.put(valeur, 1);
            }
        }

        // Trouver la valeur avec le plus grand nombre d'occurrences
        Genre valeurLaPlusFrequente = null;
        int maxOccurrences = 0;
        for (Map.Entry<Genre, Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() > maxOccurrences) {
                maxOccurrences = entry.getValue();
                valeurLaPlusFrequente = entry.getKey();
            }
        }

        return valeurLaPlusFrequente;
    }
	
	
	public Genre genrePrefere() {
		List<Genre> tousLesGenres = new ArrayList<>();
		for (Film element : this.historique) {
			Genre genreDuFilm = element.getGenre();
			tousLesGenres.add(genreDuFilm);
		}
		
		Genre genreLePlusRegarde = valeurLaPlusFrequente(tousLesGenres);
		return genreLePlusRegarde;	
	}
	
	public static void main(String[] args) {
		
		Utilisateur user1 = new Utilisateur("@gmail.com","mdp123","nom","prenom","naissance","19 rue ","phrase secrete",20);
		System.out.println(user1.getAdresse());
		System.out.println(user1.getPreferences());
		System.out.println(user1.getStatutAbonnement());

		user1.sAbonner();
		System.out.println(user1.getStatutAbonnement());

		
	}
	
}

