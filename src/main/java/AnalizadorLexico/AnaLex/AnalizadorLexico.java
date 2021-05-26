package AnalizadorLexico.AnaLex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* La clase AnalizadorLexico representa un analizador léxico. Está compuesto por un autómata finito, un diccionario de tokens y la cadena que va a "tokenizar".
* Contiene los métodos necesarios para realizar el análisis léxico de una cadena.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AutomataFinito
* @see Token
*/
public class AnalizadorLexico {
    
    private int[] cadena;
    private AutomataFinito automata;
    private int posActual;
    private Map<Integer,String> tokens;
    private List<Token> historico;
    
    /**
	* Constructor de AnalizadorLexico. Contempla el autómata finito y la lista de duplas token-lexema. Contempla la cadena vacía.
	* @param automata Representa el autómata finito del analizador léxico.
    * @param tokens Representa la lista de duplas token-lexema.
    * @see AutomataFinito
    * @see Token
	*/
	public AnalizadorLexico(AutomataFinito automata, Map<Integer, String> tokens) {
		
		this.automata = automata;
		this.tokens = tokens;
		
		this.cadena = new int[0];
		
		this.posActual = 0;
		this.historico = new ArrayList<Token>();
		
	}
	
	/**
	* Constructor de AnalizadorLexico. Inicializa el autómata finito y la lista de duplas token-lexema, así como la cadena a analizar.
	* @param cadena Representa la cadena a analizar a través de un vector de enteros.
	* @param automata Representa el autómata finito del analizador léxico.
    * @param tokens Representa la lista de duplas token-lexema.
    * @see AutomataFinito
    * @see Token
	*/
	public AnalizadorLexico(int[] cadena, AutomataFinito automata, Map<Integer, String> tokens) {
		
		this.cadena = cadena;
		this.automata = automata;
		this.tokens = tokens;
		
		this.posActual = 0;
		this.historico = new ArrayList<Token>();
		
	}
	
	//-------------------------------------------------
	
	/**
    * Método que proporciona el siguiente token generado por la cadena.
    * Recorre la cadena desde el último estado para el que se ha generado un token, en busca del token con lexema más largo, hasta que se termina la cadena o recibe una transición no válida.
    * El indicador de la posición de la cadena avanza y registra el token generado en el histórico.
    * @return Token generado por la cadena. "null" en caso de no existir.
	* @throws ExcepcionDeTransicion En caso de no existir token, lanza una ExcepcionDeTransicion.
    * @see Token
    */
	public Token nextToken() throws ExcepcionDeTransicion {
		
		this.automata.reset();
		
		int posActualAuxiliar = this.posActual;
		int ultimoEstadoVisitado = 0;
		int ultimoFinalVisitado = -1;
		int ultimaPosicionCadenaFinal = this.posActual;
		boolean masTransiciones = true;
		
		while(this.cadena.length > posActualAuxiliar && masTransiciones) {
			
			if(automata.aplicarTransicion(cadena[posActualAuxiliar])) {
				
				ultimoEstadoVisitado = this.automata.getEstActual();
				posActualAuxiliar++;
				
				if(this.automata.esEstadoFinal(ultimoEstadoVisitado)) {
					
					ultimoFinalVisitado = ultimoEstadoVisitado;
					ultimaPosicionCadenaFinal = posActualAuxiliar;
					
				}
				
			}
			else {
				
				masTransiciones = false;
				
			}
			
		}
		
		if(ultimoFinalVisitado == -1){
			
			throw new ExcepcionDeTransicion();
			
		}
		else {
			
			int[] lexema = new int[ultimaPosicionCadenaFinal-this.posActual];
			for(int i = 0; i < ultimaPosicionCadenaFinal-this.posActual; i++)
				lexema[i] = this.cadena[this.posActual+i];
			
			Token token = new Token(this.tokens.get(ultimoFinalVisitado), lexema);
			
			this.posActual = ultimaPosicionCadenaFinal;
			this.historico.add(token);
			
			return token;
			
		}
		
	}
	
	/**
    * Método que indica si se puede extraer algún token más de la cadena.
    * @return True en caso de poder generar algún token más. False en caso contrario.
    * @see Token
    */
    public boolean hasMoreTokens(){
        
    	this.automata.reset();
		
		int posActualAuxiliar = this.posActual;
		
		while(this.cadena.length > posActualAuxiliar) {
			
			if(!this.automata.aplicarTransicion(cadena[posActualAuxiliar])) {
				
				return false;
				
			}
			
			if(this.automata.esEstadoFinal(this.automata.getEstActual())) {
				
				return true;
				
			}
			
			posActualAuxiliar++;
			
		}
		
		return false;
        
    }
    
    /**
     * Método que devuelve el histórico de tokens generado por el analizador.
     * @return Lista de tokens generados por el analizador para la cadena actual.
     * @see Token
     */
    public List<Token> getHistorico(){
        
        return this.historico;
    } 
    
    /**
     * Método que devuelve las condiciones del analizador a las originales (conservando la cadena).
     * Reinicia el indicador de la posición actual.
     * Limpia el histórico de tokens.
     */
    public void reiniciar(){
        
        this.posActual = 0;
        this.historico.clear();
        
    }
    
    /**
     * Método que sustituye la cadena por la informada y devuelve las condiciones del analizador a las originales.
     * @param cadena Nueva cadena del analizador léxico.
     */
    public void setCadena(int[] cadena){
        
        this.reiniciar();
        
        this.cadena = cadena;
        
    }
    
    /**
     * Método que devuelve la cadena asociada al Analizador Léxico
     * @return array de enteros que representa la cadena a reconocer por el analizador léxico.
     */
    public int[] getCadena() {
    	
    	return this.cadena;
    	
    }
    
    /**
     * Método que proporciona el resto de los tokens generados por la cadena.
     * @return Lista de tokens generados por el analizador para la cadena actual.
     * @see Token
     * @see AnalizadorLexico#nextToken()
     */
 	public List<Token> finalizarAnalisis() {
 		
 		List<Token> tokens = new ArrayList<Token>();
 		
 		try{
            
 			while(this.hasMoreTokens()) {
 	 			
 	 			tokens.add(this.nextToken());
 	 			
 	 		}
 			
        } catch (ExcepcionDeTransicion ex) {
            Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
 		
 		return tokens;
 		
 	}
 	
 	/**
 	 * Metodo get que devuelve el autómata del analizador léxico.
 	 * @return AutomataFinito es el autómata finito asociado al analizador.
 	 */
 	public AutomataFinito getAutomata() {
 		
 		return this.automata;
 		
 	}
 	
 	/**
 	 * Metodo get que devuelve los tokens del analizado léxico.
 	 * @return Map es la estructura donde se almacenan los tokens del analizador.
 	 */
 	public Map<Integer,String> getTokens() {
 		
 		return this.tokens;
 		
 	}
    
}
