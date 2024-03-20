package projetFilms;

import java.util.Date;

public class Commentaire {
    private String auteur;
    private Date date;
    private String corps;
    private Film film;

    // Constructeur
    public Commentaire(String auteur, Date date, String corps, Film film) {
        this.auteur = auteur;
        this.date = date;
        this.corps = corps;
        this.film = film;
    }

    // Getters et Setters
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
