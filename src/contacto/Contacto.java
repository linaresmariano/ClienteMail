package contacto;

public abstract class Contacto {
	
	private String nombre;
	private String mail;
	
	public boolean equals(Contacto c) {
		return this.getMail().equals(c.getMail());
	}
	
	public abstract boolean contains(Contacto c);
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getMail() {
		return this.mail;
	}
}
