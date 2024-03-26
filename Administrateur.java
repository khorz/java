package projetFilms;

import java.time.LocalDate;
import java.util.Set;

public class Administrateur {
  private String id;
  private String mdp;
  private String auteur;
  private LocalDate date;
  private Set<Commentaire> commentaires;
  private float note;

  public Administrateur(String id, String mdp, String auteur, LocalDate date, Set<Commentaire> commentaires, float note) {
    this.id=id;
    this.mdp=mdp;
    this.auteur=auteur;
    this.date=date;
    this.commentaires=commentaires;
    this.note=note;
  }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id=id;
    }
    public String getMdp() {
      return mdp;
    }
    public void setMdp(String mdp) {
      this.mdp=mdp;
    }
    public String getAuteur() {
      return auteur;
    }
    public void setAuteur(String auteur) {
      this.auteur=auteur;
    }
    public LocalDate getDate() {
      return date;
    }
    public void setDate(LocalDate date) {
      this.date=date;
    }
    public Set<Commentaire> getCommentaires() {
      return commentaires;
    }
    public void setCommentaires(Set<Commenatire> commentaires) {
      this.commentaires=commentaires;
    }
    public float getNote() {
      return note;
    }
    public void setNote(float note) {
      this.note=note;
    }
}
