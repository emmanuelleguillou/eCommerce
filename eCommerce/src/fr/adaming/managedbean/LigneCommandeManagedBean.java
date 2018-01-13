package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
//@SessionScoped
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	@EJB
	private ILigneCommandeService ligneCommandeService;
	@EJB
	private IProduitService produitService;

	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;
	private boolean indice = false;
	List<LigneCommande> listeLCbyC;
	private int  idCommande;
	// Constructeur par défaut
	public LigneCommandeManagedBean() {
		this.ligneCommande = new LigneCommande();
		this.produit = new Produit();

	}

	// Getters et setters
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

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
	public List<LigneCommande> getListeLCbyC() {
		return listeLCbyC;
	}

	public void setListeLCbyC(List<LigneCommande> listeLCbyC) {
		this.listeLCbyC = listeLCbyC;
	}

	
	
	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	// Les méthodes
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Ligne de commande ajoutée"));
			return "afficherListeProduitClient";
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

	public String afficherLigneCommandeByIDCommande() {
		//Il faut trouver un moyen pour recuperer l'id de la commande
		indice = true;
		//passer toutes les lignes de commande d'une commande dans une liste
		//List<LigneCommande> listeLCbyC = ligneCommandeService.getAllLigneCommandeByIdCommande(this.commande.getIdCommande());
		//Passer la liste dans la sessio,
		
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeLCbyC", liste);
		listeLCbyC = ligneCommandeService.getAllLigneCommandeByIdCommande(this.idCommande);
		for (LigneCommande ligneCommande : listeLCbyC) {
			System.out.println(ligneCommande);
		}
		return "accueilClient";
	}
	
	
	
}
