package fr.adaming.managedbean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfLister;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

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
//	public static final String chemin = "C:/Users/PDT/testPdf.pdf";

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
		//Récupération de toutes les lignes de commandes associées à un idCommande null
		this.listeLignecommande = ligneCommandeService.getAllLignesCommandes();
		
		
	

			// //pdf
			// PdfTable document = new pdfta
			// try {
			// PdfWriter.getInstance(document, new FileOutputStream(chemin));
			// document.open();
			//
			// document.add();
			// } catch (IOException ioe) {
			// ioe.printStackTrace();
			// } catch (DocumentException de) {
			// de.printStackTrace();
			// } finally {
			// document.close();
			// }
			
			if (this.listeLignecommande != null) {
				// passer la liste dans la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeLCPanier",
						this.listeLignecommande);
				System.out.println("liste session : " + this.listeLignecommande);

			}
		}
		

		

	}


