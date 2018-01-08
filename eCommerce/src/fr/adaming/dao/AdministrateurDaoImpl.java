package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {
	@PersistenceContext(unitName = "eCommerce") // Pour l'injection d'un em
	EntityManager em;

	// Setter pour l'inkection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Administrateur addAdmin(Administrateur a) {
		em.persist(a);
		return a;
	}

}
