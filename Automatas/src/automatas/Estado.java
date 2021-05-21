
package automatas;

import java.util.*;

public class Estado {
    String[] alfabeto;
    String nombre;
    boolean eFinal;
    boolean eInicial = false;
    ArrayList<Transicion> transiciones = new ArrayList();
    
    public Estado(){
        alfabeto = null;
        nombre = null;
        eFinal = false;
        eInicial =false;
    }
    
    public Estado(String[] a){
        alfabeto = a;
        eInicial = false;
    }
    
    public String obtenTransicion(int cam){        
        return transiciones.get(cam).objetivo;
    }

    public String funcionTransicion() {
        //Regresa las Transiciones del estado por todo el alfabeto
        String s = "";
        for (int i = 0; i < alfabeto.length; i++) {
            s += nombre + "   " +
            alfabeto[i] + "   " +
            obtenTransicion(i) + "\n";
        } return s;
    }
    
    public void setTransicion(int cambio, Estado eQ){
        //Cambia la transicion de este estado con cambio
        // y lo dirige hacia eQ
        Transicion t = new Transicion();
        t.cambio = alfabeto[cambio];
        t.objetivo = eQ.nombre;
        transiciones.set(cambio, t);
    }
}
class Transicion {
    String objetivo;
    String cambio;
}