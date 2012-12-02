package directorio;

import java.util.Calendar;
import java.util.List;

import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;

public class Mail extends DirectorioUsuario{

	private List<String> etiqueta;
	private String cuerpo;
	private boolean leido;
	private Adjunto adjunto;
	private Encabezado encabezado;
	
	public void print(){
		for(int i=0;i<getEtiqueta().size()*2+2;i++){
			System.out.print(" ");
		}
		String mensaje;
		if(leido){
			mensaje=" <Leido>";
		}else{
			mensaje=" <Sin leer>";
		}
		System.out.println("+"+encabezado.getAsunto()+".mail"+mensaje);
	}
	

	
	public List<String> getEtiqueta(){
		return this.etiqueta;
	}

	public void setEtiqueta(List<String> lista){
		this.etiqueta=lista;
	}
	
	public String getCuerpo(){
		return cuerpo;
	}
	
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public boolean suNombreEs(String sombre) {
		return false;
	}

	public String getNombre(){
		return null;
	}

	public String getAsunto() {
		return this.getEncabezado().getAsunto();
	}
	
	public String getRemitente() {
		return this.getEncabezado().getRemitente();
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
	
	public void setAdjunto(Adjunto adjunto) {
		this.adjunto = adjunto;
	}
	
	public Encabezado getEncabezado() {
		return this.encabezado;
	}

	public Calendar getFecha() {
		return this.getEncabezado().getFecha();
	}
	
	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}

}
