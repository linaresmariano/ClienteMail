package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

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
	public void testEjecutarAccionMoverACarpeta() {
		
		// Destino del mover carpeta
		LinkedList<String> destino = new LinkedList<String>();
		destino.add("alguna");
		
		// Accion a testear
		MoverACarpeta accion = new MoverACarpeta(destino);
		
		accion.ejecutarAccion(this.mail);
		verify(this.mail).setEtiqueta(accion.getPathDestino());
		//assertEquals(this.mail.getEtiqueta(), accion.getPathDestino());
	}
	
	@Test
	public void testEjecutarAccionBorrar() {
		// Accion a testear
		Borrar accion = new Borrar();
		
		accion.ejecutarAccion(this.mail);
		
		verify(this.mail).setEtiqueta(accion.getPathDestino());
		assertEquals(this.mail.getEtiqueta(), accion.getPathDestino());
	}
	
	@Test
	public void testEjecutarAccionMarcarComoLeido() {
		// Accion a testear
		MarcarComoLeido accion = new MarcarComoLeido();
		
		accion.ejecutarAccion(this.mail);
		verify(this.mail).setLeido(true);
	}
}
