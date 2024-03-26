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
}
