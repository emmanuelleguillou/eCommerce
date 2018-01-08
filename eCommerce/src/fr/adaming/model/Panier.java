package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;


public class Panier implements Serializable {

	//Traduction de l'association d'uml en java
	@OneToMany(mappedBy="panier")
	private List<LigneCommande> listeLigneCommande;

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	public Panier() {
		super();
	}
	
	
	
}
