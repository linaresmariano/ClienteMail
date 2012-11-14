package filtros;

import java.util.ArrayList;
import java.util.List;
import filtrosTest.Mail;

public class CondicionCompuesta extends Condicion {
	private OperadorBool operador;
	private List<Condicion> operandos = new ArrayList<Condicion>();
	
	public CondicionCompuesta(OperadorBool op) {
		this.operador = op;
	}
	
	public void addCondicion(Condicion c) {
		this.getOperandos().add(c);
	}
	
	public boolean evaluar(Mail m) {
		boolean retval = false;
		
		// Si hay operandos
		if(! this.getOperandos().isEmpty()) {
			retval = this.getOperandos().get(0).evaluar(m);
			
			for(Condicion c : this.getOperandos()) {
				retval = this.getOperador().evaluar(c.evaluar(m), retval);
			}
		}
		
		return retval;
	}
	
	public OperadorBool getOperador() {
		return this.operador;
	}
	
	public List<Condicion> getOperandos() {
		return this.operandos;
	}
}
