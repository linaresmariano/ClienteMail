package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import directorio.Mail;
import filtros.accion.*;

public class AccionTest {

	private Accion accion;
	private Mail mail;
	
	@Before
	public void setUp() {
		this.mail = mock(Mail.class);
	}

	@Test
	public void testEjecutarAccionBorrar() {
		// Accion a testear
		this.accion = new Borrar();
		
		this.accion.ejecutarAccion(this.mail);
		verify(this.mail).setBorrado(true);
	}
	
	@Test
	public void testEjecutarAccionMoverACarpeta() {
		// Accion a testear
		this.accion = new MoverACarpeta();
		
		this.accion.ejecutarAccion(this.mail);
		verify(this.mail).setPath(this.accion.getPathDestino());
		assertEqual(this.mail.getPath(), this.accion.getPathDestino());
	}
	
	@Test
	public void testEjecutarAccionMoverACarpeta() {
		// Accion a testear
		this.accion = new MarcarComoLeido();
		
		this.accion.ejecutarAccion(this.mail);
		verify(this.mail).setPath(this.accion.setLeido(true));
	}
}
