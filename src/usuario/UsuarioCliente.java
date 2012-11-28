package usuario;

import java.util.Calendar;

import cliente.Cliente;

import calendario.Calendario;
import calendario.estrategiasRecordatorias.EstrategiaRecordatoria;
import calendario.eventos.Evento;
import calendario.periodicidades.Periodicidad;
import calendario.periodicidades.TiempoAntelacion;
import contacto.Contacto;
import directorio.DirectorioUsuario;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import estadoUsuario.EstadoUsuario;
import estrategiaAcceso.EstrategiaAcceso;
import filtros.Filtro;

public class UsuarioCliente {

	private Cliente cliente;
	private int nroCelular;
	private String password;
	private String usuario;
	private EstadoUsuario estado;
	private Contacto contactos;
	private Filtro filtros;
	private DirectorioUsuario directorio;
	private EstrategiaAcceso estrategia;
	private Calendario calendario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// Constructor
	public UsuarioCliente(String usuario, String password) {
		this.setUsuario(usuario);
		this.password = password;
	}
	
	// Se logua con el Cliente y checkea mails.
	public void login() {
		
		this.cliente.login(getUsuario(), password, servidor);
		this.recibirMail();
	}
	
	// El usuario cliente tiene que conocer al cliente para poder enviar mails!! arreglar en UML.
	public void enviarMail(Mail mail) {
		
		this.cliente.send(mail);
		
	}
	
	// Agregar en UML; metodo para recibir mail del servidor.
	public void recibirMail() {
		
		this.estrategia.login();
		
	}
	
	
	public Mail redactarMail(Contacto contactos, String asunto, String cuerpo) {
		
		
	}
	
	public void adjuntar(Adjunto adjunto, Mail mail) {
		
	}
	
	public void agregarContacto(Contacto contactos) {
		
	}
	
	public void eliminarContacto(Contacto contacto) {
		
	}
	
	public void agregarFiltro(Filtro filtros) {
		
	}
	
	public void eliminarFiltros(Filtro filtros) {
		
	}
	
	
	//Agregar argumento de antelacion de evento (unidad de tiempo)
	public void agregarEventoRecordatorio(String titulo, String descripcion, String lugar, Calendar inicio, Calendar fin, TiempoAntelacion antelacion, Periodicidad periodicidad, EstrategiaRecordatoria estrategia) {
		
	}
	
	public void agregarEventoModificador(String titulo, String descripcion, String lugar, Calendar inicio, Calendar fin, Periodicidad periodicidad) {
		
	}
	
	// EliminarEvento hay uno solo, remover en UML (hay para eventoModificador y para eventoRecordatorio)
	public void eliminarEvento(Evento evento) {
		
		this.calendario.eliminarEvento(evento);
	}
	
	
}
