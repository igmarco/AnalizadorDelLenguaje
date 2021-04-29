/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *Clase token que define el par identificador de token y lexema asociado a dicho identificador.
 * @author paascorb
 */
public class Token {
    
    private String idToken;
    private int[] lexema;
    
    /**
     * Constructor de la clase token, recibe el identificador del token y el lexema asociado.
     * @param idTok El parámetro idTok define el identificador del token
     * @param lexema El parámetro lexema refiere el lexema asociado al token.
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
     * Es el método get del atributo lexema
     * @return devuelve el atributo lexema. 
     */
    public int [] getLexema(){
        return this.lexema;
    }
    
    /**
     * Redefinición del metodo toString para Token.
     * @return Devuelve una cadena donde se documenta el identificador del token y su lexema asociado.
     */
    public String toString(){
        String resultado = "Token:"+'\n';
        resultado += "-  Identificado: "+this.idToken+'\n';
        resultado += "-  Lexema: "+transformarLexema(this.lexema)+'\n';
        return resultado;
    }
    
    /**
     * Método que transforma el vector de enteros en el que está representado el lexema a una cadena tranformando los enteros a sus correspondientes caracteres en minúsculas [a-z]
     * @param lexema Vector de enteros que reprenta al lexema.
     * @return  Cadena transformada a partir del vector de enteros.
     */
    public String transformarLexema(int [] lexema){
        
        String resultado = "";
        
        for(int i : lexema)
            resultado += Character.toString ((char) i+97);
        
        return resultado;
        
    }
    
}
