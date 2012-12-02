package calendario.periodicidades;

import java.util.Calendar;

public  abstract class  Periodicidad {

	/**
	 * 
	 * @param actual
	 * @param inicio
	 * @param fin
	 * @return boolean
	 */
     public boolean tengoQueEjecutarEvento(Calendar actual,Calendar inicio,Calendar fin){
    	 
		return tengoQueEjecutarEvento(actual,inicio,fin,
				new TiempoAntelacion(0,0,0,0,0));
	 }
     
     /**
      * compruaba si la fecha actual estaEntre inicio(-ta) y fin(-ta)
      * y cumple la condicion chequear,que es especifica en cada subcales
      * @param actual
      * @param inicio
      * @param fin
      * @param ta
      * @return boolean
      */
     public  boolean tengoQueEjecutarEvento(Calendar actual,Calendar inicio, Calendar fin,TiempoAntelacion ta){
    	Calendar inicioMenosAntelacion=restarAntelacion(inicio,ta);
 		Calendar finMenosAntelacion=restarAntelacion(fin,ta);
 		return estaEntre(actual,inicioMenosAntelacion,finMenosAntelacion)
 		                && chequearTiempo(actual,inicio,ta) && chequearSegunPeriodicidad(actual,inicio,ta);
     }
     
     /**
      * chequea so las horas y minutos son iguales a la de la fecha actual mas la
      * antelacion
      * @param actual
      * @param inicio
      * @param ta
      * @return boolean
      */
     public boolean chequearTiempo(Calendar actual,Calendar inicio,TiempoAntelacion ta){
    	 Calendar actualMasAntelacion=sumarAntelacion(actual,ta);
    	 return actualMasAntelacion.get(Calendar.HOUR)==inicio.get(Calendar.HOUR)
		    &&actualMasAntelacion.get(Calendar.MINUTE)==inicio.get(Calendar.MINUTE);
     }
     
     /**
      * resta a la fecha fa la fecha de antelacion
      * 
      * @param fa
      * @param ta
      * @return
      */
     public Calendar restarAntelacion(Calendar fa,TiempoAntelacion ta){
    	    Calendar faux=(Calendar)fa.clone();
	     
    	    faux.add(Calendar.YEAR,-ta.getAnio());
    	    faux.add(Calendar.MONTH,-ta.getMes());
    	    faux.add(Calendar.DATE,-ta.getDia());
    	    faux.add(Calendar.HOUR,-ta.getHora());
    	    faux.add(Calendar.MINUTE,-ta.getMinuto());
	        return faux;
     }
     
     /**
      * suma a la fecha fa la fecha de antelacion
      * 
      * @param fa
      * @param ta
      * @return una nueva fecha resultante
      */
     public Calendar sumarAntelacion(Calendar fa,TiempoAntelacion ta){
 	    Calendar faux=(Calendar)fa.clone();
	     
 	    faux.add(Calendar.YEAR,ta.getAnio());
 	    faux.add(Calendar.MONTH,ta.getMes());
 	    faux.add(Calendar.DATE,ta.getDia());
 	    faux.add(Calendar.HOUR,ta.getHora());
 	    faux.add(Calendar.MINUTE,ta.getMinuto());
	    return faux;
     }
	    
     /**
      * chequea si la fecha es igual a la fecha primero o 
      * despues y esta antes que la fecha segundo o igual
      * @param entre
      * @param primero
      * @param segundo
      * @return boolean
      */
	    public boolean estaEntre(Calendar entre,Calendar primero,Calendar segundo){
	    	boolean estaAntes=primero.before(entre)|| iguales(primero,entre);
			boolean estaDespues=segundo.after(entre)|| iguales(segundo,entre); 
            
	    	return estaAntes && estaDespues;
	    }
	    /**
	     * calcula si la fecha actualya paso a la fechaFin sin utilizar
	     * el tiempo de antelacion
	     * @param actual
	     * @param fin
	     * @return boolean
	     */
	    public boolean termino(Calendar actual,Calendar fin){
	    	TiempoAntelacion ta=new TiempoAntelacion(0,0,0,0,0);
	    	return termino(actual,fin,ta);
	    }
	    
	    /**
	     * calcula si la fecha actualya paso a la fechaFin utilizando
	     * el tiempo de antalacion
	     * @param actual
	     * @param fin
	     * @param ta
	     * @return boolean
	     */
	    public boolean termino(Calendar actual,Calendar fin,TiempoAntelacion ta){
	    	Calendar finMenosAntelacion=restarAntelacion(fin,ta);
	    	return finMenosAntelacion.before(actual)|| iguales(finMenosAntelacion,actual);
	    }
	    
	    /**
	     * chequea si las fecha ca y cb son iguales,sin tener en cuenta
	     * los segundos
	     * @param ca
	     * @param cb
	     * @return boolean
	     */
	    public boolean iguales(Calendar ca,Calendar cb){
	    	return ca.get(Calendar.YEAR) == cb.get(Calendar.YEAR) &&
	    	       ca.get(Calendar.MONTH) == cb.get(Calendar.MONTH) &&
	    	       ca.get(Calendar.DATE) == cb.get(Calendar.DATE)&&
	    	       ca.get(Calendar.HOUR) == cb.get(Calendar.HOUR)&&
	    	       ca.get(Calendar.MINUTE) == cb.get(Calendar.MINUTE);
	    }
	    
	    /**
	     * este metodo es implementado segun la periodicidad(subclases)
	     * @param actual
	     * @param inicio
	     * @param ta
	     * @return boolean
	     */
	    public abstract boolean chequearSegunPeriodicidad(Calendar actual,Calendar inicio,TiempoAntelacion ta);
}
