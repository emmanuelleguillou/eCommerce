package fr.adaming.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.service.IAdministrateurService;




@ManagedBean(name="adminMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {

	@EJB
	private IAdministrateurService administrateurService;
	
	private Administrateur administrateur;

	private HttpSession maSession;
	
	public AdministrateurManagedBean() {
		this.administrateur=new Administrateur();
	}

	

	public IAdministrateurService getAdministrateurService() {
		return administrateurService;
	}

	public void setAdministrateurService(IAdministrateurService administrateurService) {
		this.administrateurService = administrateurService;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	
	public String identifierAdmin(){
		Administrateur aOut = administrateurService.isExist(this.administrateur);

		if (aOut!=null){
			return "success";
		}else
			return "failure";
	}
	
}
