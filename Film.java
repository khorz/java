package projet;


//import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


import java.io.*;
import java.util.*;

public class Film {
	private String code;
	private String titre;
	private Date dateProd;
	private String theme;
	private float prix;
	private String resume;
	private float moyenne;
	private Genre genre;
	
	private ArrayList<Commentaire> commentaires;
	private Map<String, Commentaire> comByUser;
	private boolean disable;
	
	public Film(String code,String titre,Date dateProd,String theme,float prix,String resume,float moyenne,Genre genre) {
		
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
		
		

        // Créer un objet Gson
        //Gson gson = new Gson();

        // Convertir l'objet Java en JSON
        //String jsonFilm = gson.toJson(this);

        /*try
		{
			FileWriter fw=new FileWriter("/home/cytech/Info/java/essai.txt");
			BufferedWriter bw= new BufferedWriter(fw);
			bw.write(jsonFilm);
			
			bw.close();
		}
		catch (Exception e)
		{ 
			System.out.println("Erreur "+e);
		}*/
	}

		
		
	
	
	public boolean addCom( Utilisateur user) {// ou faire venir mail comme rm   boolean set
		//si il l'a vu donc look historique
		if(user.getHistorique().contains(this)) {		
			//vérifie si les coms sont pas désactivés
			if(!this.disable) {			
				//vérifie si il a deja mis un com
				if (!this.comByUser.containsKey(user.getMail()) ) {//regarder le get de benjamin
					
					String auteur=user.getMail();
					Scanner myObj=new Scanner(System.in);
					System.out.println("Entrez une note entre 0 et 10");
					int note = myObj.nextInt();
					while ((note < 0) || (note > 10)) {
						System.out.println("Entrez une note entre 0 et 10");
						note = myObj.nextInt();
					}
					System.out.println("Ecrivez votre commentaire");
					String paragraphe = myObj.nextLine();
					// ecrire note et paragraphe
					myObj.close();
					
					Date aujourdhui = new Date();
					Commentaire com = new Commentaire(auteur,aujourdhui,note,paragraphe);
					
					
					this.commentaires.add(com);
					this.comByUser.put(user.getMail(), com);
					
					//pour le mettre dans utilisateur
					user.ajouterCommentaire(com,this.code);
					//calculer moyenne
					float moy = 0;
					 for( Commentaire elt : this.commentaires) {
						 moy=moy+ elt.getNote();
					 }
					 this.moyenne=moy/(this.commentaires.size());
					return true;
				}	
			}
		}
		return false;
	}
	
	
	public boolean rmCom(String mail) {//faire venir le mail du user
		//vérifie si le user a mis un com
		if (this.comByUser.containsKey(mail) ) {
			
			Commentaire com=this.comByUser.get(mail);
			this.commentaires.remove(com);
			this.comByUser.remove(mail);
			//calculer moyenne
			float moy = 0;
			 for( Commentaire elt : this.commentaires) {
				 moy=moy+ elt.getNote();
			 }
			 this.moyenne=moy/(this.commentaires.size());
			return true;
		}	
		return false;
	}
	
	
	public boolean modifCom(String mail,int note,String paragraphe) {
		if (this.comByUser.containsKey(mail) ) {
			Commentaire com = this.comByUser.get(mail);
			com.setNote(note);
			Date aujourdhui = new Date();
			com.setDate_com(aujourdhui);
			com.setParagraphe(paragraphe);
			return true;
		}
		return false;
	}
	
	public void visuComFilm() {
		   for(Commentaire elem: this.commentaires)
	       {
	       	 System.out.println (elem.getAuteur().concat(" \n"));
	       	 System.out.println (elem.getDate_com().toString().concat(" \n"));
	       	 System.out.println (String.valueOf(elem.getNote()).concat(" \n"));
	       	 System.out.println (elem.getParagraphe().concat(" \n"));
	       }
		
	}
	
    public void classerParDate(){
        this.commentaires.sort(new Comparator<Commentaire>() {
            @Override
            public int compare(Commentaire c1, Commentaire c2) {
            	if (c1.getDate_com().compareTo(c2.getDate_com()) == 0) {
            		return c1.getAuteur().compareTo(c2.getAuteur());
            	}
                return c1.getDate_com().compareTo(c2.getDate_com());
            }
        });
    }

    public void classerParNote() {
    	this.commentaires.sort(new Comparator<Commentaire>(){
    		@Override
    		public int compare(Commentaire c1, Commentaire c2) {
    			if (c1.getNote() == c2.getNote() ) {
    				return c1.getAuteur().compareTo(c2.getAuteur());	
    			}
    			if (c1.getNote() > c2.getNote()) {
    				return c1.getNote();
    			}
    			else {
    				return c2.getNote();
    			}
    			
    		}
    	});
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
	
}
