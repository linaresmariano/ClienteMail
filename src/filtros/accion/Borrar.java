package filtros.accion;

import java.util.LinkedList;
import java.util.List;

/**
 *  Mueve el mail a la carpeta Papelera
 *  pathDestino = /papelera
 *
 */
public class Borrar extends MoverACarpeta {
	
	public Borrar() {
		List<String> papelera = new LinkedList<String>();
		papelera.add("papelera");
		
		this.setPathDestino(papelera);
	}

}