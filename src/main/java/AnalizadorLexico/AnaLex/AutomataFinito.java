package AnalizadorLexico.AnaLex;

import java.util.ArrayList;
import java.util.List;

/**
* La clase AutomataFinito representa un autómata finito (reconocedor de un lenguaje de tipo L3) abstracto. Permite representar libremente las transiciones del mismo.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/
public abstract class AutomataFinito {
    
    private int numEstados;
    private boolean[] finales;
    private int tamAlfabeto;
	private int estActual;
	
	/**
    * Constructor de AutomataFinito. No incluye la especificación de estados finales.
    * @param num Representa el número de estados que compondrán el autómata finito.
    * @param nAlfabeto Representa el número de letras que compondrán el alfabeto.
    */
    public AutomataFinito (int num, int nAlfabeto){

    	this.numEstados = num;
		this.finales = new boolean[num];
		
		for(int i = 0; i < num; i++) this.finales[i] = false;
		
		this.tamAlfabeto = nAlfabeto;
		this.estActual = 0;
    	
    }
    
    /**
	* Constructor de AutomataFinito. Contempla la información de estados finales mediante un array de booleanos.
	* @param num Representa el número de estados que compondrán el autómata.
    * @param nAlfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Indica los estados finales. El tamaño de "finales" es igual a "num", y (finales[i] implies estado "i" es final).
	*/
    public AutomataFinito (int num, int nAlfabeto, boolean [] finales){

    	this.numEstados = num;
		this.finales = finales;
		this.tamAlfabeto = nAlfabeto;
		this.estActual = 0;
    	
    }
    
    /**
	* Constructor de AutomataFinito. Contempla la información de estados finales mediante una lista de enteros.
	* @param num Representa el número de estados que compondrán el autómata.
    * @param nAlfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Representa la lista de estados que son finales.
	*/
	public AutomataFinito(int num, int nAlfabeto, List<Integer> finales) {
		
		this.numEstados = num;
		this.finales = new boolean[num];
		
		for(int i = 0; i < num; i++) this.finales[i] = (finales.contains(i) ? true : false);
		
		this.tamAlfabeto = nAlfabeto;
		this.estActual = 0;
		
	}
    
    /**
     * Método que devuelve el estado resultante de aplicar una transición dada a un estado dados.
     * En caso de no existir dicha transición, devuelve "-1".
     * @param estado Representa el estado de partida.
     * @param letra Representa la transición a aplicar.
     * @return Estado al que se llega tras aplicar la transición ("-1" en caso de no existir).
     */
 	public abstract int getEstadoTransicion(int estado, int letra);
 	
 	/**
     * Método que aplica al autómata la transición dada en caso de ser válida.
     * @param letra Representa la transición a aplicar.
     * @return Devuelve true en caso de haber sido posible la transición y false en caso contrario.
     * @see AutomataFinito#getEstadoTransicion(int, int)
     */
 	public boolean aplicarTransicion(int letra) {
 		
 		
 		int estado = this.getEstadoTransicion(this.estActual, letra);
 		
 		if(estado == -1) return false;
 		else {
 			this.estActual = estado;
 			return true;
 		}
 		
 	}
 	
 	/**
     * Método que devuelve el estado resultante de aplicar una serie de transiciones dadas a un estado dados.
     * En caso de haber una transición no posible, el método devuelve el último estado.
     * @param estado Representa el estado de partida.
     * @param cadena Representa la cadena de transiciones a aplicar.
     * @return Estado al que se llega tras aplicar la serie de transiciones.
     */
 	public int getCierreTransicion(int estado, int[] cadena) {
 		
 		for(int i = 0; i < cadena.length; i++) {
 			
 			if(estado != 1) estado = this.getEstadoTransicion(estado, cadena[i]);
 			else return estado;
 			
 		}
 		
 		return estado;
 		
 	}
 	
 	/**
     * Método que aplica al autómata la lista de transiciones dada.
     * En caso de haber una transición no posible, el autómata se queda en el último estado alcanzado.
     * @param cadena Representa la serie de transiciones a aplicar.
     * @return Devuelve true en caso de haber sido todas las transiciones posibles y false en caso contrario.
     * @see AutomataFinito#getCierreTransicion(int, int[])
     */
 	public boolean aplicarCierreTransicion(int[] cadena) {
 		
 		for(int i = 0; i < cadena.length; i++) if(!this.aplicarTransicion(cadena[i])) return false;
 		
 		return true;
 		
 		// Otra forma:
 		// this.estActual = this.getCierreTransicion(this.estActual,cadena);
 		
 	}
 	
 	/**
     * Método que indica si una palabra pertenece al lenguaje definido por el autómata finito.
     * @param palabra Representa la palabra a evaluar. Será consumida por el autómata para comprobar si pertenece o no al lenguaje.
     * @return True en caso de que la palabra pertenezca al lenguaje definido por el autómata finito. False en caso contrario.
     */
 	public boolean perteneceLenguaje(int[] palabra) {
 		
 		this.aplicarCierreTransicion(palabra);
 		
 		return (this.esEstadoFinal(this.estActual));
 		
 	}
 	
 	/**
     * Método que devuelve las condiciones del autómata a las originales.
     * Reinicia el indicador del estado actual.
     */
 	public void reset() {
 		
 		this.estActual = 0;
 		
 	}
    
    /**
     * Marca como final el estado indicado.
     * @param estado Estado final.
     */
    public void marcarFinal(int estado){
        
        this.finales[estado] = true;
        
    }
    
    /**
     * Método que indica los estados finales del autómata mediante un array de booleanos.
     * @param estadosFinales Indica los estados finales. El tamaño de "estadosFinales" es igual a "num", y (finales[i] implies estado "i" es final).
     * @see AutomataFinito#marcarFinal(int)
     */
    public void setFinales(boolean[] estadosFinales){
        
        this.finales=estadosFinales;
        
    }
    
    /**
     * Método que indica los estados finales del autómata mediante una lista de estados finales.
     * @param estadosFinales Indica los estados finales.
     * @see AutomataFinito#marcarFinal(int)
     */
 	public void setFinales(List<Integer> estadosFinales) {
 		
 		for(int i = 0; i < this.numEstados; i++) this.finales[i] = (estadosFinales.contains(i) ? true : false);
 		
 	}
 	
    /**
     * Método que devuelve el número de estados del autómata.
     * @return Número de estados del autómata.
     */
    public int getNumEstados(){
        
        return this.numEstados;
        
    }
    
    /**
     * Método que devuelve el estado actual.
     * @return Estado actual.
     */
 	public int getEstActual() {
 		
 		return this.estActual;
 		
 	}
 	
 	/**
     * Método que devuelve el número de letras que conforman el alfabeto del autómata.
     * @return Número de estados del autómata.
     */
 	public int getTamAlfabeto() {
 		
 		return this.tamAlfabeto;
 		
 	}
 	
 	/**
     * Método que devuelve el vector de booleanos que indica los estados finales y no finales del autómata.
     * @return Indica los estados finales. El tamaño de la lista "return" es igual a "num", y (return[i] implies estado "i" es final).
     */
 	public boolean[] getFinalesBooleanList() {
 		
 		return this.finales;
 		
 	}
    
 	/**
	* Método que devuelve una lista con los estados finales.
	* @return Indica los estados finales.
	*/
	public List<Integer> getFinalesIntegerList() {
		
		List<Integer> finales = new ArrayList<Integer>();
		
		for(int i = 0; i < this.numEstados; i++) if(this.finales[i]) finales.add(i);
		
		return finales;
		
	}
    
    /**
     * Método que indica si un estado es final.
     * @param estado Representa el estado del que determinar si es o no final.
     * @return True en caso de que sea final. False en caso contrario.
     */
    public boolean esEstadoFinal(int estado){
        
        return this.finales[estado];
        
    }
    
}
