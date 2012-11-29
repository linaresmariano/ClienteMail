package contacto;

import java.util.List;
import java.util.ArrayList;

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
	
	public void addContacto(Contacto c) {
		this.getContactos().add(c);
	}
	
	public List<Contacto> getContactos() {
		return this.contactos;
	}
	
	public String getMail() {
		return "";
	}

}
