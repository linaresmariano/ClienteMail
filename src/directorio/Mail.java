package directorio;

import java.util.List;

import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;
import contacto.Contacto;

public class Mail extends DirectorioUsuario{

	private List<String> etiqueta;
	private Contacto remitente;
	private String asunto;
	private String cuerpo;
	private boolean leido;
	private Adjunto adjunto;
	private Encabezado encabezado;
	
	public List<String> getEtiqueta(){
		return this.etiqueta;
	}

	public void setEtiqueta(List<String> lista){
		this.etiqueta=lista;
	}
	
	public String getCuerpo(){
		return cuerpo;
	}

	@Override
	public boolean suNombreEs(String sombre) {
		return false;
	}

	public String getNombre(){
		return null;
	}

	public String getAsunto() {
		return asunto;
	}
	
	public Contacto getRemitente() {
		return this.remitente;
	}
	
	public boolean estaLeido() {
		return this.leido;
	}
	
	public void setLeido(boolean b) {
		this.leido = b;
	}
	
	public Adjunto getAdjunto() {
		return this.adjunto;
	}
	
	public Encabezado getEncabezado() {
		return this.encabezado;
	}

}
