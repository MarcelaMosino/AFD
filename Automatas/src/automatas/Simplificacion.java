
package automatas;

import java.util.Arrays;
import java.util.HashSet;

public class Simplificacion {
    CuadroDialogo v = new CuadroDialogo();
    Automata a1,a2; //a1 es el de entrada
    Cola<Estado> cola = new Cola(); //cola para comparaciones
    //Para evitar hacer comparaciones con estados eliminados
    HashSet<String> eliminados = new HashSet();    
    boolean grandeAun=true; //evita automatas con k vacio
    
    public Simplificacion(Automata a1){
        this.a1 = a1;              
        if(a1.estados.length<=1)
            grandeAun = false; //No se puede reducir mas
    }
    
    public void simplificaAutomata(){
        Estado e1, e2;
        //Para acceder a metodos de comparacion de estados
        Comparacion comp; 
        
        v.mensaje(a1.toString(), 1); //imprime el automata original   
        llenaCola(); // encola todas las posibles combinaciones     
        do{  
            do{//evita que tomemos de la cola a los eliminados
                e1 = cola.despacha();
                e2 = cola.despacha();  
            }while(eliminados.contains(e1.nombre) ||
                    eliminados.contains(e2.nombre));                       
            comp = new Comparacion(a1); //objeto con automata actualizado           
            if(comp.comparaEstados(e1, e2)){
                simplifica(e1, e2);//simplifica el automata                
                imprimeTraza(comp, e1);//imprime la tabla estados equivalentes
                //muestra la definicion formal del automata simplificado
                v.mensaje(a1.toString(),-1);                
            }else imprimeTraza(comp, e1); //imprime tabla no equivalentes          
            //Cambia a falso si el automta quedo con un estado
            if(a1.estados.length<=1) grandeAun=false;             
        }while(!cola.estaVacia() && grandeAun); 
        v.mensaje(a1.toString());//Imprime la DF del AFD final simplificado
    }
    
    public void simplifica(Estado quita, Estado queda){
        //Si los estados son iniciales, queda se vuelve inicial
        if(quita.eInicial || queda.eInicial){
            queda.eInicial = true;
            a1.inicial.eInicial = false;
            a2 = new Automata(a1.alfabeto, queda);
        } else //Si no lo son, conserva su estado inicial
            a2 = new Automata(a1.alfabeto, a1.inicial);
        quitaDeEstados(quita, queda);
        a1 = a2;
    }
    
     //elimina quita y redirecciona a queda
    public void quitaDeEstados(Estado quita, Estado queda){
        Estado auxiliar = null; //Repasar los estados
        int index = 0; //Gurdar el indice de quita
        
        for (int i = 0; i < a1.estados.length; i++) {
            auxiliar = a1.estados[i];    
            //Cambia las transiciones que van a quita, por queda
            for (int j = 0; j < a1.alfabeto.length; j++) {
                if(a1.transCon(auxiliar,j).nombre.equals(quita.nombre))
                    a1.estados[i].setTransicion(j, queda);
            }//Encuentra el indice de queda
            if(auxiliar.nombre.equals(quita.nombre))
                index =i;
        } 
        //Intercambia el ultimo indice por quita 
        auxiliar = a1.estados[a1.estados.length-1];
        a1.estados[index] = auxiliar;
        //En estados de a2, copia el arreglo hasta antes de "quita"
        a2.estados = Arrays.copyOf(a1.estados, a1.estados.length-1);
        eliminados.add(quita.nombre); //Agrega a quita a los eliminados
    }
    
    //Imprime procedimiento
    public void imprimeTraza(Comparacion c, Estado e1){
        String acumulado="Salio "+e1.nombre;
        while(!c.renglones.estaVacia())
            acumulado +=c.renglones.despacha()+"\n";
        if(c.compatibles==true)
            acumulado+="\nEstados equivalentes";
        else
            acumulado+="\nEstados no equivalentes";
        System.out.println(acumulado+"\n*****************\n");
    }
    
    public void llenaCola(){
        //Evita duplicar combinaciones
        HashSet<String> idTuplas = new HashSet();
        Estado e1, e2;
        
        for (int i = 0; i < a1.estados.length; i++) {
            e1 = a1.estados[i];
            for (int j = 0; j < a1.estados.length; j++) {                
                e2 = a1.estados[j];
                //Encola las combinaciones validas y las agrega
                //en las dos maneras posibles
                if(!idTuplas.contains(e1.nombre+ e2.nombre) && j!=i){
                    cola.encola(e1);
                    cola.encola(e2);
                    idTuplas.add(e1.nombre+e2.nombre);
                    idTuplas.add(e2.nombre+e1.nombre);
                }
            }
        }
    }
}
