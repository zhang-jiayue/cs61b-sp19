public class ArrayDeque <T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private double usage;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T [])new Object[8];
        this.nextFirst = items.length / 2;
        this.nextLast = items.length/2 + 1;
    }

    public ArrayDeque(int len) {
        this.size = 0;
        this.items = (T [])new Object[len];
        this.nextFirst = items.length / 2;
        this.nextLast = items.length/2 + 1;
    }

    public void updateUsage(){
        this.usage = this.size / this.items.length;
    }

    public int minusOne(int index){
        int resu = 0;
        return resu;
    }

    public void addFirst(T item) {
        this.items[this.nextFirst] = item;
        if(this.nextFirst != 0){
            this.nextFirst --;
        }
        else if(this.nextFirst == 0 && this.items[this.items.length - 1] == null)
        {
            this.nextFirst = this.items.length - 1;
        }
        else{
            resize(this.items.length * 2);
            this.nextFirst = this.items.length - 1;
        }
        this.size ++;
    }

    public void addLast(T item) {
        if(this.nextLast != this.items.length - 1){
            this.items[this.nextLast] = item;
            this.nextLast ++;
        }
        else if(this.items[0] == null){
            this.nextLast = 0;
        }
        else{
            resize(this.items.length * 2);
            this.items[this.nextLast] = item;
            this.nextLast ++;
        }
        this.size ++;
    }

    public void printDeque() {
        for(int i = 0; i < this.items.length; i++){
            System.out.println(this.items[i]);
        }
    }

    public boolean isEmpty() {
        boolean resu = this.size == 0;
        return resu;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if(this.size == 0){
            return null;
        }
        else{
            if(this.nextFirst == this.items.length - 1){
                this.items[0] = null;
                this.nextFirst = 0;
            }
            else{
                this.items[this.nextFirst + 1] = null;
                this.nextFirst = this.nextFirst + 1;
            }
            this.size --;
            updateUsage();
            if(this.items.length >= 16 & this.usage < 0.25){
                resize(this.items.length / 2);
            }
            return this.items[this.nextFirst + 1];
        }

    }


    public T get(int index) {
        return this.items[this.size];
    }

    public T removeLast(){
        if(this.size == 0){
            return null;
        }
        else{
            if(this.nextLast - 1 >= 0 & this.nextLast - 1 < this.items.length){
                this.items[this.nextLast - 1] = null;
                this.nextLast = this.nextLast - 1;
            }
            else if(this.nextLast ==0){
                this.items[this.items.length - 1] = null;
                this.nextLast = this.items.length - 2;
            }
            this.size --;
            if(this.items.length >= 16 & this.usage < 0.25){
                resize(this.items.length / 2);
            }
            return this.items[this.nextLast - 1];
        }

    }

    private void resize(int cap){
//        T[]
    }

}