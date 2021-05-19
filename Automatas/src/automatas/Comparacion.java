package automatas;

import java.util.HashSet;


public class Comparacion {
    Cola<Estado> cola = new Cola();
    Cola<String> renglones = new Cola();
    HashSet<String> idTuplas = new HashSet();
    Automata a1, a2;
    boolean compatibles = true;
    
    public Comparacion(Automata a1, Automata a2){
        this.a1 = a1;
        this.a2 = a2;        
        
        renglones.encola(encabezado());
        cola.encola(a1.inicial);        
        cola.encola(a2.inicial);
        idTuplas.add(a1.inicial.nombre+a2.inicial.nombre);
    }
    
    public String encabezado(){
        String acumulado = "( M1, M2 )" + " \t ";
        for(int i=0; i<a1.alfabeto.length; i++){
            acumulado += "( M1->"+a1.alfabeto[i]+", M2->"+a1.alfabeto[i]+" )";
        }
        return acumulado+"\n";
    }

    public void comparaAutomatas() {
        Estado e1,e2;
        while(compatibles && !cola.estaVacia()){
            e1 = cola.despacha();
            e2 = cola.despacha();
            compara(e1,e2);
        }
    }

    public void verificaCompraciones(Estado e1, Estado e2) {
        if(verificaCompatibles(e1,e2)){
            if(!idTuplas.contains(e1.nombre+e2.nombre)){
                idTuplas.add(e1.nombre+e2.nombre);
                cola.encola(e1);
                cola.encola(e2);
            }                
        }else compatibles=false;
    }

    public boolean verificaCompatibles(Estado e1, Estado e2) {
        return e1.eFinal == e2.eFinal;
    }

    private void compara(Estado e1, Estado e2) {
        String acumulado="   ( " + e1.nombre + ", " 
                + e2.nombre +"' )" + "         ";
        for(int i=0; i<a1.alfabeto.length; i++){
            acumulado += "( " + e1.obtenTransicion(i) + " , " ;
            acumulado += e2.obtenTransicion(i) +"' )" + "         " ;  
            verificaCompraciones(
                    a1.transCon(e1, i), 
                    a2.transCon(e2, i)
            );
        } renglones.encola(acumulado);
    }
}
