package automatas;

import java.util.ArrayList;


public class Automata {
    Estado inicial; 
    Estado[] estados; //contempla si son finales
    String[] alfabeto;
    CuadroDialogo v = new CuadroDialogo();
    
    public Automata (){
        v.mensaje("Capture el automata", 1);
        estados = new Estado[v.capInt("Cantidad de estados")];
        String s = v.cap("Escriba el alfabeto separando por comas");
        alfabeto = s.split(",");
        v.mensaje("Comience a capturar por el estado inicial", 1);
        
        estados[0]=inicial = capturaEstado();
        for(int i=1; i<estados.length; i++)            
            estados[i] = capturaEstado();
    }
    
    public Estado capturaEstado(){
        Estado e = new Estado();
        e.nombre = v.cap("Nombre del estado siguiente");
        
        e.eFinal = v.confirm("¿El estado es final?");
        for(int i=0; i<alfabeto.length; i++){
            e.transiciones.add(capturaTransicion(alfabeto[i]));
        }
        return e;
    }
    
    public Transicion capturaTransicion(String s){
        Transicion t = new Transicion();
        
        t.cambio = s;
        t.objetivo = v.cap("Transicion con "+s);
        return t;
    }
    
    public Estado transCon(Estado origen, int cambio){
        String nomBuscar = origen.obtenTransicion(cambio);
        Estado e2 = estados[0];
        
        for(int i=0; i<estados.length; i++){
            e2 = estados[i];
            if(e2.nombre.equals(nomBuscar))
                break;
        }
        return e2;
    }
}
