package filtros;

import filtros.accion.*;
import directorio.Mail;

public class Filtro {
	
	private boolean excluyente;
	private Accion accion;
	private Condicion condicion; 
	
	public Filtro(boolean excluyente, Accion act, Condicion cond) {
		this.excluyente = excluyente;
		this.accion = act;
		this.condicion = cond;
	}
	
	public boolean evaluar(Mail m) {
		return this.condicion.evaluar(m);
	}
	
	public void ejecutarAccion(Mail m) {
		this.accion.ejecutarAccion(m);
	}
	
	public void aplicar(Mail m) {
		this.evaluar(m);
		this.ejecutarAccion(m);
	}
	
	public boolean esExcluyente() {
		return this.excluyente;
	}
	
}
