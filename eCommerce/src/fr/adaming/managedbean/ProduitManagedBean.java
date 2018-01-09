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

@ManagedBean(name="prMB")
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
		this.produit=new Produit();
	}
	
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
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
	
	public String ajouterProduit(){
		this.produit=produitService.addproduit(this.produit);
		
		if (this.produit.getIdProduit() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeProduit = produitService.getAllPorduit();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("produitList", this.listeProduit);
			return "accueilAdmin";
		} else
			return "failure";
	}
	
	public String supprimerProduit() {
		// Récuperer l'agent dans la session
		produitService.deleteProduit(this.produit.getIdProduit());
		// recuperer la nouvelle liste de la bd
		this.listeProduit = produitService.getAllPorduit();
		// Mettre a jour la liste dans la session
		maSession.setAttribute("produitList", this.listeProduit);
		return "accueilAdmin";
	}
	
	public String modifierCategorie() {
		// Récuperer l'agent dans la session
		this.produit = produitService.updateProduit(this.produit);

		if (this.categorie.getIdCategorie() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeProduit = produitService.getAllPorduit();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("produitList", this.listeProduit);
			return "accueilAdmin";
		} else
			return "accueilAdmin";
	}
	
	public String rechercherProduit() {
		this.produit = produitService.getProduit(this.produit.getIdProduit());
		if (this.produit.getIdProduit() != 0) {
			return "rechercherProduit";
		} else
			return "rechercherProduit";

	}
	
	public void afficherParCategorie(){
		this.listeProduit=produitService.getAllPorduitByCategorie(idCategorie);
	}
	
}
