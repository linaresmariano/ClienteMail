package contacto;

public class ContactoHoja extends Contacto {
	
	private String mail;
	
	public ContactoHoja(String nombre, String mail) {
		super(nombre);
		this.mail = mail;
	}
	
	public boolean contains(Contacto c) {
		return this.equals(c);
	}
	
	public String getMail() {
		return this.mail;
	}
}
