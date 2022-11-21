public interface ListNew<T> {
    T getFirst();

    boolean add(T value);

    boolean isEmpty();

    T remove(int index);

    int getSize();

    T get(int index);

    void clear();

    T getLast();

    T removeFirst();
}
