package projetFilms;
import java.util.Date;


import java.util.List;


public class Film {

	private String code;

	private String titre;

	private Date dateProd;

	private String theme;

	private float prix;

	private String resume;

	private float moyenne;

	private Genre genre;

	private List<Commentaire> commentaires;

	

	public Film() {

		

	}

	

	public void addCom(Commentaire com,Utilisateur user) {

		this.commentaires.add(com);

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

	public Date getDateProd() {

		return dateProd;

	}

	public void setDateProd(Date dateProd) {

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

	public List<Commentaire> getCommentaires() {

		return commentaires;

	}

	public void setCommentaires(List<Commentaire> commentaires) {

		this.commentaires = commentaires;

	}



	public String toStringResume(){
		return "Film [resume=" + resume + "]";
	}
	
}