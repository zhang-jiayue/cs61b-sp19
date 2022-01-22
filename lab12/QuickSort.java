import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        for (Item i : unsorted) {
            if (i.compareTo(pivot) < 0) {
                less.enqueue(i);
            } else if (i.compareTo(pivot) == 0) {
                equal.enqueue(i);
            } else {
                greater.enqueue(i);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Base case: size 1 problems.
        if (items.isEmpty() || items.size() == 1) {
            return items;
        }

        // Pass a random item in the queue as pivot.
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        partition(items, getRandomItem(items), less, equal, greater);

        // Recursive calls, the equal queue is already in its place,
        // exactly where it'd be if the array were sorted
        // Sort the rest two halves separately.
        Queue<Item> resu = catenate(quickSort(less), equal);
        resu = catenate(resu, quickSort(greater));

        return resu;
    }

    public static void main(String[] args) {
        Queue<Integer> ints = new Queue<>();
        ints.enqueue(34);
        ints.enqueue(17);
        ints.enqueue(14);
        ints.enqueue(17);
        ints.enqueue(23);
        ints.enqueue(35);
        ints.enqueue(99);
        ints.enqueue(14);
        ints.enqueue(14);
        ints.enqueue(6);
        ints.enqueue(5);
        for (Integer i : ints) {
            System.out.println(i);
        }
        ints = quickSort(ints);
        System.out.println("After MergeSort: ");
        for (Integer i : ints) {
            System.out.println(i);
        }
        Queue<Integer> emptyQueue = new Queue<>();
        emptyQueue = quickSort(emptyQueue);
        System.out.println("After MergeSort: ");
        for (Integer i : emptyQueue) {
            System.out.println(i);
        }


    }
}
