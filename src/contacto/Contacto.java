package contacto;

public abstract class Contacto {
	
	private String nombre;
	
	public Contacto(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract boolean contains(Contacto c);
	public abstract String getMail();
	
	public boolean equals(Contacto c) {
		return this.getMail().equals(c.getMail());
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	
}
