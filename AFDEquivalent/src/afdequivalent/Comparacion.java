package afdequivalent;

public class Comparacion {
    protected String nombreColumna;
    protected final Node nodos [] = new Node[2];
    
    public Comparacion(String nombreColumna,Node estado1, Node estado2){
        this.nombreColumna = nombreColumna;
        nodos[0] = estado1;
        nodos[1] = estado2;
    }
    
    public Node[] getEstadosColumna(String nombreColumna){
        return this.nodos;
    }

}
