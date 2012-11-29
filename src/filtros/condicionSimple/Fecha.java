package filtros.condicionSimple;

import java.util.Calendar;

import directorio.Mail;

public class Fecha extends Campo {
	
	private Calendar valor;
	
	public Fecha(Calendar fecha) {
		this.valor = fecha;
	}

	@Override
	public boolean evaluarEsIgual(Mail m) {
		
		return 
		(m.getEncabezado().getFecha().get(Calendar.YEAR) ==
		this.getValor().get(Calendar.YEAR))
		&&
		(m.getEncabezado().getFecha().get(Calendar.MONTH) ==
		this.getValor().get(Calendar.MONTH))
		&&
		(m.getEncabezado().getFecha().get(Calendar.DATE) ==
		this.getValor().get(Calendar.DATE))
		&&
		(m.getEncabezado().getFecha().get(Calendar.HOUR_OF_DAY) ==
		this.getValor().get(Calendar.HOUR_OF_DAY));
	}
	
	@Override
	public boolean evaluarContiene(Mail m) {
		return this.evaluarEsIgual(m);
	}

	@Override
	public boolean evaluarEsDistinto(Mail m) {
		return ! this.evaluarEsIgual(m);
	}
	
	public Calendar getValor() {
		return this.valor;
	}

}
