package automatas;

import java.util.ArrayList;


public class Automata {
    Estado inicial; 
    Estado[] estados; //contempla si es inicial y/o final
    String[] alfabeto;
    CuadroDialogo v = new CuadroDialogo();
    
    public Automata (){
        v.mensaje("Capture el automata", 1);
        estados = new Estado[v.capInt("Cantidad de estados")];
        String s = v.cap("Escriba el alfabeto separando por comas");
        alfabeto = s.split(",");
        v.mensaje("Comience a capturar por el estado inicial", 1);
        
        //El estado inicial también es el 0
        estados[0]=inicial = capturaEstado();
        inicial.eInicial=true; // se cambia a true en eInicial
        for(int i=1; i<estados.length; i++)            
            estados[i] = capturaEstado();
    }
    
    public Automata (String[] alf, Estado ini){
        alfabeto = alf;
        inicial = ini;
    }
    
    public Estado capturaEstado(){
        Estado e = new Estado(alfabeto);
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

    @Override
    public String toString(){//Definicion formal en String
        String c ="Automata Definicion formal\n\n";
        c += "S = " + inicial.nombre + "\n";
        String s = "\u03A3 = { ";//acumula elementos del alfabeto
        String k ="\n\nK = { "; // acumula nombres de estados
        String f ="\n\nF = { "; // acumula nombres de estados finales
        //Acumula renglones de la funcion de transicion por estados
        String t ="\n\nFuncion de transicion =\n";
        for (int i = 0; i < estados.length; i++) {
            t += estados[i].funcionTransicion() ;
            if(i==(estados.length-1))
                k += estados[i].nombre + " }";
            else 
                k += estados[i].nombre+", ";
            if(estados[i].eFinal)
                f += estados[i].nombre+", ";
        } f += " }";
        for (int i = 0; i < alfabeto.length; i++) {
            if(i==(alfabeto.length-1))
                s += alfabeto[i]+" }";
            else
                s += alfabeto[i]+", ";
        }
        c += s +  k +  f + t;//Concatena la defincion formal
        return c;
    }    
}