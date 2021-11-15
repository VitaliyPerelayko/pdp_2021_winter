package senla.list;

public interface MyStack<E> extends Iterable<E>{

    E peek();

    E pop();

    void push(E item);
}
