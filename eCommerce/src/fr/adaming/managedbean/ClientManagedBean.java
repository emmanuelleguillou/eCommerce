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
	private Commande commande;
	private List<Commande> listeCommandes;
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

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setCommande(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// Les Méthodes
	public String ajouterClient() {
		this.client = clientService.addClient(this.client);
		// Passer le client dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", this.client);

		// Donner le client à la commande associée
//		this.commande.setClient(this.client);

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
			
			// Donner le client à la commande associée
//			this.commande.setClient(this.client);
			
			//Passer le client dans la session
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
