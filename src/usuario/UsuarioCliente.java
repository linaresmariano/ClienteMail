package usuario;

public class UsuarioCliente {

	private int nroCelular;
	private String contrase–a;
	private String usuario;
	private String servidor;
	private EstadoUsuario estado;
	private Contacto contactos;
	private Filtro filtros;
	private DirectorioUsuario directorio;
	private EstrategiaAcceso estrategia;
	private Calendario calendario;
	
	public void login() {
		
	}
	
	public void enviarMail(Mail mail) {
		
	}
	
	public Mail redactarMail(Contacto contactos, String asunto, String cuerpo) {
		
		
	}
	
	public void adjuntar(Adjunto adjunto, Mail mail) {
		
	}
	
	public void agregarContacto(Contacto contactos) {
		
	}
	
	public void eliminarContacto(Contacto) {
		
	}
	
	public void agregarFiltro(Filtro filtros) {
		
	}
	
	public void eliminarFiltros(Filtro filtros) {
		
	}
	
	
	//Agregar argumento de antelacion de evento (unidad de tiempo)
	agregarEventoRecordatorio(String titulo, String descripcion, String lugar, Calendar inicio, Calendar fin, Periodicidad periodicidad, EstrategiaRecordatorio estrategia) {
		
	}
	
	eliminarEventoRecordatorio(Evento evento) {
		
	}
	
	agregarEventoModificador(String titulo, String descripcion, String lugar, Calendar inicio, Calendar fin, Periodicidad periodicidad) {
		
	}
	
	eliminarEventoRecordatorio(Evento evento) {
		
	}
}
