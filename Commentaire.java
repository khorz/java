package com.cytech.testsUnitaires;

import java.time.LocalDate;


public class Commentaire {
	private String auteur;
	private String paragraphe;
	private  LocalDate date_com;
	private int note;




	public Commentaire(String auteur,LocalDate date_adj,int note,String paragraphe) {
		this.auteur=auteur;
		this.date_com= date_adj;
		this.note=note;
		this.paragraphe=paragraphe;
	}

	public String getParagraphe() {
		return paragraphe;
	}

	public void setParagraphe(String paragraphe) {
		this.paragraphe = paragraphe;
	}

	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public LocalDate getDate_com() {
		return date_com;
	}
	public void setDate_com(LocalDate date_com) {
		this.date_com = date_com;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
}
