package contacto;

public class ContactoHoja extends Contacto {
	
	private String mail;
	
	// Constructor de un contacto simple con un nombre y un mail
	public ContactoHoja(String nombre, String mail) {
		super(nombre);
		this.mail = mail;
	}
	
	// Denota true si soy contacto c
	public boolean contains(Contacto c) {
		return this.equals(c);
	}
	
	// Denota mi direccion de mail
	public String getMail() {
		return this.mail;
	}

	@Override
	public void eliminarContacto(Contacto c) {}
}
