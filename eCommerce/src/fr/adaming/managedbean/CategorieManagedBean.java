package fr.adaming.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "caMB")
@RequestScoped
public class CategorieManagedBean {

	@EJB
	private ICategorieService categorieService;

	private Categorie categorie;
	private List<Categorie> listeCategories;

	private HttpSession maSession;

	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public ICategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public String ajouterCatgeorie() {
		// Récuperer l'agent dans la session
		this.categorie = categorieService.addCategorie(this.categorie);

		if (this.categorie.getIdCategorie() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeCategories = categorieService.getAllCategorie();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("categoriesList", this.listeCategories);
			return "accueilAdmin";
		} else
			return "failure";
	}

	public String supprimerCategorie() {
		// Récuperer l'agent dans la session
		categorieService.deleteCategorie(this.categorie.getIdCategorie());
		// recuperer la nouvelle liste de la bd
		this.listeCategories = categorieService.getAllCategorie();
		// Mettre a jour la liste dans la session
		maSession.setAttribute("categoriesList", this.listeCategories);
		return "accueilAdmin";
	}
	
	

	public String modifierCategorie() {
		// Récuperer l'agent dans la session
		this.categorie = categorieService.updateCategorie(this.categorie);

		if (this.categorie.getIdCategorie() != 0) {
			// recuperer la nouvelle liste de la bd
			this.listeCategories = categorieService.getAllCategorie();
			// Mettre a jour la liste dans la session
			maSession.setAttribute("categoriesList", this.listeCategories);
			return "accueilAdmin";
		} else
			return "accueilAdmin";
	}

	public String rechercherCategorie() {
		this.categorie = categorieService.getCategorie(this.categorie.getIdCategorie());
		if (this.categorie.getIdCategorie() != 0) {
			return "rechercherCategorie";
		} else
			return "rechercherCategorie";

	}
	
	public String modifLien() {
		// Appel de la methode service
		Categorie cOut = categorieService.getCategorie(this.categorie.getIdCategorie());
		
		this.categorie=cOut;
		
		return"modifCategorie";
	}

}
