/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que representa el analizador léxico, que contiene los método necesarios para realizar el análisis léxico de una cadena
 * @author paascorb
 */
public class AnalizadorLéxico {
    
    private int[] cadena;
    private AutomataFinito automata;
    private int posActual;
    private Map<Integer,String> tokens;
    private List<Token> historico;
    
    /**
     * Constructor de AnalizadorLéxico que inicializa los atributos cadena, automata y tokens pasados por parametros, luego el historico y posActual lo inicializa con su valor inicial.
     * @param cadena Es el parámetro que representa através de un vector de enteros la cadena a analizar.
     * @param automata Es el automata finito que se utilizará para realizar el analisis léxico
     * @param tokens Es la tabla que para cada clave indica el valor del token.
     */
    public AnalizadorLéxico(int [] cadena, AutomataFinito automata,Map<Integer,String> tokens){
        
        this.cadena = cadena;
        this.automata = automata;
        this.tokens = tokens;
        
        this.posActual = 0;
        this.historico = new ArrayList<Token>();
        
    }
    
    /**
     * La joya de la corona, recorre la cadena en busca del token con lexema más largo, hasta que o se termina la cadena o recibe una transición no válida.
     * @return devuelve el siguiente token de la cadena del analizador léxico
     * @throws ExcepcionDeTransicion Es la excepcion que salta si ha sucedido algún problema analizando la cadena en busca de tokens.
     */
    public Token nextToken()throws ExcepcionDeTransicion{
        
        int pos = posActual;
        int estadoInicial = 0;
        int ultimoFinal = -1;
        int posFinal = -1;
        Token resultado;
        
        while(pos < this.cadena.length && estadoInicial != -1){
            
            estadoInicial = automata.transicion(cadena[pos], estadoInicial);
            
            if(estadoInicial != -1 && automata.esEstadoFinal(estadoInicial)){
                ultimoFinal = estadoInicial;
                posFinal = pos;
            }
            
            ++pos;
            
        }
        
        if(ultimoFinal == -1)
            throw new ExcepcionDeTransicion();
        else{
            
            String id = this.tokens.get(ultimoFinal);
            int[] lexema = new int[(posFinal-posActual)+1];
            int cont = 0;
            for (int i = posActual; i<=posFinal; ++i){
                lexema[cont] = cadena[i];
                ++cont;
            }
            this.posActual=posFinal+1;
            resultado = new Token(id,lexema);
            
        }
        
        return resultado;
        
    }
    
    /**
     * Metodo que comprueba si en la cadena restante desde la posición posActual quedan más tokens
     * @return Devuelve el booleano correspondiente a si existen más tokens o en caso contrario ya no se pueden obtener más.
     */
    public boolean hasMoreTokens(){
        
        int pos = posActual;
        int estadoInicial = 0;
        int ultimoFinal = -1;
        int posFinal = -1;
        Token resultado;
        
        while(pos < this.cadena.length && estadoInicial != -1){
            
            estadoInicial = automata.transicion(cadena[pos], estadoInicial);
            
            if(estadoInicial != -1 && automata.esEstadoFinal(estadoInicial)){
                ultimoFinal = estadoInicial;
                posFinal = pos;
            }
            
            ++pos;
            
        }
        
        return (ultimoFinal == -1 ? false : true);
        
    }
    
    /**
     * Devuelve el historico del analizador del lenguaje.
     * @return Lista de tokens que representa el historico del lenguaje.
     */
    public List<Token> getHistorico(){
        
        return this.historico;
    } 
    
    /**
     * Reinicia el analisis reiniciando el historico y la posición desde la que se analiza.
     */
    public void reiniciar(){
        
        this.posActual = 0;
        this.historico.clear();
        
    }
    
    /**
     * Reinicia el analizador y actualiza la cadena por la nueva que es pasada por parámetro.
     * @param cadena Representa la nueva cadena a analizar por el analizador.
     */
    public void setCadena(int[] cadena){
        
        this.reiniciar();
        
        this.cadena = cadena;
        
    }
    
    /**
     * Método que finaliza directamente el análisis, comprobando que se puedan generar más tokens y si es asi generarlos.
     */
    public void finalizarAnálisis(){
        
        try{
            while(this.hasMoreTokens()){
                this.historico.add(this.nextToken());
            }
        } catch (ExcepcionDeTransicion ex) {
            Logger.getLogger(AnalizadorLéxico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
