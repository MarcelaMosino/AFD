
package automatas;

public class Main2 {
    CuadroDialogo v = new CuadroDialogo();
    
    public void inicio(){
        v.mensaje("El programa simplifica un Automata"
                + " Finito Determinista");
    }
    
    public static void main(String[] args){
        Simplificacion s;
        Main2 m = new Main2();
        
        m.inicio();
        s = new Simplificacion(new Automata());
        s.simplificaAutomata();
    }
}
