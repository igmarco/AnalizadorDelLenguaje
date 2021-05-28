package AnalizadorLexico.AnaLex;

import java.util.HashMap;
import java.util.Map;

/**
* Clase de prueba para el proyecto. Permite comprobar que tanto el autómata como el analizador léxico funcionan correctamente.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/
public class Main3 {
    
	/**
    * Método main de la clase Main1.
    * @param args Argumentos del main. En este caso, ninguna entrada.
    */
    public static void main(String args[]) {
        
    	int tamAlfabeto = 2;
        int NumEstados=3;
    	
        int matriz[][] = 	{{2,0}, {-1,1}, {0,-1}};
        boolean finales[] = {false,	 true,	 true};
        
        String palabra = "aaabbabbabaaabbbababaa";
        
        Map<Integer,String> equivTokens = new HashMap<>();
        equivTokens.put(2, "dos");
        equivTokens.put(3, "tres");
        
        AutomataFinito A = new AutomataFinitoMatriz(NumEstados, tamAlfabeto, finales, matriz);
        
        AnalizadorLexico escaner = new AnalizadorLexico(Tools.codificadorLetrasEnteros(palabra), A, equivTokens);
        
        escaner.finalizarAnalisis();
        
        Tools.printTokensConLetras(escaner.getHistorico());
        
        
    }
    
}