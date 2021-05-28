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
public class Main1 {

	/**
    * Método main de la clase Main1.
    * @param args Argumentos del main. En este caso, ninguna entrada.
    */
	public static void main(String[] args) {
		
		int tamAlfabeto = 2;
		int NumEstados=7;
		
		int matriz[][] =    {{1, 2}, {3, 4}, {-1, -1}, {3, 2}, {5, 6}, {5, -1}, {-1, 6}};
		boolean finales[] = {false,   true,   true,     false,  true,   true,    true};
		
		String palabra="aaaaaaaaaaababbbabaabaaaabbabaaaabbbabababaaabbbaaaaababaaababa";
		
		Map<Integer, String> equivTokens = new HashMap<>();
		equivTokens.put(1, "dos");
		equivTokens.put(2, "cuatro");
		equivTokens.put(4, "uno");
		equivTokens.put(5, "tres");
		equivTokens.put(6, "dos");
		
		AutomataFinito A = new AutomataFinitoMatriz(NumEstados, tamAlfabeto, finales, matriz);
		
		AnalizadorLexico escaner = new AnalizadorLexico(Tools.codificadorLetrasEnteros(palabra), A, equivTokens);
		
		escaner.finalizarAnalisis();
		
		Tools.printTokensConLetras(escaner.getHistorico());
		
	}

}
