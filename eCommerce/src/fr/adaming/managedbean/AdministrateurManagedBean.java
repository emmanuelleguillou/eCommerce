package fr.adaming.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Administrateur;
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

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.administrateur = (Administrateur) maSession.getAttribute("adminSession");
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
	
	public String ajouterAdmin(){
		// Récuperer l'agent dans la session		
		this.administrateur= administrateurService.addAdmin(this.administrateur);
		System.out.println(this.administrateur);
		if (this.administrateur.getId_a()!=0){
			return "success";
		}else
			return "failure";
	}
	
}
