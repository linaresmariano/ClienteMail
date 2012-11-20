package filtros.condicionSimple;

import filtros.Mail;

public class Asunto extends Campo {
	
	private String valor;
	
	public Asunto(String valor) {
		this.valor = valor;
	}

	public boolean evaluarContiene(Mail m) {
		return m.getAsunto().contains(this.getValor());
	}
	
	public boolean evaluarEsIgual(Mail m) {
		return m.getAsunto().contentEquals(this.getValor());
	}

	public boolean evaluarEsDistinto(Mail m) {
		return !(m.getAsunto().contentEquals(this.getValor()));
	}
	
	public String getValor() {
		return this.valor;
	}
}
