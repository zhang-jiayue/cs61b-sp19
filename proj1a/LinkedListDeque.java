public class LinkedListDeque<T> {
    private int size;
    private  IntNode sentinel;


    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new IntNode();
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    private class IntNode {
        private IntNode prev;
        private IntNode next;
        private T item;
    }

    public void addFirst(T item) {
        IntNode newNode = new IntNode();
        newNode.item = item;
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        newNode.next.prev = newNode;
        sentinel.next = newNode;
        if (this.size == 0) {
            sentinel.prev = newNode;
        }
        this.size++;
    }

    public void addLast(T item) {
        IntNode newNode = new IntNode();
        newNode.item = item;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        newNode.next = sentinel;
        sentinel.prev = newNode;
        this.size++;
    }
    public void printDeque() {
        IntNode ptr = sentinel.next;
        while (ptr != this.sentinel) {
            System.out.println(ptr.item);
            ptr = ptr.next;
        }
    }

    public boolean isEmpty() {
        boolean resu = false;
        if (this.size == 0) {
            resu = true;
        }
        return resu;
    }

    public int size() {
        return this.size;
    }


    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            T resu = this.sentinel.next.item;
            this.sentinel.next = this.sentinel.next.next;
            this.sentinel.next.prev = this.sentinel;
            this.size--;
            return resu;
        }

    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            T resu = this.sentinel.prev.item;
            this.sentinel.prev.prev.next = this.sentinel;
            this.sentinel.prev = this.sentinel.prev.prev;
            this.size--;
            return resu;
        }
    }
/*
* get method that must use iteration, not recursion
 */
    public T get(int index) {
        T resu = this.sentinel.item;
        IntNode ptr = this.sentinel;
        while (index != -1) {
            ptr = ptr.next;
            resu = ptr.item;
            index--;
        }
        return resu;
    }

/*
* same as get, but use recursion
 */
    private T getRecursiveHelper(IntNode ptr, int index) {
        if (index == -1) {
            return ptr.item;
        }
        ptr = ptr.next;
        return getRecursiveHelper(ptr, --index);
    }
    public T getRecursive(int index) {
        IntNode ptr = this.sentinel;
        T resu = getRecursiveHelper(ptr, index);
        return resu;
    }

}
