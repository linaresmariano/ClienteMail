package directorioUsuario;

import java.util.List;

import directorioUsuario.partesDeMail.Adjunto;
import directorioUsuario.partesDeMail.Encabezado;

public class Mail extends DirectorioUsuario{

	private List<String> etiqueta;
	private String cuerpo;
	private boolean leido;
	private Adjunto adjunto;
	private List<Encabezado> encabezado;
	
	public List<String> getEtiqueta(){
		return this.etiqueta;
	}
	
	public String getCuerpo(){
		return cuerpo;
	}
	
	public void setEtiqueta(List<String> lista){
		this.etiqueta=lista;
	}


	@Override
	public boolean suNombreEs(String sombre) {
		return false;
	}

	public String getNombre(){
		return null;
	}
	
	
	
}
