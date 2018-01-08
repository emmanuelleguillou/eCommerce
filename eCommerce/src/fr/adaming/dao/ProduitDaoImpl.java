package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "eCommerce") // Pour l'injection d'un em
	EntityManager em;

	// Setter pour l'inkection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Produit addproduit(Produit p) {
		em.persist(p);
		return p;
	}

	@Override
	public Produit getProduit(int id_p) {
		String req = "select p from Produit p where p.idProduit=:pId ";
		// Creer query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("pId", id_p);
		try {
			return (Produit) query.getSingleResult();
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public void deleteProduit(int id_p) {
		// creation de la requete jpql
		String req = "delete from Produit as p where p.idProduit=:pId";

		// Creer query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("pId", id_p);

		query.executeUpdate();

	}

	@Override
	public Produit updateProduit(Produit p) {
		em.merge(p);
		return p;
	}

	@Override
	public List<Produit> getAllPorduit() {
		// Creation de la requete JPQL
		String req = "select p from Produit as p ";
		// Creer un query
		Query query = em.createQuery(req);

		// Envoyer la requete
		return query.getResultList();
	}

	@Override
	public List<Produit> getAllPorduitByCategorie(int id_c) {
		// Creation de la requete JPQL
		String req = "select p from Produit as p where categorie_idCategorie=:cId";
		// Creer un query
		Query query = em.createQuery(req);
		
		// passage des param
		query.setParameter("cId", id_c);

		// Envoyer la requete
		return query.getResultList();
	}

}
