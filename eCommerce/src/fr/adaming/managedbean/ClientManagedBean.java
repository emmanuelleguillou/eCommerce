package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;

@ManagedBean(name = "clMB")
@SessionScoped
public class ClientManagedBean implements Serializable {

	@EJB
	private IClientService clientService;
	@EJB
	private ICommandeService commandeService;

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

		// Récupérer la commande effectuée par le client avant l'enregistrement
		// de celui-ci (id_c null dans la comande)
		this.commande = commandeService.getCommandeByIdClNULL(this.client.getIdClient());

		// Donner le client à la commande associée
		this.commande.setClient(this.client);
		this.commande = commandeService.updateCommande(this.commande);

		// liste des commandes du client
		this.listeCommandes = commandeService.gettAllCommande(this.client.getIdClient());
		// Passer la liste des commandes dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCommandes",
				this.listeCommandes);

		if (this.client.getIdClient() != 0) {
			return "accueilClient";
		} else {
			return "loginClient";
		}
	}

	public String supprimerClient() {
		clientService.deleteClient(this.client.getIdClient());
		if (this.client.getIdClient() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Votre compte a été supprimé"));
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Votre compte n'a pas été supprimé"));
			return "supprimerClient";
		}

	}

	public String rechercherClient() {
		Client clOut = clientService.getClientByNomEmail(this.client.getNomClient(), this.client.getEmail());

		if (clOut != null) {
			this.client = clOut;

			// Récupérer la commande effectuée par le client avant
			// l'enregistrement de celui-ci (id_c null dans la comande)
			this.commande = commandeService.getCommandeByIdClNULL(this.client.getIdClient());

			// Donner le client à la commande associée
			this.commande.setClient(this.client);
			this.commande = commandeService.updateCommande(this.commande);
			// Passer le client dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", this.client);

			// liste des commandes du client
			this.listeCommandes = commandeService.gettAllCommande(this.client.getIdClient());
			System.out.println("Liste des comandes : " + this.listeCommandes);
			
			// Passer la liste des commandes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCommandes",
					this.listeCommandes);

			return "accueilClient";
		} else {
			return "loginClient";
		}

	}

	public String modifierClient() {
		Client clOut = clientService.updateClient(this.client);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Votre compte a été modifié"));
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Votre compte n'a pas été modifié"));
			return "modifierClient";
		}

	}

	public void deconnexionClient() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

	}

}
