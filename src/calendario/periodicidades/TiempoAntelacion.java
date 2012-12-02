package calendario.periodicidades;

public class TiempoAntelacion {

	private int dia;
	private int mes;
	private int año;
	private int hora;
	private int minuto;
	
	public TiempoAntelacion(int año,int mes,int dia,int hora,int minuto){
		this.dia=Math.abs(dia);
		this.hora=Math.abs(hora);
		this.mes=Math.abs(mes);
		this.año=Math.abs(año);
		this.minuto=Math.abs(minuto);
	}
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
}
