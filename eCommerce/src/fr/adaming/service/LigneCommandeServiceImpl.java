package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;
@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	@EJB
	private ILigneCommandeDao ligneCommandeDao;
	
	
	//getter et setter
	public ILigneCommandeDao getLigneCommandeDao() {
		return ligneCommandeDao;
	}

	public void setLigneCommandeDao(ILigneCommandeDao ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}

	
	//les Méthodes
	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		LigneCommande lcOut = ligneCommandeDao.addLigneCommande(lc);
		return lcOut;
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande lc) {
		LigneCommande lcOut = ligneCommandeDao.updateLigneCommande(lc);
		return lcOut;
	}

	@Override
	public void deleteLigneCommande(int idLigneCommande) {
		ligneCommandeDao.deleteLigneCommande(idLigneCommande);
		
	}

	@Override
	public LigneCommande getLigneCommande(int idLigneCommande) {
		LigneCommande lcOut = ligneCommandeDao.getLigneCommande(idLigneCommande);
		return lcOut;
	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByIdCommande(int idCommande) {
		List<LigneCommande> listeOut = ligneCommandeDao.getAllLigneCommandeByIdCommande(idCommande);
		return listeOut;
	}

}
