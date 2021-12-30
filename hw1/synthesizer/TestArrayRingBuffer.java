package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(6);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        int actual = (int) arb.dequeue();
        assertEquals(6, actual);
        assertEquals(3, arb.fillCount());
        assertEquals(5, arb.peek());
    }

//    /** Calls tests for ArrayRingBuffer. */
//    public static void main(String[] args) {
//        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
//    }
}
