package AnalizadorLexico.AnaLex;

import java.util.HashMap;
import java.util.Map;

/**
* Clase de prueba para el proyecto. Permite comprobar que tanto el autómata como el analizador léxico funcionan correctamente.
* Resolución de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/
public class Main2_Pr4 {
    
	/**
    * Método main de la clase Main2_Pr4.
    * Inicializa el analizador correspondiente a la práctica 4 y realiza el análisis de la cadena 'acccbbaaba'.
    * @param args Argumentos del main. En este caso, ninguna entrada.
    */
    public static void main(String args[]) {
        
    	int tamAlfabeto = 3;
        int NumEstados=9;
    	
        int matriz[][] = 	{{1,2,3}, {4,5,-1}, {-1,5,-1}, {-1,-1,3}, {6,7,-1}, {-1,5,-1}, {6,5,-1}, {-1,8,-1}, {-1,5,-1}};
        boolean finales[] = {false,	   true,	 true,	    true,	   false,	 true,	    false,    true,      true};
        
        String palabra = "acccbbaaba";
        
        Map<Integer,String> equivTokens = new HashMap<>();
        equivTokens.put(1, "cero");
        equivTokens.put(2, "uno");
        equivTokens.put(3, "cuatro");
        equivTokens.put(5, "tres");
        equivTokens.put(7, "tres");
        equivTokens.put(8, "dos");
        
        AutomataFinito A = new AutomataFinitoMatriz(NumEstados, tamAlfabeto, finales, matriz);
        
        AnalizadorLexico escaner = new AnalizadorLexico(Tools.codificadorLetrasEnteros(palabra), A, equivTokens);
        
        escaner.finalizarAnalisis();
        
        Tools.printTokensConLetras(escaner.getHistorico());
        
        
    }
    
}