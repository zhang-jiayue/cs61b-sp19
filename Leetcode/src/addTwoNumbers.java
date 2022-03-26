
public class addTwoNumbers {
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
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resu;
        int carry = 0;
        int l1Len = 0;
        int l2Len = 0;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        while (ptr1 != null) {
            l1Len ++;
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            l2Len ++;
            ptr2 = ptr2.next;
        }
        // Add the shorter list to the longer list.
        if (l1Len >= l2Len) {
            resu = l1;
            ListNode ptr = l1;
            while (l2 != null) {
                l1.val += l2.val + carry;
                if (l1.val >= 10) {
                    l1.val = l1.val - 10;
                    carry = 1;
                }
                else {
                    carry = 0;
                }
                ptr = l1;
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1 != null) {
                l1.val += carry;
                if (l1.val >= 10) {
                    l1.val = l1.val - 10;
                    carry = 1;
                }
                else {
                    carry = 0;
                }
                ptr = l1;
                l1 = l1.next;
            }
            if(carry == 1) {
                ptr.next = new ListNode(1);
            }
        }

        else {
            resu = l2;
            ListNode ptr = l2;
            while (l1 != null) {
                l2.val += l1.val + carry;
                if (l2.val >= 10) {
                    l2.val = l2.val - 10;
                    carry = 1;
                }
                else {
                    carry = 0;
                }
                ptr = l2;
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l2 != null) {
                l2.val += carry;
                if (l2.val >= 10) {
                    l2.val = l2.val - 10;
                    carry = 1;
                }
                else {
                    carry = 0;
                }
                ptr = l2;
                l2 = l2.next;
            }
            if(carry == 1) {
                ptr.next = new ListNode(1);
            }
        }
        return resu;
    }

    public static void main(String[] args) {
        ListNode listA = new ListNode(9);
        listA = new ListNode(9, listA);
        listA = new ListNode(9, listA);
        listA = new ListNode(9, listA);

        ListNode listB = new ListNode(9);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);

        System.out.println(addTwoNumbers(listA, listB));
    }
}
