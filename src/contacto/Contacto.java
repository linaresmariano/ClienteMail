package contacto;

public abstract class Contacto {
	
	private String nombre;
	
	// Constructor de un contacto con un nombre
	public Contacto(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract boolean contains(Contacto c);
	public abstract String getMail();
	
	// Eliminar un contacto
	public abstract void eliminarContacto(Contacto c);
	
	// Son iguales cuando tienen el mismo mail
	public boolean equals(Contacto c) {
		return this.getMail().equals(c.getMail());
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	
}
