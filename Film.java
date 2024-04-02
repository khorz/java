package projetFilmJava;//import com.google.gson.Gson;

import java.time.LocalDate;
import java.io.*;
import java.util.*;
import java.util.Comparator;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class Film {
	private String code;
	private String titre;
	private LocalDate dateProd;
	private String theme;
	private float prix;
	private String resume;
	private float moyenne;
	private Genre genre;

	private ArrayList<Commentaire> commentaires;
	private Map<String, Commentaire> comByUser;
	private boolean disable;
	private List<Personne> acteurs;
	private List<Personne> realisation;

	public Film(String code,String titre,LocalDate dateProd,String theme,float prix,String resume,float moyenne,Genre genre,List<Personne> acteurs,List<Personne> realisation) {

		this.code=code;
		this.titre=titre;
		this.dateProd=dateProd;
		this.theme=theme;
		this.prix=prix;
		this.resume=resume;
		this.moyenne=moyenne;
		this.genre=genre;
		this.disable=false;

		this.commentaires= new ArrayList<Commentaire>();
		this.comByUser = new HashMap<String, Commentaire>();
		this.acteurs = acteurs;//ne vont pas changer
		this.realisation = realisation;



		String chemin="listfilm.json";
		// Créer un objet Gson
		ajouterFilmJSON(chemin,this);

	}





	public List<Personne> getActeurs() {
		return acteurs;
	}





	public void setActeurs(List<Personne> acteurs) {
		this.acteurs = acteurs;
	}





	public List<Personne> getRealisation() {
		return realisation;
	}





	public void setRealisation(List<Personne> realisation) {
		this.realisation = realisation;
	}



	/*public static boolean EcrireJsonDirecte(Film film, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonStr = gson.toJson(film);
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
	}*/

	public static List<Film> lireJSON(String fichierJSON) {
		List<Film> films = new ArrayList<>();
		try {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.create();
			FileReader reader = new FileReader(fichierJSON);
			films = gson.fromJson(reader, new TypeToken<List<Film>>() {
			}.getType());
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé : " + e.getMessage());
		} catch (JsonParseException e) {
			System.out.println("Erreur de parsing JSON : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}
		return films;
	}
	public static boolean EcrireJsonDirecte(Film film, String fichierJSON) {
		try {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.setPrettyPrinting()
					.create();

			JsonArray jsonArray;
			File file = new File(fichierJSON);

			// Crée un nouveau tableau JSON vide si le fichier n'existe pas encore
			if (!file.exists()) {
				jsonArray = new JsonArray();
			}
			else {
				// Lecture du contenu existant du fichier JSON
				JsonParser jsonParser = new JsonParser();
				JsonElement jsonElement = jsonParser.parse(new FileReader(fichierJSON));

				// Vérifie si le contenu est un tableau JSON
				if (jsonElement.isJsonArray()) {
					jsonArray = jsonElement.getAsJsonArray();

					//vérifier si le film est déjà dedans

					List<projetFilmJava.Film> films = lireJSON("listfilm.json");
					// Parcourir les éléments du tableau JSON
					for (JsonElement element : jsonArray) {
						// Convertir chaque élément en objet Utilisateur en utilisant Gson
						projetFilmJava.Film film_liste = new Gson().fromJson(element, projetFilmJava.Film.class);
						films.add(film_liste);

					}
					for(Film elt : films){
						if(Objects.equals(elt.getCode(), film.getCode())){
							films.remove(elt);
							films.add(film);
							System.out.println("Le film a ete mis a jour");
							for (JsonElement element : jsonArray) {
								// Convertir chaque élément en objet Utilisateur en utilisant Gson
								projetFilmJava.Film film_liste = new Gson().fromJson(element, projetFilmJava.Film.class);
								films.add(film_liste);
							}
							return true;
						}
					}

				} else {
					// Si ce n'est pas un tableau JSON valide, crée un nouveau tableau JSON vide
					jsonArray = new JsonArray();
				}
			}


			// Ajout du nouveau film au tableau JSON
			jsonArray.add(gson.toJsonTree(film));

			// Écriture du contenu mis à jour dans le fichier JSON
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichierJSON));
			writer.write(gson.toJson(jsonArray));
			writer.close();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problème de fichier : " + e.getMessage());
			return false;
		} catch (JsonIOException e) {
			e.printStackTrace();
			System.out.println("Problème de manipulation JSON : " + e.getMessage());
			return false;
		}
	}

	public static boolean ajouterFilmJSON(String nomFichier, Film nouveauFilm) {
		List<Film> films = lireJSON(nomFichier);

		// Ajouter le nouvel film à la liste
		for (Film f : films){
			if(Objects.equals(nouveauFilm.getCode(), f.getCode())){
				films.remove(f);
				films.add(nouveauFilm);
				//nouveauFilm.visuComFilm();
				Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting().create();
				JsonArray jsonArray = new JsonArray();
				for (Film f2 : films) {
					jsonArray.add(gson.toJsonTree(f2));
				}

				// Écrire le contenu JSON dans le fichier
				try (FileWriter file = new FileWriter(nomFichier)) {
					gson.toJson(jsonArray, file);
					file.flush();
					System.out.println("Film mis à jour");
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

			}
		}
		films.add(nouveauFilm);

		// Convertir la liste mise à jour en JSON
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting().create();
		JsonArray jsonArray = new JsonArray();
		for (Film f : films) {
			jsonArray.add(gson.toJsonTree(f));
		}

		// Écrire le contenu JSON dans le fichier
		try (FileWriter file = new FileWriter(nomFichier)) {
			gson.toJson(jsonArray, file);
			file.flush();
			System.out.println("Film ajouté avec succès.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}




	public boolean addCom( Utilisateur user) {// ou faire venir mail comme rm   boolean set
		//si il l'a vu donc look historique
		if(user.getHistorique().contains(this)) {
			//vérifie si les coms sont pas désactivés
			if(!this.disable) {
				//vérifie si il a deja mis un com
				if (!this.comByUser.containsKey(user.getMail()) ) {

					String auteur=user.getMail();

					Scanner scanner1 = new Scanner(System.in);
					System.out.println("Entrez une note entre 0 et 10");

					int note = scanner1.nextInt();
					while (note < 0 || note > 10) {
						System.out.println("Entrez une note entre 0 et 10");
						note = scanner1.nextInt();
					}

					scanner1 = new Scanner(System.in);
					System.out.println("Ecrivez votre commentaire");
					String paragraphe = scanner1.nextLine();
					// ecrire note et paragraphe


					LocalDate aujourdhui = LocalDate.now();
					//Random rand = new Random(); int day = rand.nextInt(30 - 1 + 1) + 1;
					//permet de tester filtrer par date
					//LocalDate aujourdhui = LocalDate.of(2023,5,day);
					Commentaire com = new Commentaire(auteur,aujourdhui,note,paragraphe);


					this.commentaires.add(com);
					this.comByUser.put(user.getMail(), com);


					//pour le mettre dans utilisateur
					if (user.ajouterCommentaire(com,this.code)) {
						user.ajouterCommentaire(com,this.code);
						System.out.println("Com ajouter");
					}
					else {
						System.out.println("erreur ajout");
						return false;
					}
					//calculer moyenne
					float moy = 0;
					for( Commentaire elt : this.commentaires) {
						moy=moy+ elt.getNote();
					}
					this.moyenne=moy/(this.commentaires.size());
					boolean maj = ajouterFilmJSON("listfilm.json",this);
					return true;
				}
				System.out.println("Vous avez déjà mis un com");
				return false;
			}
			System.out.println("les coms sont désactivés");
			return false;
		}
		System.out.println("vous n'avez pas encore vu le film");
		return false;
	}


	public boolean rmCom(Utilisateur user) {//faire venir le mail du user
		//vérifie si le user a mis un com
		if (this.comByUser.containsKey(user.getMail()) ) {

			Commentaire com=this.comByUser.get(user.getMail());
			this.commentaires.remove(com);
			this.comByUser.remove(user.getMail());
			//calculer moyenne
			float moy = 0;
			for( Commentaire elt : this.commentaires) {
				moy=moy+ elt.getNote();
			}
			this.moyenne=moy/(this.commentaires.size());
			System.out.println("com supprimé");
			return true;
		}
		System.out.println("Vous n'avez pas de com sur ce film");
		return false;
	}


	public boolean modifCom(Utilisateur user) {
		if (this.comByUser.containsKey(user.getMail()) ) {
			Commentaire com = this.comByUser.get(user.getMail());

			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Entrez une note entre 0 et 10");


			int note = scanner1.nextInt();
			while (note < 0 || note > 10) {
				System.out.println("Entrez une note entre 0 et 10");
				note = scanner1.nextInt();
			}

			scanner1 = new Scanner(System.in);
			System.out.println("Ecrivez votre commentaire");
			String paragraphe = scanner1.nextLine();
			// ecrire note et paragraphe
			com.setNote(note);
			LocalDate aujourdhui = LocalDate.now();
			com.setDate_com(aujourdhui);
			com.setParagraphe(paragraphe);
			System.out.println("Com modifié");
			//calculer moyenne
			float moy = 0;
			for( Commentaire elt : this.commentaires) {
				moy=moy+ elt.getNote();
			}
			this.moyenne=moy/(this.commentaires.size());

			return true;
		}
		System.out.println("Vous n'avez pas de com sur ce film");
		return false;
	}

	public void visuComFilm() {
		for(Commentaire elem: this.commentaires)
		{
			System.out.println (elem.getAuteur());
			System.out.println (elem.getDate_com().toString());
			System.out.println (String.valueOf(elem.getNote()));
			System.out.println (elem.getParagraphe().concat("\n"));
		}

	}

	public boolean isDisable() {
		return disable;
	}





	public void setDisable(boolean disable) {
		this.disable = disable;
	}





	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}





	public void setCommentaires(ArrayList<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}



	public void trierParDate() {
		Scanner rev = new Scanner(System.in);
		System.out.println("Pour l'avoir de la plus ancienne à la plus récente tapez 1 sinon 0");
		int reverse = rev.nextInt();
		while (reverse != 0 && reverse != 1) {
			System.out.println("Pour l'avoir de la plus ancienne à la plus récente tapez 1 sinon 0");
			reverse = rev.nextInt();
		}
		if(reverse == 1) {
			this.commentaires.sort(Comparator.comparing(Commentaire::getDate_com));
		}
		else {
			this.commentaires.sort(Comparator.comparing(Commentaire::getDate_com).reversed());
		}

		visuComFilm();
	}



	public void trierParNote() {
		Scanner rev = new Scanner(System.in);
		System.out.println("Pour l'avoir de la plus ancienne à la plus récente tapez 1 sinon 0");
		int reverse = rev.nextInt();
		while (reverse != 0 && reverse != 1) {
			System.out.println("Pour l'avoir de la plus ancienne à la plus récente tapez 1 sinon 0");
			reverse = rev.nextInt();
		}
		if(reverse == 1) {
			this.commentaires.sort(Comparator.comparing(Commentaire::getNote));
		}
		else {
			this.commentaires.sort(Comparator.comparing(Commentaire::getNote).reversed());
		}

		visuComFilm();
	}
	public void visualiserPersonne(List<Personne> personnes) {
		for(Personne elem: personnes)
		{
			System.out.println (elem.getNom());
			System.out.println (elem.getPrenom().concat("\n"));
		}
	}

	public void visuFilm(Utilisateur user) {
		System.out.println( "Titre : " + titre + "\n" +
				"Date de production : " + dateProd + "\n" +
				"Thème : " + theme + "\n" +
				"Genre : " + genre + "\n" +
				"Prix : " + prix + "\n" +
				"Moyenne : " + moyenne + "\n" +
				"Acteurs : " + acteurs + "\n" +
				"Réalisateurs : " + realisation + "\n");

		if(user.getStatutAbonnement()) {
			System.out.println("Resume : "+ this.resume);
		}
		visualiserPersonne(this.acteurs);
		visualiserPersonne(this.realisation);


	}

	public void desactiverCom(boolean choix) {
		this.disable=choix;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public LocalDate getDateProd() {
		return dateProd;
	}
	public void setDateProd(LocalDate dateProd) {
		this.dateProd = dateProd;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}



	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String toString() {
		return "Titre : " + titre + "\n" +
				"Date de production : " + dateProd + "\n" +
				"Thème : " + theme + "\n" +
				"Genre : " + genre + "\n" +
				"Prix : " + prix + "\n" +
				"Résumé : " + resume + "\n" +
				"Moyenne : " + moyenne + "\n" +
				"Acteurs : " + acteurs + "\n" +
				"Réalisateurs : " + realisation + "\n";
		// Vous pouvez ajuster cette méthode pour inclure d'autres détails du film si nécessaire
	}

}
