package AnalizadorLexico.AnaLex;

import java.util.List;

/**
* La clase AutomataFinitoMatriz representa un autómata finito cuya implementación de las transiciones se modeliza mediante la representación en matriz del grafo de estados. Extiende la clase abstracta AutomataFinito.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
* @see AutomataFinito
*/
public class AutomataFinitoMatriz extends AutomataFinito{

    private int[][] matriz;
    
    /**
     * Constructor de AutomataFinitoMatriz. No incluye la especificación de estados finales.
     * No agrega la matriz de transiciones.
     * @param num Representa el número de estados que compondrán el AutomataFinito.
     * @param alfabeto Representa el número de letras que compondrán el alfabeto.
     */
 	public AutomataFinitoMatriz(int num, int alfabeto) {
 		
 		super(num, alfabeto);
 		
 	}
 	
 	/**
	* Constructor de AutomataFinitoMatriz. Contempla la información de estados finales mediante un array de booleanos.
	* No agrega la matriz de transiciones.
	* @param num Representa el número de estados que compondrán el AutomataFinito.
    * @param alfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Indica los estados finales. El tamaño de "finales" es igual a "num", y (finales[i] implies estado "i" es final).
	*/
	public AutomataFinitoMatriz(int num, int alfabeto, boolean[] finales) {
		
		super(num, alfabeto, finales);
		
	}
	
	/**
	* Constructor de AutomataFinitoMatriz. Contempla la información de estados finales mediante una lista de enteros.
	* @param num Representa el número de estados que compondrán el AutomataFinito.
    * @param alfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Representa la lista de estados que son finales.
    * @param matriz Representa la matriz de transiciones (filas = estados, columnas = letras).
	*/
	public AutomataFinitoMatriz(int num, int alfabeto, boolean[] finales, int[][] matriz) {
		super(num, alfabeto, finales);
		
		this.matriz = matriz;
		
	}
	
	/**
	* Constructor de AutomataFinito. Contempla la información de estados finales mediante una lista de enteros.
	* No agrega la matriz de transiciones.
	* @param num Representa el número de estados que compondrán el AutomataFinito.
    * @param alfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Representa la lista de estados que son finales.
	*/
	public AutomataFinitoMatriz(int num, int alfabeto, List<Integer> finales) {
		super(num, alfabeto, finales);
		
	}
	
	/**
	* Constructor de AutomataFinito. Contempla la información de estados finales mediante una lista de enteros.
	* @param num Representa el número de estados que compondrán el AutomataFinito.
    * @param alfabeto Representa el número de letras que compondrán el alfabeto.
    * @param finales Representa la lista de estados que son finales.
    * @param matriz Representa la matriz de transiciones (filas = estados, columnas = letras).
	*/
	public AutomataFinitoMatriz(int num, int alfabeto, List<Integer> finales, int[][] matriz) {
		super(num, alfabeto, finales);
		
		this.matriz = matriz;
		
	}
 	
	/**
    * Método que devuelve el estado resultante de aplicar una transición dada a un estado dados.
    * @param estado Representa el estado de partida.
    * @param letra Representa la transición a aplicar.
    * @return Estado al que se llega tras aplicar la transición.
    * @see AutomataFinito#getEstadoTransicion(int, int)
    */
	@Override
	public int getEstadoTransicion(int estado, int letra) {
		
		return this.matriz[estado][letra];
		
	}
    
}
