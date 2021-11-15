package senla.list;

import java.util.Iterator;
import java.util.Optional;

public class MyLinkedQueue<E> implements MyQueue<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E element) {
        if (element == null) return;
        linkLast(element);
    }

    @Override
    public E poll() {
        if (first == null) throw new RuntimeException("Queue is empty");
        return unlinkFirst();
    }

    @Override
    public Optional<E> peek() {
        if (first == null) return Optional.empty();
        return Optional.of(first.item);
    }

    private E unlinkFirst(){
        E res = first.item;
        final Node<E> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        return res;
    }

    private void linkLast(E element) {
        Node<E> node = new Node<>(element, null);
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> currentNode;
            @Override
            public boolean hasNext() {
                if (first == null) return false;
                if (currentNode == null) return true;
                return currentNode.next != null;
            }

            @Override
            public E next() {
                if (currentNode == null) {
                    currentNode = first;
                } else {
                    currentNode = currentNode.next;
                }
                return currentNode.item;
            }
        };
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
