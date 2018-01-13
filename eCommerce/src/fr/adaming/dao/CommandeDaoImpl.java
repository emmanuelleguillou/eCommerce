package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;


@Stateless
public class CommandeDaoImpl implements ICommandeDao {

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
	public Commande addCommande(Commande c) {
		em.persist(c);
		return c;
	}

	@Override
	public Commande updateCommande(Commande c) {
		em.merge(c);
		return c;
	}

	@Override
	public void deleteCommande(long idCommande) {
		Commande cOut = em.find(Commande.class, idCommande);
		em.remove(cOut);

	}

	@Override
	public Commande getCommandeByIdClNULL(long idCl) {
		// construre la requête
		String req = "SELECT c FROM Commande AS c WHERE c.client.idClient IS NULL";

		// création du query
		Query query = em.createQuery(req);

		// création de la nouvelle liste des lignes commandes
		Commande cOut = (Commande) query.getSingleResult();

		return cOut;
	}

	@Override
	public List<Commande> gettAllCommande(long idCl) {
		// construre la requête
		String req = "SELECT c FROM Commande AS c WHERE c.client.idClient=:idCl";

		// création du query
		Query query = em.createQuery(req);

		// Spécification des paramètres
		query.setParameter("idCl", idCl);

		// création de la nouvelle liste des lignes commandes
		List<Commande> listeCommande = query.getResultList();

		return listeCommande;
	}

	@Override
	public Commande getCommande(long idCommande) {
		Commande cOut = em.find(Commande.class, idCommande);
		
		return cOut;
	}

}
