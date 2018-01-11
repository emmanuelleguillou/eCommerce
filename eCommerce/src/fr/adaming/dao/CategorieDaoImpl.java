package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {
	@PersistenceContext(unitName = "eCommerce") // Pour l'injection d'un em
	EntityManager em;

	// Setter pour l'inkection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Categorie addCategorie(Categorie c) {
		em.persist(c);
		return c;
	}

	@Override
	public Categorie getCategorie(int id_c) {
		Categorie cOut = em.find(Categorie.class, id_c);
		return cOut;
	}

	@Override
	public void deleteCategorie(int id_c) {
		// creation de la requete jpql
		String req = "delete from Categorie as c where c.idCategorie=:cId";

		// Creer query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("cId", id_c);

		query.executeUpdate();
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		em.merge(c);
		return c;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// Creation de la requete JPQL
		String req = "select c from Categorie as c ";
		// Creer un query
		Query query = em.createQuery(req);

		// Envoyer la requete
		return query.getResultList();
	}
}
