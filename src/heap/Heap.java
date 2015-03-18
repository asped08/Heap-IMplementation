/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

/**
 *
 * @author Supun Athukorala
 */
public class Heap <T extends Comparable<T>> {
    
IntFace HeapAll;
    // You can select the backStorage by giving the type ArrayList - HeapList
    // Array - HeapArray
    public Heap(HeapArray a){
        HeapAll = (IntFace) a;
    }
    
    public Heap(HeapDatabase a){
        HeapAll = (IntFace) a;
    }
    
    public Heap(HeapList a){
        HeapAll = (IntFace) a;
    }
    
    public void add(T value){
        HeapAll.add(value);
    }
    public T remove(){
       return (T) HeapAll.remove();
    }
    
    public void show(){
        HeapAll.show();
    };
    public boolean isEmpty(){
        return HeapAll.isEmpty();
    }
    
    public static void main(String[] args) {
        Heap heap = new Heap<String>(new HeapArray());

        for (int i = 0; i < 10; i++) {
            heap.add((int) (Math.random() * 10));
            heap.show();
        }

        System.out.println("You should see sorted numbers HeapArray");

        while (!heap.isEmpty()) {
            System.out.print(heap.remove()+" ");
        }
        System.out.println();
        System.out.println("==============================");
      
        Heap heap2 = new Heap<Integer>(new HeapList());

        for (int i = 0; i < 10; i++) {
            heap2.add((int) (Math.random() * 10));
            heap2.show();
        }

        System.out.println("You should see sorted numbers Heap_list");

        while (!heap2.isEmpty()) {
            System.out.print(heap2.remove()+" ");
        }
        System.out.println();
        System.out.println("==============================");
        
        
        Heap heap3 = new Heap<>(new HeapDatabase());

        for (int i = 0; i < 10; i++) {
            heap3.add((int) (Math.random() * 10));
            heap3.show();
        }

        System.out.println("You should see sorted numbers Heap_DataBase");

        while (!heap3.isEmpty()) {
            System.out.print(heap3.remove()+" ");
        }
        System.out.println();
        System.out.println("==============================");
    }
}
