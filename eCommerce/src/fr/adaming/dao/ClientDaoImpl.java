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

	// getter et setter pour injection dépendance
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// Les méthodes
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
	public Client getClientById(long idCl) {
		Client clOut = em.find(Client.class, idCl);
		return clOut;
	}

	@Override
	public Client updateClient(Client cl) {
		em.merge(cl);
		return cl;
	}

}
