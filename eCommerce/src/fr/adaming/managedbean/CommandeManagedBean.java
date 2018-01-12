package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "cMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	@EJB
	ICommandeService commandeService;
	@EJB
	ILigneCommandeService ligneCommandeService;

	private Commande commande;
	private Client client;
	private List<LigneCommande> listeLigneCommande;
	private LigneCommande ligneCommande;

	// constructeur par d�faut
	public CommandeManagedBean() {
		this.commande = new Commande();
		this.client = new Client();
		this.ligneCommande = new LigneCommande();
	}

	// getters et setters
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	// Les m�thodes
	public String ajouterComande() {
		//Cr�er une commande
		this.commande = commandeService.addCommande(this.commande);
		
		//r�cup�rer toutes les lignes de commandes avec un idCommande null
		this.listeLigneCommande = ligneCommandeService.getAllLignesCommandes();
		
		//Donner � chaque ligne de commande l'id de la commande cr��e
		for (LigneCommande lc : this.listeLigneCommande) {
			lc.setCommandes(this.commande);
			this.ligneCommande = ligneCommandeService.updateLigneCommande(lc);
			System.out.println("commande de la lc : " + this.ligneCommande);
		}

		//g�n�rer une nouvelle liste des ligne commande qui sont associ�es � la commande
		this.listeLigneCommande = ligneCommandeService.getAllLigneCommandeByIdCommande(this.commande.getIdCommande());
		System.out.println("liste des lc par id commande : " + this.listeLigneCommande);
		if (this.commande != null) {
			return "loginClient";
		} else {
			return "panier";
		}
	}

	public String modifierCommande() {
		this.commande = commandeService.updateCommande(this.commande);
		if (this.commande.getIdCommande() != null) {
			return "pageClient";
		} else {
			return "panier";
		}
	}

	public String rechercherCommande() {
		Commande cOut = commandeService.getCommande(this.commande.getIdCommande());
		if (cOut != null) {
			this.commande = cOut;
			return "rechercheCommande";
		} else {
			return "rechercherCommande";
		}
	}

	public String supprimerCommande() {
		commandeService.deleteCommande(this.commande.getIdCommande());
		Commande cOut = commandeService.getCommande(this.commande.getIdCommande());

		if (cOut == null) {
			return "pageClient";
		} else {
			return "suprimerCommande";
		}
	}

	public String rechercherToutesCommandes() {
		List<Commande> listeCommande = commandeService.gettAllCommande(this.client.getIdClient());
		//passer la liste dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCommandes",
				listeCommande);
		return "#";
	
	}
}
