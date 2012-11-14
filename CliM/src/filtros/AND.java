package filtros;

import filtrosTest.Mail;

public class AND extends CondicionCompuesta {

	public AND(Condicion a, Condicion b) {
		this.addCondicion(a);
		this.addCondicion(b);
	}
	
	@Override
	public boolean evaluar(Mail m) {
		
		boolean retval = true;

		for(Condicion c : this.getOperandos()) {
			retval = c.evaluar(m) && retval;
		}
		
		return retval;
	}

}
