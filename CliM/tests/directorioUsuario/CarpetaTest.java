package directorioUsuario;

import static org.junit.Assert.*;

import java.util.LinkedList;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class CarpetaTest {

	Carpeta carpeta;
	LinkedList<String> etiqueta;
	@Mock Mail mail;
	
	@Before
	public void setUp(){
		carpeta=new Carpeta("Indice");
		etiqueta=new LinkedList<String>();
		mail=mock(Mail.class);
		
		when(mail.getEtiqueta()).thenReturn(etiqueta);
		
	}
	
	
	@Test
	public void agregarMailARecibidos() {
		etiqueta.add("Recibidos");
		
		//agregando mail a la carpeta Indice/Recibidos
		carpeta.agregarMail(mail);
		
		
		assertEquals("no se creo la carpeta Recibidos",carpeta.getHijos().get(0).getNombre(),"Recibidos");
		assertEquals("el mail,no se agrego a la carpeta recibidos",((Carpeta)carpeta.getHijos().get(0)).getHijos().get(0),mail);
	}
	
	@Test
	public void agregarMailARecibidosYaExistente() {
		etiqueta.add("Recibidos");
		
		//agregando mail a la carpeta Indice/Recibidos
		carpeta.agregarMail(mail);
		assertEquals(carpeta.getHijos().get(0).getNombre(),"Recibidos");
		
		//agregando otro mail a mail a la carpeta Indice/Recibidos
		carpeta.agregarMail(mail);
		assertEquals("se agrego mas de una o ninguna carpeta Recibidos",carpeta.getHijos().size(),1);
		assertEquals("no se agregaron los mail correctamente",((Carpeta)carpeta.getHijos().get(0)).getHijos().size(),2);
		
	}
	
	@Test
	public void agregarDosMailAFacultadMateriasObjetos() {
		etiqueta.add("Facultad");
		etiqueta.add("Materias");
		etiqueta.add("Objetos");
		
		//agregando mail a la carpeta Indice/Facultad/Materias/Objetos
		carpeta.agregarMail(mail);
		
		
		assertEquals(carpeta.getHijos().get(0).getNombre(),"Facultad");
		
		
		assertEquals(carpeta.getHijos().size(),1);
		
		//metiendome en la estructura para verificar la existencia de la carpeta Materia
		Carpeta cAux=(Carpeta) ((Carpeta)carpeta.getHijos().get(0)).getHijos().get(0);
		assertEquals("la carpeta Materias no se a creado ",cAux.getNombre(),"Materias");
		
		//metiendome en la estructura para verificar la existencia de la carpeta Objetos
		cAux=(Carpeta) cAux.getHijos().get(0);
		assertEquals("la carpeta Objetos no se a creado ",cAux.getNombre(),"Objetos");
		
		//metiendome en la estructura para verificar la existencia del mail en el directorio
		assertEquals("el mail no fue gregado al directoio",cAux.getHijos().get(0),mail);		
		
		
		carpeta.agregarMail(mail);
		
		assertEquals("no hay dos mail en el directorio Objetos",cAux.getHijos().size(),2);
	}
	
	
	@Test
	public void agregarTresDirectoriosAFacultad() {
		etiqueta.add("Facultad");
		etiqueta.add("Materias");
		
		//agregando mail a la carpeta Indice/Facultad/Materias
		carpeta.agregarMail(mail);
		
		etiqueta.removeLast();
		etiqueta.add("Objetos");
		
		//agregando mail a la carpeta Indice/Facultad/Objetos
		carpeta.agregarMail(mail);
		
		etiqueta.removeLast();
		etiqueta.add("Mate2");
		
		//agregando mail a la carpeta Indice/Facultad/Mate2
		carpeta.agregarMail(mail);
		
		
		assertEquals(carpeta.getHijos().size(),1);
		
		
		Carpeta c=(Carpeta)carpeta.getHijos().get(0);
		
		assertEquals("la cantidad de sub directorios de Fcultas no es 3",c.getHijos().size(),3);
		
		assertEquals(c.getNombre(),"Facultad");
		
		assertEquals("la carpeta Materias no se agrego ",c.getHijos().get(0).getNombre(),"Materias");
		assertEquals("la carpeta Objetos no se agrego ",c.getHijos().get(1).getNombre(),"Objetos");
		assertEquals("la carpeta Objetos no se Mate2 ",c.getHijos().get(2).getNombre(),"Mate2");
		
		
	}
	
	@Test
	public void yaFueCreadoConCarpetaCreada(){
		etiqueta.add("Facultad");
		
		carpeta.agregarMail(mail);
		assertTrue("la carpeta Facultads no se a creado correctamente",carpeta.yaFueCreado("Facultad"));
		
		etiqueta.remove();
		etiqueta.add("Recibidos");
		
		carpeta.agregarMail(mail);
		
		assertTrue("la carpeta Recibidos no se a creado correctamente",carpeta.yaFueCreado("Recibidos"));
		
	}
	
	@Test
	public void yaFueCreadoConCarpetaSinCrear(){
		
		assertFalse(carpeta.yaFueCreado("Facultad"));
		
		etiqueta.add("Recibidos");
		carpeta.agregarMail(mail);
		
		assertFalse("la carpeta UNQ puede que exista",carpeta.yaFueCreado("UNQ"));
		
	}
	
	@Test
	public void retornarCarpetaCreadaDeNombre(){
        etiqueta.add("Recibidos");
      //agregando mail a la carpeta Indice/Recibidosd
        carpeta.agregarMail(mail);
        
        etiqueta.removeFirst();
        etiqueta.add("Borrados");
        
      //agregando mail a la carpeta Indice/Borrados/Objetos
		carpeta.agregarMail(mail);
		
		assertEquals("no se retorno la carpeta de nombre Recibidos",carpeta.retornarCarpetaDeNombre("Recibidos").getNombre(),"Recibidos");
		assertEquals("no se retorno la carpeta de nombre Borrados",carpeta.retornarCarpetaDeNombre("Borrados").getNombre(),"Borrados");
	}
	
	@Test
	public void retornarCarpetaSinCrearDeNombre(){
		
		assertNull("no se retorno null",carpeta.retornarCarpetaDeNombre("Recibidos"));
		assertNull("no se retorno null",carpeta.retornarCarpetaDeNombre("Borrados"));
	}
	
	
	@Test
	public void limpiarDirectorio() {
		etiqueta.add("Recibidos");
		carpeta.agregarMail(mail);
		carpeta.agregarMail(mail);
		carpeta.limpiarDirectorio();
		assertEquals("no se borraron todos los sub directorios",carpeta.getHijos().size(),0);
	}
	
	@Test
	public void suNombreEs(){
		assertTrue("la carpeta no se llama Inbox",carpeta.suNombreEs("Indice"));
		
		etiqueta.add("Recibidos");
		carpeta.agregarMail(mail);
		
		assertTrue("la carpeta no se llama recibidos",carpeta.getHijos().get(0).suNombreEs("Recibidos"));
	}

}
