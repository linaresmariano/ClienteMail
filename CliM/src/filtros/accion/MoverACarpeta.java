package filtros.accion;

import java.util.List;

import directorio.Mail;

public class MoverACarpeta extends Accion {

	private List<String> pathDestino;
	
	@Override
	public void ejecutarAccion(Mail m) {
		m.setEtiqueta(this.getPathDestino());
	}
	
	public List<String> getPathDestino() {
		return this.pathDestino;
	}

}
