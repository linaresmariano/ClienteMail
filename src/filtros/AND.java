package filtros;

import directorio.Mail;

/**
 * Condicion compuesta de filtros. Une otras condiciones
 * con el operador lógico AND
 */
public class AND extends CondicionCompuesta {

	public AND(Condicion a, Condicion b) {
		this.addCondicion(a);
		this.addCondicion(b);
	}
	
	public AND() {
	}
	
	@Override
	public boolean evaluar(Mail m) {
		// Si no hay operandos retorna false
		if (this.getOperandos().isEmpty()) {
			return false;
		}
		
		// Si hay operandos, los evalua
		boolean retval = true;

		for(Condicion c : this.getOperandos()) {
			retval = c.evaluar(m) && retval;
		}
		
		return retval;
	}

}
