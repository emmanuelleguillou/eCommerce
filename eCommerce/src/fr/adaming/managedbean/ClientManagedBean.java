package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	@EJB
	private IClientService clientService;

	private Client client;
	private List<Commande> commande;
	private Panier panier;

	// Constructeur par défaut
	public ClientManagedBean() {
		this.client = new Client();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Commande> getCommande() {
		return commande;
	}

	public void setCommande(List<Commande> commande) {
		this.commande = commande;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	// Les Méthodes

	public String ajouterClient() {
		this.client = clientService.addClient(this.client);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", this.client);

		if (this.client.getIdClient() != 0) {
			return "accueilClient";
		} else {
			return "loginClient";
		}
	}

	public String supprimerClient() {
		clientService.deleteClient(this.client.getIdClient());
		if (this.client.getIdClient() == null) {
			return "accueil";
		} else {
			return "supprimerClient";
		}

	}

	public String rechercherClient() {
		Client clOut = clientService.getClientByNomEmail(this.client.getNomClient(), this.client.getEmail());
		if (clOut != null) {
			this.client = clOut;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", this.client);
			return "accueilClient";
		} else {
			return "loginClient";
		}

	}

	public String modifierClient() {
		Client clOut = clientService.updateClient(this.client);
		if (clOut != null) {
			return "accueil";
		} else {
			return "modifierClient";
		}

	}

	public void deconnexionClient() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
	}

}
