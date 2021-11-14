package senla.list;

import java.util.Collection;
import java.util.List;

public interface MyList<T> extends Iterable<T> {

    int size();

    void add(T element);

    boolean contains(T element);

    boolean remove(T element);

    void addAll(MyList<T> list);

    T[] toArray();
}
