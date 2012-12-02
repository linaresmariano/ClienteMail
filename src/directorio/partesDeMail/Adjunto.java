package directorio.partesDeMail;

public class Adjunto {

	private String nombre;
	private Object archivo;
	
	public Adjunto(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Object getArchivo() {
		return archivo;
	}	
}
