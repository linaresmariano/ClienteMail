package filtros.accion;

import java.util.LinkedList;
import java.util.List;

import directorio.Mail;

public class MoverACarpeta extends Accion {

	// Direccion donde se debe mover el mail
	private List<String> pathDestino = new LinkedList<String>();
	
	public MoverACarpeta(List<String> destino) {
		this.pathDestino = destino;
	}
	
	public MoverACarpeta() {
	}
	
	@Override
	// Le setea al mail la nueva etiqueta
	public void ejecutarAccion(Mail m) {
		m.setEtiqueta(this.getPathDestino());
	}
	
	// Direccion donde se debe mover el mail
	public List<String> getPathDestino() {
		return this.pathDestino;
	}
	
	// Cambiar el path destino del filtro
	public void setPathDestino(List<String> path) {
		this.pathDestino = path;
	}

}
