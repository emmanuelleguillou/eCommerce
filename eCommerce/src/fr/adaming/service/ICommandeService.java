package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;


import fr.adaming.model.Commande;

@Local
public interface ICommandeService {

	public Commande addCommande(Commande c);

	public Commande updateCommande(Commande c);

	public void deleteCommande(long idCommande);

	public Commande getCommande(long idC);

	public List<Commande> gettAllCommande(long idCl);

}
