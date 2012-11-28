package calendario;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import calendario.eventos.Evento;

public class Calendario extends Thread{

	private List<Evento> eventos;
	private List<Evento> eventosActivos;
	private final long duerme=1000*60;
	
	
	
	public synchronized void eliminarEvento(Evento e){
		eventosActivos.remove(e);
		eventos.remove(e);
	}
	
	public synchronized void agregarEvento(Evento e){
		eventosActivos.add(e);
		eventos.add(e);
	}
	
	public synchronized void avisarFecha(Calendar actual){
		LinkedList<Evento> aBorrar=new LinkedList<Evento>();
		for(Evento e:eventosActivos){
			e.realizarEvento(actual);
		
			if(e.termino(actual)){
				aBorrar.add(e);
			}
		}
		eventosActivos.removeAll(aBorrar);
	}
	
	public Calendar generarFechaActual(){
		Calendar c=Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), 
				c.get(Calendar.MONTH),
			    c.get(Calendar.DATE), 
				c.get(Calendar.HOUR), 
				c.get(Calendar.MINUTE),
				0);
		return c;
	}
	
	@Override
	public void run(){
		while(true){
			avisarFecha(generarFechaActual());
			try {
				Thread.sleep(duerme);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Evento> getEventosActivos() {
		return eventosActivos;
	}

	public void setEventosActivos(List<Evento> eventosActivos) {
		this.eventosActivos = eventosActivos;
	}

	public  long getDuerme() {
		return duerme;
	}
}