package contacto;

import java.util.List;

public class Lista extends Contacto {

	private List<Contacto> contactos;
	
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
	
	public List<Contacto> getContactos() {
		return this.contactos;
	}

}
