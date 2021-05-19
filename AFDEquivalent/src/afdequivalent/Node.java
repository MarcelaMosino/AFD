package afdequivalent;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Node {

    protected String nombre;
    protected boolean inicial, fina;
    protected Node apuntado;
    protected Map<String, String> transiciones;

    public Node(String name, boolean i, boolean f) {
        this.nombre = name;
        this.inicial = i;
        this.fina = f;
        this.transiciones = new HashMap();
    }

    public void llenarTransiciones(String alfabeto) {
        for (String simbolo : alfabeto.split(",")) {
            transiciones.put(simbolo, JOptionPane.showInputDialog(
                    "La transicion de " + this.nombre + " cuando es (" + simbolo + ") va hacia:"));
        }
    }
    
    public String getTransicion(String simbolo){
        return this.transiciones.get(simbolo);
    }
}
