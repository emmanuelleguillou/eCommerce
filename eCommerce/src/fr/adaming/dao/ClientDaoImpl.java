package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "eCommerce")
	EntityManager em;

	// getter et setter pour injection d�pendance
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// Les m�thodes
	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public void deleteClient(long idCl) {
		Client clOut = em.find(Client.class, idCl);
		em.remove(clOut);
	}

	@Override
	public Client getClientByNomEmail(String nom, String email) {
		// cr�ation de la requete
		String req = "SELECT cl FROM Client AS cl WHERE cl.nomClient=:nomCl AND cl.email=:emailCl";

		// cr�ation du query
		Query query = em.createQuery(req);

		// Sp�cification des param�tres
		query.setParameter("nomCl", nom);
		query.setParameter("emailCl", email);

		// obtention du client en question
		Client clOut = (Client) query.getSingleResult();
		return clOut;
	}

	@Override
	public Client updateClient(Client cl) {
		em.merge(cl);
		return cl;
	}

}
