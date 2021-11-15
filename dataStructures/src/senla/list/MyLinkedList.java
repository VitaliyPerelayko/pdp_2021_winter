package senla.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

public class MyLinkedList<T> implements MyList<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index){
        if (index >= size) throw new ArrayIndexOutOfBoundsException("index is bigger then size");
        Iterator<T> iterator = this.iterator();
        int counter = 0;
        while (iterator.hasNext() && (counter < index)){
            iterator.next();
            counter++;
        }
        return iterator.next();
    }

    @Override
    public void add(T element) {
        if (element == null) return;
        linkLast(element);
        size++;
    }

    @Override
    public boolean contains(T element) {
        return findNode(element).isPresent();
    }

    @Override
    public boolean remove(T element) {
        Optional<Node<T>> findRes = findNode(element);
        if (findRes.isEmpty()) return false;
        Node<T> nodeToRemove = findRes.get();
        unlink(nodeToRemove);
        return true;
    }

    @Override
    public void addAll(MyList<T> list) {
        list.forEach(this::add);
    }

    @Override
    public T[] toArray() {
        Object[] array = new Object[size];
        Iterator<T> iterator = this.iterator();
        int i = 0;
        while (iterator.hasNext()){
            array[i++] = iterator.next();
        }
        return (T[]) array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> currentNode;
            @Override
            public boolean hasNext() {
                if (currentNode == null) return true;
                return currentNode.next != null;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    currentNode = first;
                } else {
                    currentNode = currentNode.next;
                }
                return currentNode.item;
            }
        };
    }

    private Optional<Node<T>> findNode(T element) {
        Node<T> tempNode = first;
        do {
            T nodeValue = tempNode.item;
            if (Objects.equals(element, nodeValue)) {
                return Optional.of(tempNode);
            } else {
                tempNode = tempNode.next;
            }
        } while (tempNode != null);
        return Optional.empty();
    }

    private void linkFirst(T element) {
        if (first == null) {
            Node<T> node = new Node<>(null, element, null);
            last = node;
            first = node;
        }
        Node<T> node = new Node<>(null, element, first);
        first.prev = node;
        first = node;
    }

    private void linkLast(T element) {
        if (last == null) {
            Node<T> node = new Node<>(null, element, null);
            last = node;
            first = node;
        } else {
            Node<T> node = new Node<>(last, element, null);
            last.next = node;
            last = node;
        }
    }

    private void unlink(Node<T> x) {
        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }

    private void unlinkFirst(){
        final Node<T> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
    }

    private void unlinkLast(){
        final Node<T> prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
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
