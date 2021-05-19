/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author TecNM
 */
public interface Encolable<T> {
    public T despacha();
    public void encola(T elemento);
    public boolean estaVacia();
}
