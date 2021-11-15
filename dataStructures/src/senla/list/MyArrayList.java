package senla.list;

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
    public T get(int index){
        return (T) elements[index];
    }

    @Override
    public T[] toArray() {
        return (T[]) elements;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        if (capacity == size) grow(size + 1);
        elements[size] = element;
        size++;
    }

    @Override
    public void addAll(MyList<T> list) {
        if (list.size() + size >= capacity) grow(size + list.size());
        System.arraycopy(list.toArray(), 0, elements, size, list.size());
        size += list.size();
    }

    @Override
    public boolean contains(T o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(T o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(T o, int start, int end) {
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
        int index = this.indexOf(element);
        if (index == -1) return false;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int counter;
            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                return (T) elements[counter++];
            }
        };
    }

    public void removeLast(){
        elements[size - 1] = null;
        size--;
    }

    private void grow(int minCapacity) {
        System.out.println(newCapacity(minCapacity));
        newCapacity(minCapacity);
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private int newCapacity(int minCapacity) {
        if (minCapacity < 0 || size == Integer.MAX_VALUE) throw new OutOfMemoryError();
        int oldCapacity = size;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < 0) newCapacity = Integer.MAX_VALUE;
        capacity = (newCapacity >= minCapacity)
                ? newCapacity
                : newCapacity(minCapacity);
        return capacity;
    }
}
