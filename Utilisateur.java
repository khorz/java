package projetFilms;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.HashMap;
//import java.util.ArrayList
import java.util.ArrayList;


public class Utilisateur {
	private String mail;
	private String motDePasse;
	private String nom;
	private String prenom;
	private Date naissance;
	private String adresse;
	private String phraseSecrete;
	private boolean statutAbonnement;
	private int age;
	private Film[] preferences;
	private Map<String,Commentaire> listeCommentairesPublies;
	private List<Film> historique;
	
	public List<Film> getHistorique() {
		return historique;
	}



	public void setHistorique(List<Film> historique) {
		this.historique = historique;
	}
	public void ajouterFilmHistorique(Film film) {
		this.historique.add(film);
	}
	
	public static Genre valeurLaPlusFrequente(List<Genre> liste) {
        // Créer une map pour stocker le nombre d'occurrences de chaque valeur
        Map<Genre, Integer> occurrences = new HashMap<>();

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
	
	
	public Utilisateur(String mail, String motDePasse, String nom, String prenom, Date naissance, String adresse, String phraseSecrete, boolean statutAbonnement, int age, Film[] preferences, Map<String, Commentaire> listeCommentairesPublies) {
		
		this.mail = mail;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
		this.adresse = adresse;
		this.phraseSecrete = phraseSecrete;
		this.statutAbonnement = statutAbonnement;
		this.age = age;
		this.preferences = preferences;
		this.listeCommentairesPublies = listeCommentairesPublies;
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

    public Date getNaissance() {
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

    public Film[] getPreferences() {
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

    public void setNaissance(Date naissance) {
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

    public void setPreferences(Film[] preferences) {
        this.preferences = preferences;
    }
    public void setListeCommentairesPublies(Map<String, Commentaire> listeCommentairesPublies) {
		this.listeCommentairesPublies = listeCommentairesPublies;
	}

    
    //Méthode abonnement
    
	public void sAbonner(String mailUtilisateur) {
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
    


	
}

