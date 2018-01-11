package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.plaf.synth.SynthSpinnerUI;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "paMB")
@RequestScoped
public class PanierManagedBean implements Serializable {

	@EJB
	ILigneCommandeService ligneCommandeService;

	private Panier panier;
	private LigneCommande ligneCommande;
	private List<LigneCommande> listeLignecommande;

	// constructeur par défaut
	public PanierManagedBean() {
		this.panier = new Panier();
		this.ligneCommande = new LigneCommande();
		this.listeLignecommande = new ArrayList<LigneCommande>();

	}

	// getter et setter
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public List<LigneCommande> getListeLignecommande() {
		return listeLignecommande;
	}

	public void setListeLignecommande(List<LigneCommande> listeLignecommande) {
		this.listeLignecommande = listeLignecommande;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	// Les méthodes
	public void envoyerPanier() {
		List<LigneCommande> listeLCPanier = new ArrayList<LigneCommande>();
		List<LigneCommande> liste = ligneCommandeService.getAllLignesCommandes();
		System.out.println("liste des lignes commandes : \n" );
		liste.forEach(System.out::println);
		for (LigneCommande lc : liste) {
			listeLCPanier.add(lc);
			listeLCPanier.forEach(System.out::println);

			this.listeLignecommande = listeLCPanier;
			
			if (this.listeLignecommande != null) {
				// passer la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeLCPanier",
						this.listeLignecommande);
				System.out.println("liste session : " + this.listeLignecommande);

			}
		}

	}

}
