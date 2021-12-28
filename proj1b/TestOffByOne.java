import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char a  = 'x';
        char b = 'x';
        char c = 'y';
        assertTrue(offByOne.equalChars(a, c));
        assertTrue(offByOne.equalChars(c, a));
        assertFalse(offByOne.equalChars(a, b));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('A', 'a'));


    }
}
