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
public class HeapArray<T extends Comparable<T>> implements IntFace {

    private final int default_size = 10;

    private final T[] array;
    private int size;

    public HeapArray() {
        
        System.out.println("Array is Created");
        array = (T[]) new Comparable[default_size];
        size = 0;
    }

    boolean isRoot(int index) {
        return (index == 0);
    }

    int leftChild(int index) {
        return 2 * index + 1;
    }

    int parent(int index) {
        return (int) ((index - 1) / 2);
    }

    int rightChild(int index) {
        return 2 * index + 2;
    }

    T myParent(int index) {
        return array[parent(index)];
    }

    boolean hasLeftChild(int i) {
        return leftChild(i) < size;
    }

    boolean hasRightChild(int i) {
        return rightChild(i) < size;
    }

    private void swap(int a, int b) {
        T tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    /* adding heap */
    /**
     *
     * @param value
     */
    @Override
    public void add(Comparable value) {
        if (size == default_size) {
            throw new IllegalStateException("Full array");
        }
        array[size++] = (T) value;
        bubbleUp();
    }

    public void bubbleUp() {
        if (size == 0) {
            throw new IllegalStateException("Shape error");
        }
        int index = size - 1;
        while (!isRoot(index)) {
            if (myParent(index).compareTo(array[index]) <= 0) {
                break;
            }
            /* else part */
            swap(parent(index), index);
            index = parent(index);
        }
    }

    /* removing */
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T res = array[0]; /* root */

        array[0] = array[size - 1];
        size--;
        bubbleDown();
        //  show();
        return res;
    }

    private void bubbleDown() {

        int index = 0;

        while (true) {

            if (hasLeftChild(index) && hasRightChild(index)) {

                if (array[leftChild(index)].compareTo(array[rightChild(index)]) >= 0) {

                    swap(rightChild(index), index);
                    index = rightChild(index);
                } else {

                    swap(leftChild(index), index);
                    index = leftChild(index);
                }

            } else if (!hasRightChild(index) && hasLeftChild(index)) {

                swap(leftChild(index), index);
                index = leftChild(index);

            } else {
                break;
            }

        }
    }

    @Override
    public void show() {

        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("=======");
    }

}
