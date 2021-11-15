package senla.list;

import java.util.Queue;

public interface MyQueue<E> {

    boolean add(E var1);

    boolean offer(E var1);

    E remove();

    E poll();

    E element();

    E peek();
}
