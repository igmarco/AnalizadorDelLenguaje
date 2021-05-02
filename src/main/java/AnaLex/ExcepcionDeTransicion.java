package AnaLex;

/**
* La clase ExcepcionDeTransicion representa la excepción de fallo de una transición.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author paascorb
* @author igmarco
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
* @see AutomataFinito
*/
public class ExcepcionDeTransicion extends Exception{
    
	private static final long serialVersionUID = 1L;

	/**
     * Constructor de la excepción. Muestra un mensaje en el que se indca que la transición ha fallado.
     */
    public ExcepcionDeTransicion(){
        super("ExcepcionDeTransicion: Error al transicionar al siguiente estado.");
    }
    
}
