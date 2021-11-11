package senla.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T>{

    private int size = 0;

    private int capacity = 10;

    private Object[] elements;

    public MyArrayList(){
        elements = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        checkCapacity(1);
        elements[size] = element;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = elements;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public boolean addAll(MyList<T> list) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    private Object[] grow(int minCapacity) {
        return elements = Arrays.copyOf(elements,
                newCapacity(minCapacity));
    }

    private boolean checkCapacity(int amountElementsToAdd){
        if (size + amountElementsToAdd < 0) throw new RuntimeException("Max capacity wos reached");
        return (size + amountElementsToAdd) < capacity;
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = size;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }
}
