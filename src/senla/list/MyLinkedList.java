package senla.list;

import java.util.Iterator;
import java.util.LinkedList;

public class MyLinkedList<T> implements MyList<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        linkLast(element);
        size++;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public void addAll(MyList<T> list) {

    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> currentNode = first;
            @Override
            public boolean hasNext() {
                return currentNode.next != null;
            }

            @Override
            public T next() {
                currentNode = currentNode.next;
                return currentNode.item;
            }
        };
    }

    private Node<T> findNode(T element){
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()){

        }
    }

    private void linkFirst(T element){
        Node<T> node = new Node<>(null, element, first);
        first.prev = node;
        first = node;
    }

    private void linkLast(T element){
        Node<T> node = new Node<>(last, element, null);
        last.next = node;
        last = node;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
