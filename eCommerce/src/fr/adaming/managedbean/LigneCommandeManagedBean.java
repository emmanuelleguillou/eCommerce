package fr.adaming.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	@EJB
	private ILigneCommandeService ligneCommandeService;
	@EJB
	private IProduitService produitService;

	LigneCommande ligneCommande;
	Produit produit;
	Commande commande;

	// Constructeur par défaut
	public LigneCommandeManagedBean() {
		this.ligneCommande = new LigneCommande();
		this.produit = new Produit();

	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	// Les Méthodes

	public String ajouterLigneCommande() {
		// récupération du produit par l'id entré
		this.produit = produitService.getProduit(this.produit.getIdProduit());
		// spécification du produit pour la ligne de commande
		this.ligneCommande.setProduit(this.produit);
		// calcul du prix total
		this.ligneCommande.setPrix(ligneCommandeService.calculPrixLigneCommande(this.ligneCommande, this.produit));
		// ajout de la ligne dans la base de données
		this.ligneCommande = ligneCommandeService.addLigneCommande(this.ligneCommande);
		System.out.println(this.ligneCommande);
		if (this.ligneCommande.getIdLigneCommande() != 0) {
			return "accueil";
		} else {
			return "ajouterLigneCommande";
		}

	}

	public String modifierLigneCommande() {
		// récupération du produit par l'id entré
		this.produit = produitService.getProduit(this.produit.getIdProduit());
		// spécification du produit pour la ligne de commande
		this.ligneCommande.setProduit(this.produit);
		// calcul du prix total
		this.ligneCommande.setPrix(ligneCommandeService.calculPrixLigneCommande(this.ligneCommande, this.produit));
		// update de la ligne dans la base de données
		this.ligneCommande = ligneCommandeService.updateLigneCommande(this.ligneCommande);
		if (this.ligneCommande != null) {
			return "accueil";
		} else {
			return "modifierLigneCommande";
		}

	}

	public String rechercherLigneCommande() {
		LigneCommande lcOut = ligneCommandeService.getLigneCommande(this.ligneCommande.getIdLigneCommande());
		if (lcOut != null) {
			this.ligneCommande = lcOut;
			return "rechercherLigneCommande";
		} else {
			return "rechercherLigneCommande";
		}

	}

	public String supprimerLigneCommande() {
		
		ligneCommandeService.deleteLigneCommande(this.ligneCommande.getIdLigneCommande());
		LigneCommande lcOut = ligneCommandeService.getLigneCommande(this.ligneCommande.getIdLigneCommande());
		if (lcOut == null) {
			return "accueil";
		} else {
			return "supprimerLigneCommande";
		}

	}
	

}
