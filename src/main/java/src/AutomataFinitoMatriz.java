/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *Particularización del automata finito usando una matriz para definir sus transiciones.
 * @author paascorb
 */
public class AutomataFinitoMatriz extends AutomataFinito{

    private int[][] matriz;
    
    /**
     * Constructor del automata finito matriz, sin estados finales.
     * @param num El parámetro num define el numero de estados del autómata
     * @param nAlfabeto El parámetro nAlfabeto define el número de caracteres del alfabeto
     * @param matriz Es la matriz que define las transiciones del autómata.
     */
    public AutomataFinitoMatriz(int num, int nAlfabeto,int[][] matriz) {
        super(num, nAlfabeto);
        this.matriz=matriz;
    }

    /**
     * Constructor del automata finito matriz, con estados finales.
     * @param num El parámetro num define el numero de estados del autómata
     * @param nAlfabeto El parámetro nAlfabeto define el número de caracteres del alfabeto
     * @param finales El parárametro finales define un vector de booleanos donde cada posición refiere a un estado y si es true es porque ese estado será final, false en caso contrario.
     * @param matriz Es la matriz que define las transiciones del autómata.
     */
    public AutomataFinitoMatriz (int num, int nAlfabeto, boolean [] finales,int[][] matriz){
        super(num, nAlfabeto,finales);
        this.matriz=matriz;
    }
    
    /**
     * Metodo de transicion del automata finito matriz, devulve el estado al que transicionará el autómata con el estado y letra pasados por parametros.
     * @param estado El parámetro estado define el estado en el que se encuentra el autómata
     * @param letra El parámetro letra define la letra que consume el autómata y con la cual transicionará
     * @return Devolverá un entero que refiere al estado al que transicionará.
     */
    @Override
    public int transicion(int estado, int letra) {
            return this.matriz[estado][letra];
    }
    
    /**
     * En este caso este metodo no queda redefinido porque no nos interesa.
     */
    public void transicion(int letra){}
    
}
