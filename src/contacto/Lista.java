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
		// Recorre la lista de contactos buscando a "c"
		for(Contacto cont : this.getContactos()) {
			// Si el contacto actual con tiene a "c" termina
			if(cont.contains(c)) {
				return true;
			}
		}
		// Si no lo encuentra devuelve false
		return false;
	}
	
	// Agrega un contacto a la lista de contactos
	public void addContacto(Contacto c) {
		this.getContactos().add(c);
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
