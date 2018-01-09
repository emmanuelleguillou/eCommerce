package fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@PersistenceContext(unitName = "eCommerce")
	EntityManager em;

	// getter et setter
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		em.persist(lc);
		return lc;
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande lc) {
		em.merge(lc);
		return lc;
	}

	@Override
	public void deleteLigneCommande(int idLigneCommande) {
		LigneCommande lcOut = em.find(LigneCommande.class, idLigneCommande);
		em.remove(lcOut);

	}

	@Override
	public LigneCommande getLigneCommande(int idLigneCommande) {
		LigneCommande lcOut = em.find(LigneCommande.class, idLigneCommande);
		return lcOut;

	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByIdCommande(int idCommande) {
		// construre la requête
		String req = "SELECT lc FROM LigneCommande AS lc WHERE idCommande=:idC";

		// création du query
		Query query = em.createQuery(req);

		// Spécification des paramètres
		query.setParameter("idC", idCommande);

		// création de la nouvelle liste des lignes commandes
		List<LigneCommande> listeLigneCommande = query.getResultList();

		return listeLigneCommande;
	}

}
