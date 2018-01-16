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
	public List<LigneCommande> getAllLigneCommandeByIdCommande(long idCommande) {
		// construre la requête
		String req = "SELECT lc FROM LigneCommande AS lc WHERE lc.commande.idCommande=:idC";

		// création du query
		Query query = em.createQuery(req);

		// Spécification des paramètres
		query.setParameter("idC", idCommande);

		// création de la nouvelle liste des lignes commandes
		List<LigneCommande> listeLigneCommande = query.getResultList();

		return listeLigneCommande;
	}

	@Override
	public double calculPrixLigneCommande(LigneCommande lc, Produit p) {
		//System.out.println("lc :" + lc + "\n" + "p : " + p);
		//System.out.println("p.getprix : " + p.getPrix());
		double prixTotal = p.getPrix() * lc.getQuantite();
		//System.out.println("prix :" + prixTotal);
		return prixTotal;
	}

	@Override
	public List<LigneCommande> getAllLignesCommandes() {
		// construre la requête
		String req = "SELECT lc FROM LigneCommande AS lc WHERE lc.commande IS NULL";
		Query query = em.createQuery(req);
		//System.out.println("query : " + query);

		// mettre les parametres
		// query.setParameter("idCommande", null);
		// System.out.println("query : " + query);
		//System.out.println("liste avant evoyer liste : " + query.getResultList());
		// création de la nouvelle liste des lignes commandes
		List<LigneCommande> listeLigneCommande = query.getResultList();
		//System.out.println("liste : " + listeLigneCommande);

		return listeLigneCommande;
	}

}
