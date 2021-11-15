package senla.list;

import java.util.Iterator;

public class MyArrayStack<E> implements MyStack<E> {

    private MyArrayList<E> myList;

    public MyArrayStack(){
        myList = new MyArrayList<>();
    }

    public MyArrayStack(int capacity){
        myList = new MyArrayList<>(capacity);
    }

    @Override
    public E peek() {
        return myList.get(myList.size() -1);
    }

    @Override
    public E pop() {
        E res = peek();
        myList.removeLast();
        return res;
    }

    @Override
    public void push(E item) {
        myList.add(item);
    }

    @Override
    public Iterator<E> iterator() {
        return myList.iterator();
    }
}
