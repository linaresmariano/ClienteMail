package directorio.partesDeMail;

public class Adjunto {

	private String nombre;
	private String archivo;
	
	public Adjunto(String nombre) {
		this.nombre = nombre;
		this.archivo = "";
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		
		this.archivo = archivo;	
	}
}
