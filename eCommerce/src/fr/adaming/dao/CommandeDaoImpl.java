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
	public void deleteCommande(int idCommande) {
		Commande cOut = em.find(Commande.class, idCommande);
		em.remove(cOut);

	}

	@Override
	public Commande getCommande(Commande c) {
		Commande cOut = em.find(Commande.class, c);
		return cOut;
	}

	@Override
	public List<Commande> gettAllCommande(long idCl) {
		// construre la requ�te
		String req = "SELECT c FROM Commande AS c WHERE idClient=:idCl";

		// cr�ation du query
		Query query = em.createQuery(req);

		// Sp�cification des param�tres
		query.setParameter("idCl", idCl);

		// cr�ation de la nouvelle liste des lignes commandes
		List<Commande> listeCommande = query.getResultList();

		return listeCommande;
	}

}
