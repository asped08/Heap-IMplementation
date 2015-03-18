/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.LinkedList;

/**
 *
 * @author Supun Athukorala
 * @param <T>
 *
 */
public class HeapList<T extends Comparable<T>> implements IntFace {

    private final int default_size = 10;

    private final LinkedList<T> List;

    public HeapList() {
        System.out.println("LinkedList is Created");
        List = new LinkedList<>();
    }
  //  private int size;

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
        return List.get(parent(index));
    }

    boolean hasLeftChild(int i) {
        return leftChild(i) < List.size();
    }

    boolean hasRightChild(int i) {
        return rightChild(i) < List.size();
    }

    private void swap(int a, int b) {
        T tmp = (T) List.get(a);
        List.set(a, List.get(b));
        List.set(b, tmp);
    }

    @Override
    public boolean isEmpty() {
        return (List.isEmpty());
    }

    // adding heap 
    @Override
    public void add(Comparable value) {
        List.add((T) value);
        bubbleUp();
    }

    public void bubbleUp() {
        if (List.isEmpty()) {
            throw new IllegalStateException("Shape error");
        }
        int index = List.size() - 1;
        while (!isRoot(index)) {
            if (myParent(index).compareTo(List.get(index)) <= 0) {
                break;
            }
            // else part 
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // removing 
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T res = List.get(0);
    // root 
        T res2 = List.get(List.size()-1);
        List.set(0, res2);
        List.remove(List.size()-1);
        bubbleDown();

        return res;
    }

    private void bubbleDown() {

        int index = 0;

        while (true) {

            if (hasLeftChild(index) && hasRightChild(index)) {

                if (List.get(leftChild(index)).compareTo(List.get(rightChild(index))) >= 0) {

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

        List.stream().forEach((List1) -> {
            System.out.print(List1 + " ");
        });
        System.out.println("=======");

    }

}
