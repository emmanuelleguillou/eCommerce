package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{

	@EJB
	private IProduitDao produitDao;
	
	@Override
	public Produit getProduit(int id_p) {
		return produitDao.getProduit(id_p);
	}

	@Override
	public void deleteProduit(int id_p) {
		produitDao.deleteProduit(id_p);
		
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitDao.updateProduit(p);
	}

	@Override
	public List<Produit> getAllPorduit() {
		return produitDao.getAllPorduit();
	}

	@Override
	public Produit addproduit(Produit p) {
		return produitDao.addproduit(p);
	}

	@Override
	public List<Produit> getAllPorduitByCategorie(int id_c) {
		return produitDao.getAllPorduitByCategorie(id_c);
	}

}
