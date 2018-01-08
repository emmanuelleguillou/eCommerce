package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administrateurs")
public class Administrateur {

	private int id_a;
	private String mail;
	private String mdp;
	
	
	
}
