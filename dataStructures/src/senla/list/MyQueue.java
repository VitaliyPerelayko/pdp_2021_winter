package senla.list;

import java.util.Optional;

public interface MyQueue<E> extends Iterable<E> {

    void add(E var1);

    E poll();

    Optional<E> peek();
}
