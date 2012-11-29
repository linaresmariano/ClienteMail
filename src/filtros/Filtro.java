package filtros;

import filtros.accion.*;
import directorio.Mail;

/**
 * Utilizada para filtrar mails, realizando distintas acciones
 * cuando cumplen cierta condicion. Puede ser excluyente, osea
 * que no va a permitir que se aplique el resto de los filtros
 */

public class Filtro {
	
	private boolean excluyente;
	private Accion accion;
	private Condicion condicion; 
	
	/**
	 * 
	 * @param excluyente: boolean exclutente
	 * @param act: accion a ejecutar cuando un filtro cumple condicion
	 * @param cond: condicion que se quiere evaluar de los mails
	 */
	public Filtro(boolean excluyente, Accion act, Condicion cond) {
		this.excluyente = excluyente;
		this.accion = act;
		this.condicion = cond;
	}
	
	// Evalua el mail "m" con la condicion 
	public boolean evaluar(Mail m) {
		return this.condicion.evaluar(m);
	}
	
	// Ejecuta la accion al mail "m"
	public void ejecutarAccion(Mail m) {
		this.accion.ejecutarAccion(m);
	}
	
	// Aplicar el filtro si la evaluacion del mail da true,sino no hacer nada
	public boolean aplicar(Mail m) {
		boolean retVal = this.evaluar(m);
		
		if(retVal) {
			this.ejecutarAccion(m);
		}
		
		return retVal;
	}
	
	// Retorna un booleano indicando si el filtro es excluyente
	public boolean esExcluyente() {
		return this.excluyente;
	}
	
}
