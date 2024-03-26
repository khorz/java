package projetFilms;

public abstract class Realisation {
  private String titre;
  private Date dateProd;
  private String resume;
  private double prix;
  private double moyenne;
  private Genre genre;

  public Realisation(String titre, Date dateProd, String resume, double prix, double moyenne, Genre genre) {
    this.titre = titre;
    this.dateProd = dateProd;
    this.resume = resume;
    this.prix = prix;
    this.moyenne = moyenne;
    this.genre = genre;
  }
}
