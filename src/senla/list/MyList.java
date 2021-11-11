package senla.list;

import java.util.Collection;
import java.util.List;

public interface MyList<T> extends Iterable<T> {

    public int size();

    public void add(T element);

    boolean isEmpty();

    boolean contains(T element);

    boolean remove(T element);

    boolean addAll(MyList<T> list);
}
