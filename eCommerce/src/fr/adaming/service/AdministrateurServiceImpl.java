package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
@Stateful
public class AdministrateurServiceImpl implements IAdministrateurService {

	@EJB
	private IAdministrateurDao administrateurDao;
	

	@Override
	public Administrateur isExist(Administrateur a) {
		return administrateurDao.isExist(a);
				
	}

}
