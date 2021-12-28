public interface Deque<T> {

    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    boolean isEmpty();
    public int size();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
