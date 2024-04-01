package projetFilmJava;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;



public class Utilisateur {
	private String mail;
	private String motDePasse;
	private String nom;
	private String prenom;
	private LocalDate naissance;
	private String adresse;
	private String phraseSecrete;
	private boolean statutAbonnement;
	private int age;


	private Genre preferences;


	private Map<String,Commentaire> listeCommentairesPublies;
	private List<Film> historique;


	public Utilisateur(String mail, String motDePasse, String nom, String prenom, LocalDate naissance, String adresse, String phraseSecrete, int age) {

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
        //scanner.close();
		this.preferences = Genre.values()[index-1];
		this.historique = new ArrayList<Film>();
		this.listeCommentairesPublies = new HashMap<String,Commentaire>();
		boolean ajt = ajouterUtilisateurJSON("Utilisateurs.json",this);
		//boolean ajt = EcrireJsonDirecte(this,nomFichier);

	}

	static String nomFichier = "Utilisateurs.json";
	public List<Film> getHistorique() {
		return historique;
	}


	public void setHistorique(List<Film> historique) {
		this.historique = historique;
	}

	public void ajouterFilmHistorique(Film film) {
		this.historique.add(film);
	}


	public static List<Utilisateur> lireTableauJSON(String nomFichier) {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		JsonParser parser = new JsonParser();

		try (FileReader reader = new FileReader(nomFichier)) {
			// Utilisation de JsonParser pour parser le fichier JSON
			JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

			// Parcourir les éléments du tableau JSON
			for (JsonElement element : jsonArray) {
				// Convertir chaque élément en objet Utilisateur en utilisant Gson
				Utilisateur utilisateur = new Gson().fromJson(element, Utilisateur.class);
				utilisateurs.add(utilisateur);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return utilisateurs;
	}

	public static List<Utilisateur> lireJSON(String fichierJSON) {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.create();
			FileReader reader = new FileReader(fichierJSON);
			utilisateurs = gson.fromJson(reader, new TypeToken<List<Utilisateur>>() {}.getType());
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé : " + e.getMessage());
		} catch (JsonParseException e) {
			System.out.println("Erreur de parsing JSON : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}
		return utilisateurs;
	}
	public static boolean ajouterUtilisateurJSON(String nomFichier, Utilisateur nouvelUtilisateur) {
		List<Utilisateur> utilisateurs = lireJSON(nomFichier);

		// Ajouter le nouvel utilisateur à la liste
		for (Utilisateur u : utilisateurs){
			if(Objects.equals(nouvelUtilisateur.getMail(), u.getMail())){
				System.out.println("Deja enregistre");
				return false;
			}
		}
		utilisateurs.add(nouvelUtilisateur);

		// Convertir la liste mise à jour en JSON
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting().create();
		JsonArray jsonArray = new JsonArray();
		for (Utilisateur utilisateur : utilisateurs) {
			jsonArray.add(gson.toJsonTree(utilisateur));
		}

		// Écrire le contenu JSON dans le fichier
		try (FileWriter file = new FileWriter(nomFichier)) {
			gson.toJson(jsonArray, file);
			file.flush();
			System.out.println("Utilisateur ajouté avec succès.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean EcrireJsonDirecte(Utilisateur user, String fichierJSON) {
		List<Utilisateur> utilisateurs = lireTableauJSON(nomFichier);

		// Ajouter le nouvel utilisateur à la liste
		for (Utilisateur u : utilisateurs){
			if(Objects.equals(user.getMail(), u.getMail())){
				System.out.println("Deja enregistre");
				return false;
			}
		}
		//utilisateurs.add(user);
		try {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.setPrettyPrinting()
					.create();
			String jsonStr = gson.toJson(user);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON,true));
			bw.write(jsonStr);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problème de fichier : " + e.getMessage());
			return false;
		}
		return true;
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

    public LocalDate getNaissance() {
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

    public void setNaissance(LocalDate naissance) {
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
		if (this.statutAbonnement) {
			System.out.println("Déjà abonné");
		} else {
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

	public void ajouterFilmHisto(Film film) {
		historique.add(film);
	}

    public void trierParDate() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Entrez numero de preference pour le tri");
		System.out.println("1)Du plus vieux au plus récent, 2)Du plus récent au plus vieux");
		int index = scanner.nextInt();
		while (index !=2 && index!=1 ) {
			System.out.println("Entrez numero de preference");
			index = scanner.nextInt();

		}
        //scanner.close();
        if (index == 1) {
			this.historique.sort(Comparator.comparing(Film::getDateProd));

        }
        else {
			this.historique.sort(Comparator.comparing(Film::getDateProd).reversed());

        }

	}

	public void visuInfos() {
		System.out.println("Mail : "+this.mail);
		System.out.println("Nom : " +this.nom);
		System.out.println("Prenom : "+this.prenom);
		System.out.println("Age : "+this.age);

		System.out.println("Statut de l'abonnement : "+this.statutAbonnement);

	}


}
