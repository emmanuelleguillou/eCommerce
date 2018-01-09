package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	@EJB
	private IProduitService produitService;

	private Categorie categorie;
	private List<Produit> listeProduit;
	private Produit produit;
	private HttpSession maSession;
	private int idCategorie;

	public ProduitManagedBean() {
		this.produit = new Produit();
	}


	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// Recuperer la liste des produit 
		this.listeProduit =  (List<Produit>) maSession.getAttribute("produitListByCat");
	}

	public IProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String ajouterProduit() {
		this.produit = produitService.addproduit(this.produit);

		if (this.produit.getIdProduit() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeProduit = produitService.getAllPorduit();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("produitListByCat", this.listeProduit);
			return "accueilAdmin";
		} else
			return "failure";
	}

	public void supprimerProduit() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		produitService.deleteProduit(this.produit.getIdProduit());
		// recuperer la nouvelle liste de la bd
		this.listeProduit = produitService.getAllPorduitByCategorie(idCategorie);
		// Mettre a jour la liste dans la session
		maSession.setAttribute("produitListByCat", this.listeProduit);
		
	}

	public String modifierProduit() {
		// Récuperer l'agent dans la session
		this.produit = produitService.updateProduit(this.produit);

		if (this.produit.getIdProduit() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeProduit = produitService.getAllPorduit();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("produitListByCat", this.listeProduit);
			return "afficherListeProduit";
		} else
			return "afficherListeProduit";
	}

	public String rechercherProduit() {
		this.produit = produitService.getProduit(this.produit.getIdProduit());
		if (this.produit.getIdProduit() != 0) {
			return "rechercherProduit";
		} else
			return "rechercherProduit";

	}

	public String afficherParCategorie() {
		this.listeProduit = produitService.getAllPorduitByCategorie(idCategorie);
		// Ajouter la liste dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListByCat", listeProduit);
		return "afficherListeProduit";
	}
	
	public String modifLien() {
		// Appel de la methode service
		Produit pOut = produitService.getProduit(this.produit.getIdProduit());
		
		this.produit=pOut;
		
		return"modifProduit";
	}

}
