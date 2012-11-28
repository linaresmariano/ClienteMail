package filtros.accion;

import directorio.Mail;

public class MarcarComoLeido extends Accion {

	@Override
	public void ejecutarAccion(Mail m) {
		m.setLeido(true);
	}

}
