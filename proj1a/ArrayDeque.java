public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private double usage;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T []) new Object[8];
        this.nextFirst = items.length / 2;
        this.nextLast = items.length / 2 + 1;
    }


    private void updateUsage() {
        this.usage = this.size / this.items.length;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return this.items.length - 1;
        }
        else{
            return index -1 ;
        }
    }

    private int plusOne(int index) {
        if (index == this.items.length - 1) {
            return 0;
        }
        else{
            return index + 1;
        }
    }

    public void addFirst(T item) {
        if(this.items[this.nextFirst] == null){
            this.items[this.nextFirst] = item;
            this.nextFirst = minusOne(this.nextFirst);
        }
        else{   //the array is full
            resize(this.items.length * 2);
            this.items[this.nextFirst] = item;
            this.nextFirst = minusOne(this.nextFirst);
        }
        this.size++;
    }

    public void addLast(T item) {
        if(this.items[this.nextLast] == null){
            this.items[this.nextLast] = item;
            this.nextLast = plusOne(this.nextLast);
        }
        else{   //the array is full
            resize(this.items.length * 2);
            this.items[this.nextLast] = item;
            this.nextLast = plusOne(this.nextLast);
        }
        this.size++;
    }

    public void printDeque() {
        for(int i = 0; i < this.items.length; i++){
            System.out.println(this.items[i]);
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        else {
            this.items[this.plusOne(this.nextFirst)] = null;
            this.nextFirst = this.plusOne(this.nextFirst);
            this.size--;
            updateUsage();
            if(this.items.length >= 16 & this.usage < 0.25){
                resize(this.items.length / 2);
            }
            return this.items[this.nextFirst + 1];
        }

    }


    public T get(int index) {
        return this.items[index];
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        else {
            this.items[this.minusOne(this.nextLast)] = null;
            this.nextLast = this.minusOne(this.nextLast);
            this.size--;
            if (this.items.length >= 16 & this.usage < 0.25) {
                resize(this.items.length / 2);
            }
            return this.items[this.nextLast - 1];
        }

    }

    private void resize(int cap) {
        //copy items in the original array to newItems, starting at index 0
        T[] newItems = (T[]) new Object[cap];
        int j = 0;
        for (int i = plusOne(this.nextFirst); i < this.size & j < this.size; i = plusOne(i)) {
            newItems[j] = this.items[i];
            j++;
        }
        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
        this.items = newItems;
    }

}
