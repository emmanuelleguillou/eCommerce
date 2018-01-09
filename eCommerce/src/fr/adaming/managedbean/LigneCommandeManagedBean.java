package fr.adaming.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	@EJB
	private ILigneCommandeService ligneCommandeService;

	LigneCommande ligneCommande;
	Produit produit;
	Commande commande;

	// Constructeur par défaut
	public LigneCommandeManagedBean() {
		this.ligneCommande = new LigneCommande();
		this.produit = new Produit();
		this.commande = new Commande();
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// Les Méthodes

	public String ajouterLigneCommande() {
		System.out.println(this.ligneCommande);
		this.ligneCommande = ligneCommandeService.addLigneCommande(this.ligneCommande);
		System.out.println(this.ligneCommande);
		if (this.ligneCommande.getIdLigneCommande() != 0){
			return "accueil";
		} else {
			return "ajouterLigneCommande";
		}
		
	}

	public String modifierLigneCommande() {
		this.ligneCommande = ligneCommandeService.updateLigneCommande(this.ligneCommande);
		if (this.ligneCommande != null){
			return "accueil";
		} else {
			return "modifierLigneCommande";
		}
	
		}
	
	public String rechercherLigneCommande() {
		LigneCommande lcOut = ligneCommandeService.getLigneCommande(this.ligneCommande.getIdLigneCommande());
		if (lcOut != null){
			this.ligneCommande = lcOut;
			return "accueil";
		} else {
			return "rechercherLigneCommande";
		}
	
	}
	
	public String supprimerLigneCommande() {
		ligneCommandeService.deleteLigneCommande(this.ligneCommande.getIdLigneCommande());
		if (this.ligneCommande == null) {
			return "accueil";
		}else {
			return "supprimerLigneCommande";
		}
		
	}
	
}
