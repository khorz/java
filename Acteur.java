package projetFilms;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Acteur {
  private String nom;
  private String prenom;

  public Acteur(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }
  
  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom=nom;
  }
  public String getPrenom() {
     return prenom;
  }
  public void setPrenom(String prenom) {
    this.prenom=prenom;
  }
}
