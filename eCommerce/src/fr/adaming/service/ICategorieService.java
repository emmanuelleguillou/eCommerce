package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {
	public Categorie addCategorie(Categorie c);

	public Categorie getCategorie(int id_c);

	public void deleteCategorie(int id_c);

	public Categorie updateCategorie(Categorie c);
	
	public List<Categorie> getAllCategorie();
}
