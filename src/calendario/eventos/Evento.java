package calendario.eventos;

import java.util.Calendar;

import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;

public abstract class Evento {

	private String titulo;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private String lugar;
	private String descripcion;
	private Periodicidad periodicidad;
	
	public Evento(String titulo,Calendar fechaIni,Calendar fechaFin,Periodicidad periodicidad) throws FechaInicioMayorAFechaFinException{
		if(fechaIni.after(fechaFin)){
			throw new FechaInicioMayorAFechaFinException();
		}
		this.titulo=titulo;
		this.fechaInicio=fechaIni;
		this.fechaFin=fechaFin;
		this.periodicidad=periodicidad;
	}
	
    //metodos abstractos
	public abstract boolean termino(Calendar actual);
	public abstract void realizarEvento(Calendar actual) ;
	
	
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
