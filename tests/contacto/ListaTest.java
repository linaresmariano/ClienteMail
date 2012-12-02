package contacto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class ListaTest {

	private Lista lista;
	private ContactoHoja cont1;
	private Lista cont2;
	
	@Before
	public void setUp() throws Exception {
		lista = new Lista("Amigos");
		cont1 = mock(ContactoHoja.class);
		cont2 = mock(Lista.class);
	}
	
	@Test
	public void testAddContacto() {
		
		// Agregar un contacto hoja
		lista.addContacto(cont1);
		assertTrue(lista.getContactos().contains(cont1));
		
		// Agregar un contacto lista
		lista.addContacto(cont2);
		assertTrue(lista.getContactos().contains(cont1));
		assertTrue(lista.getContactos().contains(cont2));
	}

	@Test
	public void testEliminarContacto() {
		
		lista.addContacto(cont1);
		lista.addContacto(cont2);
		
		// Eliminando al contacto 1
		lista.eliminarContacto(cont1);
		assertFalse(lista.getContactos().contains(cont1));
		
		// Eliminando al contacto 2		
		lista.eliminarContacto(cont2);
		assertFalse(lista.getContactos().contains(cont2));
	}

	
	@Test
	public void testContains() {
		// Agrego dos contactos
		lista.addContacto(cont1);
		lista.addContacto(cont2);
		
		// Verifico que el contains ande
		assertTrue(lista.contains(cont1));
		assertTrue(lista.contains(cont2));
		
		
		// Sacando los dos contactos
		lista.eliminarContacto(cont1);
		lista.eliminarContacto(cont2);
		
		// Verificar que eñ contains de false
		assertFalse(lista.contains(cont1));
		assertFalse(lista.contains(cont2));
	}
	
}
