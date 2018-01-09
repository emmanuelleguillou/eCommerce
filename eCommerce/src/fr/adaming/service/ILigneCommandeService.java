package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
@Local
public interface ILigneCommandeService {

	
	public LigneCommande addLigneCommande(LigneCommande lc);
	
	public LigneCommande updateLigneCommande(LigneCommande lc);
	
	public void deleteLigneCommande(int idLigneCommande);
	
	public LigneCommande getLigneCommande(int idLigneCommande);
	
	public List<LigneCommande> getAllLigneCommandeByIdCommande(int idCommande);
	
	public double calculPrixLigneCommande(LigneCommande lc, Produit p);
	
	
}
