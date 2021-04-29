/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *Esta clase define una excepción personalizada en el caso en el que una transición falle
 * @author paascorb
 */
public class ExcepcionDeTransicion extends Exception{
    
    /**
     * Constructor de la excepción mostrando un mensaje indicando que ha fallado la transición.
     */
    public ExcepcionDeTransicion(){
        super("ExcepcionDeTransicion: Error al transicionar al siguiente estado.");
    }
    
}
