/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *Clase toquen que define el par identificador de token y lexema asociado a dicho identificador.
 * @author paascorb
 */
public class Token {
    
    private String idToken;
    private int[] lexema;
    
    /**
     * Constructor de la clase token, recibe el identificador del token y el lexema asociado.
     * @param idTok El parámetro idTok define el identificador del token
     * @param lexema El parámetro lexema refiere el lexam asociado al token.
     */
    public Token(String idTok, int[] lexema){
        
        this.idToken=idTok;
        this.lexema=lexema;
        
    }
    
    /**
     * Es el método get del atributo idToken.
     * @return devuelve el atributo idToken.
     */
    public String getId(){
        return this.idToken;
    }
    
    /**
     * Es el metodo get del atributo lexema
     * @return devuelve el atributo lexema. 
     */
    public int [] getLexema(){
        return this.lexema;
    }
    
}
