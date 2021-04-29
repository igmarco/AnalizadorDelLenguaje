/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *Esta clase define el concepto de automata reconocedor de un lenguaje L3, es decir, un autómata finito.
 * @author paascorb
 */
public abstract class AutomataFinito {
    
    private int numEstados;
    private boolean[] finales;
    private int tamAlfabeto;
     
    /**
     * Constructor del automata finito, sin estados finales.
     * @param num El parámetro num define el numero de estados del autómata
     * @param nAlfabeto El parámetro nAlfabeto define el número de caracteres del alfabeto
     */
    public AutomataFinito (int num, int nAlfabeto){
        this.numEstados=num;
        this.tamAlfabeto=nAlfabeto;
    }
    
    /**
     * Constructor del automata finito, con estados finales.
     * @param num El parámetro num define el numero de estados del autómata
     * @param nAlfabeto El parámetro nAlfabeto define el número de caracteres del alfabeto
     * @param finales El parárametro finales define un vector de booleanos donde cada posición refiere a un estado y si es true es porque ese estado será final, false en caso contrario.
     */
    public AutomataFinito (int num, int nAlfabeto, boolean [] finales){
        this.numEstados=num;
        this.tamAlfabeto=nAlfabeto;
        this.finales=finales;        
    }
    
    /**
     * Metodo transicion que devuelve el estado al que transicionará estando en el estado pasado por parámetro y recibiendo la letra pasada por parametros.
     * @param estado El parámetro estado define el estado en el que se encuentra el autómata
     * @param letra El parámetro letra define la letra que consume el autómata y con la cual transicionará
     * @return Devolverá un entero que refiere al estado al que transicionará.
     */
    public abstract int transicion (int estado, int letra);
    
    /**
     * Metodo transicion que fija el estado en el que se encuentra el autómata al que transicionará recibiendo la letra pasada por parametros y estando en el estado en el que se encuentre el autómata.
     * @param letra El parámetro letra define la letra que consume el autómata y con la cual transicionará
     */
    public abstract void transicion (int letra);
    
    /**
     * Metodo cierre que devuelve el estado en el que se encontrará el autómata cuando consuma toda la cadena desde el estado pasado por parametros.
     * @param estado El parámetro estado define el estado en el que se encontrará el autómata
     * @param cadena El parámetro cadena define la cadena a consumir por el autómata para realizar el cierre.
     * @return  Devuelve un entero que refiere al estado en el que se encontrará al consumir la cadena.
     */
    public int cierreTransicion (int estado, int cadena []){
        
        //cierre se refiere al estado donde el autómata se encuentra según va consumiendo la cadena.
        int cierre = estado;
        
        for (int i=0; i<cadena.length; ++i){
            cierre = this.transicion(cierre, cadena[i]);
        }
        
        return cierre;
    }
    
    /**
     * Metodo perteneLenguaje devuelve si la cadena pasada por parámetros al ser consumida el estado en el que se encuentra el autómata es final.
     * @param cadena El parámetro cadena define la cadena que debe consumir el autómata para comprobar si pertenece o no al lenguaje.
     * @return Devuelve el booleano que será true si la cadena pertence al lenguaje o false en caso contrario.
     */
    public boolean perteneceLenguaje(int cadena[]){
        
        return this.finales[cierreTransicion(0,cadena)];
        
    }
    
}
