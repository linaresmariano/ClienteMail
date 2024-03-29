package directorio;

import java.util.LinkedList;
import java.util.List;

public class Carpeta extends DirectorioUsuario{

	private String nombre;
	private int profundidad=0;
	private List<DirectorioUsuario> hijos;
	
	
	public Carpeta(String nombre){
		this.nombre=nombre;
		this.hijos=new LinkedList<DirectorioUsuario>();
	}
	
	/*
	 * constructor que recibe 2 parametro,profundidad es un n umero que indica
	 * la profundidad en la estructura de directorios
	 */
	public Carpeta(String nombre,int profundidad){
		this.nombre=nombre;
		this.profundidad=profundidad;
		this.hijos=new LinkedList<DirectorioUsuario>();
	}
	
	
	public List<DirectorioUsuario> getHijos(){
		return hijos;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	
	public boolean suNombreEs(String nombre){
		return this.getNombre().equals(nombre);
	}
	
	/**
	 * Agreaga un mail al path que indica su etiqueta,va creando las carpetas que todavian no fueron
	 * creadas
	 * @param mail
	 */
	
	public void agregarMail(Mail mail){
		if(profundidad<mail.getEtiqueta().size()){
			if(yaFueCreado(mail.getEtiqueta().get(profundidad))){
				retornarCarpetaDeNombre(mail.getEtiqueta().get(profundidad)).agregarMail(mail);
			}else{
				Carpeta c=new Carpeta(mail.getEtiqueta().get(profundidad),profundidad+1);
				this.hijos.add(c);
				c.agregarMail(mail);
				
			}
		}else{
			this.hijos.add(mail);
		}
	}
	
	public boolean yaFueCreado(String nombrep){
		boolean res=false;
		for(DirectorioUsuario d:this.hijos){
			res=res || d.suNombreEs(nombrep);
		}
		return res;
	}
	
	
	/**
	 * Recorna la carpeta de nombre nombre  si existe,sino retorna null.
	 * @param nombre
	 * @return Carpeta
	 */
	
	public Carpeta retornarCarpetaDeNombre(String nombre){
		Carpeta c=null;
		for(DirectorioUsuario d:this.hijos){
			if(d.suNombreEs(nombre)){
				c=(Carpeta) d;
				break;
			}
			
		}
		return c;
	}
	
	public void limpiarDirectorio(){
        this.hijos.removeAll(this.hijos);
	}
	
	public void print(){
		for(int i=0;i<profundidad*2;i++){
			System.out.print(" ");
		}
		System.out.print("+");
		System.out.println(this.getNombre());
		for(DirectorioUsuario e:hijos){
			e.print();
		}
	}
	
	public void eliminarMail(Mail mail){
           if(mail.getEtiqueta().size()==profundidad){

                  hijos.remove(mail);
          }
          else{
                  retornarCarpetaDeNombre(mail.getEtiqueta().get(profundidad))
                         .eliminarMail(mail);
         }

       }


      //destino se recomienda que sea una LinkedList,
      //ademas despues de utilizar este metodo
     //destino no se debe utilizar,porque cambiaria
     //la etiqueta del mail
     public void moverA(Mail mail,List<String> destino){

        this.eliminarMail(mail);

        //agregando el nuevo destino
        mail.setEtiqueta(destino);
        this.agregarMail(mail);
     }
     
      //agrega una carpeta a los hijos
     public void agregarSubCarpeta(String nombre){
    	 this.getHijos().add(new Carpeta(nombre,profundidad+1));
     }
	
}
