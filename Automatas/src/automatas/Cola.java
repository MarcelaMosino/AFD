
package automatas;

public class Cola<T> implements Encolable<T>{
    Nodo<T> primero;
    Nodo<T> ultimo;
    int tam=0;
    
    public Cola (){
        primero = null;
        ultimo=null;
    }
    
    @Override
    public T despacha() {
        T valor = primero.contenido;
        primero = primero.siguiente;
        return valor;
    }

    @Override
    public void encola(T elemento) {
        Nodo<T> nuevo = new Nodo(elemento);
        if(estaVacia()){
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }   tam++;     
    }
    
    public void vaciar(){
        while(!estaVacia())
            despacha();
    }
    
    public String toString(){
        return "Tamano nodo ="+tam;
    }

    @Override
    public boolean estaVacia() {
        return primero==null;
    }    
}
