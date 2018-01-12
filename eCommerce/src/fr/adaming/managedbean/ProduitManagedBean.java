package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@ViewScoped
public class ProduitManagedBean implements Serializable {
	@EJB
	private IProduitService produitService;
	@EJB
	private ICategorieService categorieService;

	private Categorie categorie;
	private List<Produit> listeProduit;
	private List<Produit> listeProduitAll;
	private Produit produit;
	private HttpSession maSession;
	private int idCategorie;
	private int nbProduit=0;

	public ProduitManagedBean() {
		this.produit = new Produit();
	}


	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// Recuperer la liste des produit 
		this.listeProduit =  (List<Produit>) maSession.getAttribute("produitListByCat");
		this.listeProduitAll =  (List<Produit>) maSession.getAttribute("produitListAll");
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
	
	public List<Produit> getListeProduitAll() {
		return listeProduitAll;
	}


	public void setListeProduitAll(List<Produit> listeProduitAll) {
		this.listeProduitAll = listeProduitAll;
	}

	public int getNbProduit() {
		return nbProduit;
	}


	public void setNbProduit(int nbProduit) {
		this.nbProduit = nbProduit;
	}


	public String ajouterProduitByCat() {
		//Recuperer la categorie
		int idCat=(int) maSession.getAttribute("idCat");
		this.produit.setCategorie(categorieService.getCategorieById(idCat));
		this.produit.setSelectionne(false);
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

	public String supprimerProduitByCat() {
		
		produitService.deleteProduit(this.produit.getIdProduit());
		System.out.println("IDCategorieTest= : " +idCategorie);
		int idCat=(int) maSession.getAttribute("idCat");
		// recuperer la nouvelle liste de la bd
		this.listeProduit = produitService.getAllPorduitByCategorie(idCat);
		//smaSession.setAttribute("idCategorie", idCategorie);
		// Mettre a jour la liste dans la session
		maSession.setAttribute("produitListByCat", this.listeProduit);
		return "afficherListeProduit";
	}

	public String modifierProduit() {  
		// Récuperer la catégorie
		int idCat=(int) maSession.getAttribute("idCat");
	
		this.produit.setCategorie(categorieService.getCategorieById(idCat));
		this.produit = produitService.updateProduit(this.produit);
		
		if (this.produit.getIdProduit() != 0) {
			 idCat=(int) maSession.getAttribute("idCat");
			// recuperer la nouvelle liste de la bd
			this.listeProduit = produitService.getAllPorduitByCategorie(idCat);
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
		maSession.setAttribute("idCat", idCategorie);
		this.listeProduit = produitService.getAllPorduitByCategorie(idCategorie);
		// Ajouter la liste dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListByCat", listeProduit);
		return "afficherListeProduit";
	}
	
	public String afficherAll() {
		this.listeProduitAll = produitService.getAllPorduit();
		// Ajouter la liste dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListAll", listeProduitAll);
		return "afficherProduits";
	}
	
	public String modifLien() {
		// Recuperation du produit
		Produit pOut = produitService.getProduit(this.produit.getIdProduit());
		
		this.produit=pOut;
		// Retour dans la page de modification avec remplissage des champs
		return"modifProduit";
	}
	
	public String afficherParCategorieClient() {
		maSession.setAttribute("idCat", idCategorie);
		this.listeProduit = produitService.getAllPorduitByCategorie(idCategorie);
		// Ajouter la liste dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListByCat", listeProduit);
		return "afficherListeProduitClient";
	}
	
	public String ajouter() {
		this.nbProduit++;
		System.out.println("Nombre produit :" +nbProduit);
		return "afficherListeProduitClient";
	}
	
	public String enlever() {
		this.nbProduit--;
		if(this.nbProduit<=0){
			this.nbProduit=0;
		}
		System.out.println("Nombre produit :" +nbProduit);
		return "afficherListeProduitClient";
	}

}
