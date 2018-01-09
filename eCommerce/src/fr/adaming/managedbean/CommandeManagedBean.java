package fr.adaming.managedbean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="cMB")
@RequestScoped
public class CommandeManagedBean implements Serializable{

}
