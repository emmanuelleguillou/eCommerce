package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;


@Local
public interface IAdministrateurDao {
	public Administrateur isExist(Administrateur a);

}

