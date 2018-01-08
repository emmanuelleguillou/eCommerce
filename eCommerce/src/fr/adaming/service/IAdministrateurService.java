package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
@Local
public interface IAdministrateurService {

	public Administrateur isExist(Administrateur a);
}
