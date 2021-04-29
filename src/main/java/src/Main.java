/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paascorb
 */
public class Main {
    
    public static void main(String args[]) {
        
        int matriz[][] = {{1,2,3},{4,5,-1},{-1,5,-1},{-1,-1,3},{6,7,-1},{-1,5,-1},{6,5,-1},{-1,8,-1},{-1,5,-1}};
    
        int tamAlfabeto = 3;
        
        int NumEstados=9;
        
        boolean finales[] = {false,true,true,true,false,true,false,true,false};
        
        String palabra = "acccbbaaba";
        
        Map<Integer,String> equivTokens = new HashMap<>();
        
        equivTokens.put(1, "cero");
        equivTokens.put(2, "uno");
        equivTokens.put(3, "cuatro");
        equivTokens.put(5, "tres");
        equivTokens.put(7, "tres");
        equivTokens.put(8, "dos");
        
        AutomataFinito A = new AutomataFinitoMatriz(NumEstados,tamAlfabeto,finales,matriz);
        
        AnalizadorLéxico escaner = new AnalizadorLéxico(transformar(palabra),A,equivTokens);
        
        
        escaner.finalizarAnálisis();
        
        System.out.println(escaner.getHistorico());
        
        
    }
    
    
    public static int[] transformar(String cadena){
        
         int[] resultado = new int[cadena.length()];
        for (int i = 0; i < cadena.length(); ++i) {
            // this converts a integer into a character
            resultado[i] =  (int) (cadena.charAt(i)-97);
        }
        
        return resultado;
    }
    
}