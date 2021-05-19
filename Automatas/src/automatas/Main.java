
package automatas;

import java.util.Iterator;
import javax.swing.*;


public class Main {
    CuadroDialogo v = new CuadroDialogo();  
    
    public void inicio(){
       v.mensaje("El programa verifica la equivalencia de \n"
       +"dos automatas finitos deterministas",1);
    }     
    
    public void resultados(Comparacion c){
        String acumulado="";
        while(!c.renglones.estaVacia())
            acumulado +=c.renglones.despacha()+"\n";
        if(c.compatibles==true)
            acumulado+="\nNo se generaron nuevo pares para comparar y "
                    + "ninguno\nfue incompatible, por lo tanto...\n"
                    + " Son equivalentes";
        else
            acumulado+="\nNo son equivalentes";
        v.mensaje(acumulado, -1);
    }
    
    public static void main(String[] args){
        Main m = new Main();
        CuadroDialogo v = new CuadroDialogo();  
        Comparacion c;
        
        m.inicio();
        do{
            c = new Comparacion(new Automata(), new Automata());
            c.comparaAutomatas();
            m.resultados(c);
        }while(v.confirm("Repetir"));
    }
}
