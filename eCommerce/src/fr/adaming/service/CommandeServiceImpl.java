package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {

	@EJB
	private ICommandeDao commandeDao;

	// getter et setter
	public ICommandeDao getCommandeDao() {
		return commandeDao;
	}

	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	// les m�thodes
	@Override
	public Commande addCommande(Commande c) {
		Commande cOut = commandeDao.addCommande(c);
		return cOut;
	}

	@Override
	public Commande updateCommande(Commande c) {
		Commande cOut = commandeDao.updateCommande(c);
		return cOut;
	}

	@Override
	public void deleteCommande(int idCommande) {
		commandeDao.deleteCommande(idCommande);

	}

	@Override
	public Commande getCommande(Commande c) {
		Commande cOut = commandeDao.getCommande(c);
		return cOut;
	}

	@Override
	public List<Commande> gettAllCommande(long idCl) {
		List<Commande> liste = commandeDao.gettAllCommande(idCl);
		return liste;
	}

}
