package filtros;

import java.util.ArrayList;
import java.util.List;

import directorio.Mail;

public abstract class CondicionCompuesta extends Condicion {
	
	private List<Condicion> operandos = new ArrayList<Condicion>();
	
	public void addCondicion(Condicion c) {
		this.getOperandos().add(c);
	}
	
	public abstract boolean evaluar(Mail m);
	
	public List<Condicion> getOperandos() {
		return this.operandos;
	}
}
