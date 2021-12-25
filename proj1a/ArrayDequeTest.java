import java.lang.reflect.Array;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> arr = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, arr.size()) && passed;
        passed = checkEmpty(false, arr.isEmpty()) && passed;

        arr.addLast("middle");
        passed = checkSize(2, arr.size()) && passed;

        arr.addLast("back");
        passed = checkSize(3, arr.size()) && passed;

        System.out.println("Printing out deque: ");
        arr.printDeque();

        printTestStatus(passed);

    }
    public static void addFirstTest() {
        System.out.println("Running add/isEmpty/Size test.");
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> arr = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst("back");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, arr.size()) && passed;
        passed = checkEmpty(false, arr.isEmpty()) && passed;

        arr.addFirst("middle");
        passed = checkSize(2, arr.size()) && passed;

        arr.addFirst("front");
        passed = checkSize(3, arr.size()) && passed;

        System.out.println("Printing out deque: ");
        arr.printDeque();

        printTestStatus(passed);

    }

    public static void removeTest() {
        System.out.println("Running add/isEmpty/Size test.");
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> arr = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst("back");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, arr.size()) && passed;
        passed = checkEmpty(false, arr.isEmpty()) && passed;

        arr.addFirst("middle");
        passed = checkSize(2, arr.size()) && passed;

        arr.addFirst("front");
        passed = checkSize(3, arr.size()) && passed;

        System.out.println("Printing out deque: ");
        arr.printDeque();

        arr.removeFirst();
        passed = checkSize(2, arr.size()) && passed;

        arr.removeLast();
        passed = checkSize(1, arr.size()) && passed;
        passed = arr.get(0).equals("middle") & passed;


        System.out.println("Printing out deque: ");
        arr.printDeque();

        //remove everything from the array
        arr.removeLast();
        passed = checkSize(0, arr.size()) && passed;
        passed = arr.get(3) == null & passed;
        System.out.println("Printing out deque: ");
        arr.printDeque();

        //removeFirst and removeLast when the array is empty
        arr.removeLast();
        passed = checkSize(0, arr.size()) && passed;
        arr.removeFirst();
        System.out.println("Printing out deque: ");
        arr.printDeque();


        printTestStatus(passed);

    }

    /** Adds items until the array is full, then add another item, and ensures that arr is resized. */
    public static void addFullArrayGetTest() {

        ArrayDeque<String> arr = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arr.isEmpty());

        arr.addFirst("string3");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, arr.size()) && passed;
        passed = checkEmpty(false, arr.isEmpty()) && passed;

        arr.addFirst("string2");
        passed = checkSize(2, arr.size()) && passed;

        arr.addFirst("string1");
        passed = checkSize(3, arr.size()) && passed;

        System.out.println("Printing out deque: ");
        arr.printDeque();

        arr.addLast("string4");
        arr.addLast("string5");
        arr.addLast("string6");
        arr.addLast("string7");
        arr.addLast("string8");

        passed = checkSize(8, arr.size()) && passed;


        System.out.println("Printing out deque: ");
        arr.printDeque();

        //add additional item when the array is already full
        arr.addLast("string9");
        passed = checkSize(9, arr.size()) && passed;

        System.out.println("Printing out deque: ");
        arr.printDeque();

        passed = arr.get(0).equals("string1") && passed;

        printTestStatus(passed);

        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        int resu = 0;
        ArrayDeque.addLast(0);
        ArrayDeque.addLast(1);
        ArrayDeque.addLast(2);
        ArrayDeque.addLast(3);
        ArrayDeque.addLast(4);
        ArrayDeque.addLast(5);
        ArrayDeque.addLast(6);
        ArrayDeque.addLast(7);
        ArrayDeque.addLast(8);
        ArrayDeque.addLast(9);
        ArrayDeque.removeLast();

        resu = ArrayDeque.removeFirst();

    }


//
//    public static void getTest() {
//
//        System.out.println("Running get/ getRecursive test.");
//
//        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
//
//        boolean passed = checkEmpty(true, lld1.isEmpty());
//
//        lld1.addFirst("front");
//
//        // The && operator is the same as "and" in Python.
//        // It's a binary operator that returns true if both arguments true, and false otherwise.
//        passed = checkSize(1, lld1.size()) && passed;
//        passed = checkEmpty(false, lld1.isEmpty()) && passed;
//
//        lld1.addLast("middle");
//        passed = checkSize(2, lld1.size()) && passed;
//
//        lld1.addLast("back");
//        passed = checkSize(3, lld1.size()) && passed;
//
//        System.out.println("Printing out deque: ");
//        lld1.printDeque();
//
//        String exp1 = "middle";
//        String n1 = lld1.get(1);
//        passed = exp1.equals(n1) && passed;
//
//        String exp2 = "middle";
//        String n2 = lld1.getRecursive(1);
//        passed = exp2.equals(n2) && passed;
//
//
//        printTestStatus(passed);
//
//    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addFirstTest();
        removeTest();
        addFullArrayGetTest();
//        addRemoveTest();
//        getTest();
    }
}
