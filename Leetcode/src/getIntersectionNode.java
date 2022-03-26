public class getIntersectionNode {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int x, ListNode n) {
            val = x;
            next = n;
        }
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode ptrA = headA;
    ListNode ptrB = headB;
    ListNode resu = null;
    while (ptrA != null) {
        while (ptrB != null && ptrB != ptrA) {
            ptrB = ptrB.next;
        }
        //ptrB == null || ptrB = ptrA
        boolean isIntersection = false;
        // If ptrB == PtrA, we check if the rest of the nodes on the SLL are the same.
        if (ptrB == ptrA) {
            isIntersection = true;
            resu = ptrB;
            ListNode ptr = ptrA;
            while (ptrB!= null && ptr != null && isIntersection) {
                isIntersection = isIntersection && ptr == ptrB;
                ptr = ptr.next;
                ptrB = ptrB.next;
            }
        }
        if (isIntersection) {
            break;
        }
        ptrA = ptrA.next;
        ptrB = headB;
    }
    return resu;
    }

    public static void main(String[] args) {
        ListNode listA = new ListNode(5);
        listA = new ListNode(4, listA);
        listA = new ListNode(8, listA);
        ListNode listB = new ListNode(1, listA);
        listB = new ListNode(4, listB);
        listA = new ListNode(1, listA);
        listA = new ListNode(6, listA);
        listA = new ListNode(5, listA);
        System.out.println(getIntersectionNode(listA, listB).val);



    }
}
