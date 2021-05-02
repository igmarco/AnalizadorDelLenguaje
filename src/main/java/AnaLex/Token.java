package AnaLex;

/**
 *Clase token que define el par identificador de token y lexema asociado a dicho identificador.
 * @author paascorb
 */

/**
* La clase Token representa una dupla token-lexema de un analizador léxico.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/
public class Token {
    
    private String idToken;
    private int[] lexema;
    
    /**
     * Constructor de la clase Token, recibe el identificador del token y el lexema asociado.
     * @param idTok Representa el nombre o identificador del Token.
     * @param lexema Representa el lexema del token, la cadena de letras que lo conforman.
     */
    public Token(String idTok, int[] lexema){
        
        this.idToken=idTok;
        this.lexema=lexema;
        
    }
    
    /**
     * Método que devuelve el nombre o id del Token.
     * @return Nombre o id del Token.
     */
    public String getId(){
        return this.idToken;
    }
    
    /**
     * Método que devuelve el lexema del token, la cadena de letras que lo conforman.
     * @return Lexema del token, la cadena de letras que lo conforman.
     */
    public int [] getLexema(){
        return this.lexema;
    }
    
    /**
     * Redefinición del método toString para Token.
     * @deprecated
     * @return Devuelve una cadena donde se documenta el identificador del token y su lexema asociado.
     */
    public String toString(){
        String resultado = "Token:"+'\n';
        resultado += "-  Identificado: "+this.idToken+'\n';
        resultado += "-  Lexema: "+transformarLexema(this.lexema)+'\n';
        return resultado;
    }
    
    /**
     * Método que transforma el vector de enteros en el que está representado el lexema a una cadena, tranformando los enteros a sus correspondientes caracteres en minúsculas [a-z].
     * @deprecated
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
