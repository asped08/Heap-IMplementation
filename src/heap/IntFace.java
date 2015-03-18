/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

/**
 *
 * @author Supun Athukorala
 * @param <T>
 */
public interface IntFace <T extends Comparable<T>>{
    public void add(T value);
    public T remove();
    public boolean isEmpty();
    public void show();
}