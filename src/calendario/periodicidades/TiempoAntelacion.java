package calendario.periodicidades;

public class TiempoAntelacion {

	private int dia;
	private int mes;
	private int a�o;
	private int hora;
	private int minuto;
	
	public TiempoAntelacion(int a�o,int mes,int dia,int hora,int minuto){
		this.dia=Math.abs(dia);
		this.hora=Math.abs(hora);
		this.mes=Math.abs(mes);
		this.a�o=Math.abs(a�o);
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
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
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
