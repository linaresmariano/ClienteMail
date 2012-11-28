package calendario.periodicidades;

import java.util.Calendar;

public class Diaria extends Periodicidad{


	@Override
	public boolean chequearSegunPeriodicidad(Calendar actual, Calendar inicio,
			TiempoAntelacion ta) {
		return true;
	}

	
}