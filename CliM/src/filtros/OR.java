package filtros;

import filtrosTest.Mail;

public class OR extends CondicionCompuesta {

	public OR(Condicion a, Condicion b) {
		this.addCondicion(a);
		this.addCondicion(b);
	}
	
	@Override
	public boolean evaluar(Mail m) {
		
		boolean retval = false;

		for(Condicion c : this.getOperandos()) {
			retval = c.evaluar(m) || retval;
		}
		
		return retval;
	}

}
