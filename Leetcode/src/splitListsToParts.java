

class splitListToParts {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode[] splitListToParts(ListNode head, int k) {
        if (head.next == null) {
            return null;
        }
        int size = 0;
        ListNode ptr = head;
        while (ptr != null) {
            size++;
            ptr = ptr.next;
        }
        ptr = head;
        ListNode prev = head;
        if (size <= k) {
            ListNode[] resu = new ListNode[k];
            resu[0] = head;
            for (int i = 0; i < size; i++) {
                resu[i] = ptr;
                ptr = ptr.next;
                prev.next = null;
                prev = ptr;
            }
            for (int i = k; i < k; i++) {
                resu[i] = null;
            }
            return resu;
        }
        //size > k
        else {
            int numOfList = size / k;
            int numOfLongerLists = size % k;
            ListNode[] resu = new ListNode[numOfList];
            int i = 0;
            for (; i < numOfList - 1; i++) {
                resu[i] = ptr;
                int counter = k;
                while (counter > 0) {
                    ptr = ptr.next;
                    if (counter != 1) {
                        prev = prev.next;
                    }
                    counter--;
                }
                if (i < numOfLongerLists) {
                    ptr = ptr.next;
                    prev = prev.next;
                }
                prev.next = null;
                prev = ptr;
            }
            resu[i] = ptr;
            return resu;
        }
    }
    public static void main(String[] args) {
        ListNode listA = new ListNode(10);
        listA = new ListNode(9, listA);
        listA = new ListNode(8, listA);
        listA = new ListNode(7, listA);
        listA = new ListNode(6, listA);
        listA = new ListNode(5, listA);
        listA = new ListNode(4, listA);
        listA = new ListNode(3, listA);
        listA = new ListNode(2, listA);
        listA = new ListNode(1, listA);
        splitListToParts(listA, 3);
    }
}