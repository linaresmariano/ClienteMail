package filtros.condicionSimple;

import filtros.Condicion;
import filtros.Mail;

public abstract class CondicionSimple extends Condicion {
	
	private Campo campo;
	
	public CondicionSimple(Campo c) {
		this.campo = c;
	}
	
	public abstract boolean evaluar(Mail m);
	
	public Campo getCampo() {
		return this.campo;
	}
}
