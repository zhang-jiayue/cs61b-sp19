package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round(SR / frequency);
        this.buffer = new ArrayRingBuffer<>(capacity);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() throws RuntimeException {

        //       Make sure that your random numbers are different from each other.
        int limit = this.buffer.fillCount();
        for (int i = 0; i < limit; i += 1) {
            this.buffer.dequeue();
        }
        for (int i = 0; i < this.buffer.capacity(); i += 1) {
            double r  = Math.random() - 0.5;
            this.buffer.enqueue(r);

        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() throws RuntimeException {
        double front = this.buffer.dequeue();
        this.buffer.enqueue((this.buffer.peek() + front) / 2 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        try {
            return this.buffer.peek();
        } catch (RuntimeException e) {
            return 0.0;
        }
    }
}
