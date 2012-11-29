package filtros;

import directorio.Mail;

/**
 * Condicion compuesta de filtros. Une otras condiciones
 * con el operador lógico AND
 */
public class OR extends CondicionCompuesta {

	public OR(Condicion a, Condicion b) {
		this.addCondicion(a);
		this.addCondicion(b);
	}
	
	public OR() {
	}
	
	@Override
	// Evalua los operandos, si no los hay retorna false
	public boolean evaluar(Mail m) {
		
		boolean retval = false;

		for(Condicion c : this.getOperandos()) {
			retval = c.evaluar(m) || retval;
		}
		
		return retval;
	}

}
