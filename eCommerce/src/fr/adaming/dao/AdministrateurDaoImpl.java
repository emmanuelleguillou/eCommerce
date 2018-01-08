package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {
	@PersistenceContext(unitName = "eCommerce") // Pour l'injection d'un em
	EntityManager em;

	// Setter pour l'inkection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public Administrateur isExist(Administrateur a) {
		// Construire la requete JPQL
		String req = "select a from Administrateur a where a.mail=:pMail and a.mdp=:pMdp ";
		// Creer un query
		Query query = em.createQuery(req);
		// Parametrer le query
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());

		// Envoyer la requete
		Administrateur aOut = (Administrateur) query.getSingleResult();

		return aOut;
	}

}
