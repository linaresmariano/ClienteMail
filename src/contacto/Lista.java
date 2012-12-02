package contacto;

import java.util.List;
import java.util.ArrayList;

/**
 *  La clase Lista representa una lista de contactos
 */
public class Lista extends Contacto {

	private List<Contacto> contactos = new ArrayList<Contacto>();
	
	public Lista(String nombre) {
		super(nombre);
	}
	
	@Override
	public boolean contains(Contacto c) {
		return this.contactos.contains(c);
	}
	
	// Agrega un contacto a la lista de contactos
	public void addContacto(Contacto c) {
		this.getContactos().add(c);
	}
	
	@Override
	// Borra un contacto de la lista
	public void eliminarContacto(Contacto c) {
		this.getContactos().remove(c);
	}
	
	// Denota la lista de contactos
	public List<Contacto> getContactos() {
		return this.contactos;
	}
	
	// La lista no tiene mail
	public String getMail() {
		return "";
	}

}
