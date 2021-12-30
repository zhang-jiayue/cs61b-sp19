 package synthesizer;
import java.util.Iterator;


 public class ArrayRingBuffer<T>  extends  AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.rb = (T[]) new Object[this.capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    private int plusOne(int x){
        if (x == this.rb.length - 1) {
            return 0;
        } else {
            return x + 1;
        }
    }

//    private int minusOne(int x) {
//        if (x == 0){
//            return rb.length - 1;
//        } else {
//            return x + 1;
//        }
//    }
    public void enqueue(T x) {
        if (this.fillCount == this.capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        this.rb[last] = x;
        this.last = plusOne(this.last);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (this.fillCount == 0) {
            throw  new RuntimeException("Ring Buffer Underflow");
        }
        T tmp = this.rb[first];
        this.rb[this.first] = null;
        this.first = plusOne(this.first);
        fillCount -= 1;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return this.rb[this.first];
        // TODO: Return the first item. None of your instance variables should change.
    }




     // TODO: When you get to part 5, implement the needed code to support iteration.

     @Override
     public Iterator<T> iterator() {
         return new bufferIterator();
     }

     public class bufferIterator<T> implements Iterator<T> {
        private int ptr;
        public bufferIterator() {
            ptr = 0;
        }
        public boolean hasNext(){
            return (ptr != capacity);
        }
        public T next() {
            T returnItem = (T) rb[ptr];
            ptr += 1;
            return returnItem;
        }
     }
}
