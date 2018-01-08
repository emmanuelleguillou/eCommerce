package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;
@Stateful

public class CategorieServiceImpl implements ICategorieService {

	@EJB
	private ICategorieDao categorieDao;
	
	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public Categorie getCategorie(int id_c) {

		return categorieDao.getCategorie(id_c);
	}

	@Override
	public void deleteCategorie(int id_c) {
		categorieDao.deleteCategorie(id_c);
		
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		return categorieDao.updateCategorie(c);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

}
