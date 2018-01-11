package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
@Local
public interface IClientService {

	public Client addClient(Client cl);

	public void deleteClient(long idCl);

	public Client getClientByNomEmail(String nom, String email);

	public Client updateClient(Client cl);

}
