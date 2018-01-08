package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;
@Stateful
public class AdministrateurServiceImpl implements IAdministrateurService {

	@EJB
	private IAdministrateurDao administrateurDao;
	
	@Override
	public Administrateur addAdmin(Administrateur a) {
		
		return administrateurDao.addAdmin(a);
	}

}
