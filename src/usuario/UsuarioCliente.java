package usuario;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import cliente.Cliente;

import calendario.Calendario;
import calendario.eventos.Evento;
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
		this.directorio.agregarSubCarpeta("bandejaSalida");
		this.filtros = new LinkedList<Filtro>();
		this.contactos = new Lista("todos");
		this.estado = new Activo();
		// El calendario
		this.calendario = new Calendario();
		this.calendario.setEventos(new LinkedList<Evento>());
		this.calendario.setEventosActivos(new LinkedList<Evento>());
		this.calendario.start();
	}
	
	// Deriva el envio del mail al cliente
	public void enviarMail(Mail mail) throws Exception { 
		
		String destinatario = mail.getEncabezado().getDestinatario() ;
		try { 
			this.cliente.send(mail);
			LinkedList<String> nuevoPath = new LinkedList<String>();
			nuevoPath.add("enviados");
			this.directorio.moverA(mail, nuevoPath);
			System.out.println("El mail para:" + destinatario + " se ha enviado correctamente");
		} 
		
		catch (Exception e) { throw new Exception("El mail para: " + mail.getEncabezado().getDestinatario() + " no se ha enviado, quedar‡ en bandejaSalida"); }
	}
	
	// Deriva la eliminacion del mail a la estrategia
	public void eliminarMail(Mail mail) { 
		
		this.estrategia.eliminarMail(mail, this.getCliente().getServer(), this.getUsuario(), this.getDirectorio()); 	
	}
	
	// Baja mails segun la estrategia que posea el usuario, les aplica los filtros y agrega al directorio.
	// Las acciones de reenvio o aviso se derivan al estado del usuario
	public void recibirMails() {
		
		// Baja mail segun estrategia
		LinkedList<Mail> mails = this.estrategia.bajarYRetornarMails(this.getCliente().getServer(), this.usuario);
		
		// Recorre mails aplicando la lista de filtros
		for (Mail unMail : mails) {
			for (Filtro unFiltro : this.filtros) {
				if (unFiltro.aplicar(unMail) && unFiltro.esExcluyente()) { break; }
			}
			// Se agrega el mail al directorio del usuario
			this.directorio.agregarMail(unMail);
			// Se deriva el reenvio o aviso al estado del usuario
			this.estado.teLlegoMail(unMail, this);
		}
	}
	
	public void enviarMails() {
		
		// listaMails son todos los hijos de bandejaSalida
		// INVARIANTE DE REPRESENTACION - > Los hijos de borradores son todos de clase Mail
		List<DirectorioUsuario> listaMails = this.directorio.retornarCarpetaDeNombre("borradores").getHijos();
			
		// Para cada hijo de bandejaSalida se fija si es un Mail, lo envia y luego lo elimina
		for (DirectorioUsuario unMail : listaMails) {
 
			String destinatario = ((Mail) unMail).getEncabezado().getDestinatario() ;
					
			try { 
				this.enviarMail((Mail) unMail);
				System.out.println("El mail para: " + destinatario + " se ha eliminado de borradores");
			}

			catch (Exception e) { System.out.println("Ha fallado el envio de mails en el mail para: " + destinatario + " no se continuara el proceso"); }
			}
		}
	
	public Mail redactarMail(Contacto contacto, String asunto, String cuerpo) {
		
		// Creamos un nuevo mail con etiqueta "bandejaSalida"
		Mail newMail = new Mail();
		List<String> etiquetaSalida = new LinkedList<String>();
		etiquetaSalida.add("borradores");
		
		// Creamos el encabezado
		Encabezado newEncabezado = new Encabezado();
		newEncabezado.setRemitente(this.usuario);
		newEncabezado.setDestinatario(contacto.getMail());
		newEncabezado.setFecha(Calendar.getInstance());
		newEncabezado.setAsunto(asunto);
		
		// Asignamos valores a los atributos del nuevo mail
		newMail.setEncabezado(newEncabezado);
		newMail.setEtiqueta(etiquetaSalida);
		newMail.setCuerpo(cuerpo);
		newMail.setLeido(false);
	
		// Em mail queda en carpeta borradores, hasta que es enviado
		this.directorio.agregarMail(newMail);
		
		return newMail;
	}
	
	public Mail redactarMailConAdjunto(Contacto contacto, String asunto, String cuerpo, Adjunto adjunto) {
		
		Mail newMail = this.redactarMail(contacto, asunto, cuerpo);
		this.adjuntar(adjunto, newMail);
		return newMail;
	}
	
	public String getCuerpoMail(Mail mail) {
		
		return this.getEstrategia().getCuerpo(this.getCliente().getServer(), this.usuario, mail);
	}
	
	public Adjunto getAdjuntoMail(Mail mail) {
		
		return this.getEstrategia().getAdjunto(this.getCliente().getServer(), this.usuario, mail.getIndice());
	}
	
	public void adjuntar(Adjunto adjunto, Mail mail) { mail.setAdjunto(adjunto); }
	
	public void agregarContacto(Contacto contacto) { this.contactos.addContacto(contacto); }
	
	public void eliminarContacto(Contacto contacto) { this.contactos.eliminarContacto(contacto); }
	
	public void agregarFiltro(Filtro filtro) { this.filtros.add(filtro); }
	
	public void eliminarFiltro(Filtro filtro) { this.filtros.remove(filtro); }
	
	public void agregarEvento(Evento evento) { this.calendario.agregarEvento(evento); }
	
	public void eliminarEvento(Evento evento) {	this.calendario.eliminarEvento(evento);	}

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

	public Lista getContactos() { return contactos; }

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
