package usuario;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import cliente.Cliente;

import calendario.Calendario;
import calendario.estrategiasRecordatorias.EstrategiaRecordatoria;
import calendario.eventos.Evento;
import calendario.periodicidades.Periodicidad;
import calendario.periodicidades.TiempoAntelacion;
import contacto.Contacto;
import contacto.Lista;
import directorio.Carpeta;
import directorio.DirectorioUsuario;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;
import estadoUsuario.Activo;
import estadoUsuario.EstadoUsuario;
import estrategiaAcceso.EstrategiaAcceso;
import filtros.Filtro;

public class UsuarioCliente {

	private Cliente cliente;
	private int nroCelular;
	private String password;
	private String usuario;
	private EstadoUsuario estado;
	private Lista contactos;
	private LinkedList<Filtro> filtros;
	// El DirectorioUsuario se inicia como una Carpeta
	private Carpeta directorio;
	private EstrategiaAcceso estrategia;
	private Calendario calendario;

	// Constructor
	public UsuarioCliente(String usuario, String password, Cliente cliente, EstrategiaAcceso estrategia) {
		
		// Los valores de los argumentos
		this.setUsuario(usuario);
		this.password = password;
		this.cliente = cliente;
		this.estrategia = estrategia;
		// Los valores por defecto
		this.directorio = new Carpeta("root");
		this.filtros = new LinkedList<Filtro>();
		this.contactos = new Lista();
		this.estado = new Activo();
		// El calendario
		this.calendario = new Calendario();
		this.calendario.setEventos(new LinkedList<Evento>());
		this.calendario.setEventosActivos(new LinkedList<Evento>());
		this.calendario.start();
	}
	
	// Deriva el envio del mail al cliente
	public void enviarMail(Mail mail) { this.cliente.send(mail); }
	
	// Deriva la eliminacion del mail a la estrategia
	public void eliminarMail(Mail mail) { this.estrategia.eliminarMail(mail, this.directorio); }
	
	// Baja mails segun la estrategia que posea el usuario, les aplica los filtros y los acomoda en su directorio
	public void recibirMails() {
		
		// Baja mail segun estrategia
		LinkedList<Mail> mails = this.estrategia.bajarYRetornarMails();
		
		// Recorre mails aplicando la lista de filtros
		for (Mail unMail : mails) {
			for (Filtro unFiltro : this.filtros) {
				if (unFiltro.aplicar(unMail) && unFiltro.esExcluyente()) { break; }
			}
		}
		
		// Agrega mails al directorio del usuario
		for (Mail unMail : mails) {
			this.directorio.agregarMail(unMail);
		}
	}
	
	
	public Mail redactarMail(Contacto contacto, String asunto, String cuerpo) {
		
		Mail newMail = new Mail();
		List<String> etiquetaSalida = new LinkedList<String>();
		etiquetaSalida.add("bandejaSalida");
		
		Encabezado newEncabezado = new Encabezado();
		newEncabezado.remitente = this.usuario;
		newEncabezado.destinatario = contacto;
		newEncabezado.fecha = Calendar.getInstance();
		newEncabezado.asunto = asunto;
		
		newMail.setEtiqueta(etiquetaSalida);
		
		
		private String asunto;
		private String cuerpo;
		private boolean leido;
		private Adjunto adjunto;
		private Encabezado encabezado;
		
	}
	
	public void adjuntar(Adjunto adjunto, Mail mail) {
		
	}
	
	public void agregarContacto(Contacto contactos) {
		
	}
	
	public void eliminarContacto(Contacto contacto) {
		
	}
	
	public void agregarFiltro(Filtro filtros) {
		
	}
	
	public void eliminarFiltro(Filtro filtros) {
		
	}
	
	
	//Agregar argumento de antelacion de evento (unidad de tiempo)
	public void agregarEvento(Evento evento) {
		
	}
	
	// EliminarEvento hay uno solo, remover en UML (hay para eventoModificador y para eventoRecordatorio)
	public void eliminarEvento(Evento evento) {
		
		this.calendario.eliminarEvento(evento);
	}

	
	// Getters y Setters
	public Cliente getCliente() { return cliente; }

	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public int getNroCelular() { return nroCelular; }

	public void setNroCelular(int nroCelular) { this.nroCelular = nroCelular; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getUsuario() { return usuario; }

	public void setUsuario(String usuario) { this.usuario = usuario; }
	
	public EstadoUsuario getEstado() { return estado; }

	public void setEstado(EstadoUsuario estado) { this.estado = estado; }

	public Contacto getContactos() { return contactos; }

	public void setContactos(Lista contactos) { this.contactos = contactos; }

	public LinkedList<Filtro> getFiltros() { return filtros; }

	public void setFiltros(LinkedList<Filtro> filtros) { this.filtros = filtros; }

	public Carpeta getDirectorio() { return directorio; }

	public void setDirectorio(Carpeta directorio) { this.directorio = directorio;}

	public EstrategiaAcceso getEstrategia() { return estrategia; }

	public void setEstrategia(EstrategiaAcceso estrategia) { this.estrategia = estrategia; }

	public Calendario getCalendario() { return calendario; }

	public void setCalendario(Calendario calendario) { this.calendario = calendario; }
}
