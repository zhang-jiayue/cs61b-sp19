import java.util.Stack;

public class reorderList {
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
    public static void reorderList(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            s.push(cur);
            cur = cur.next;
            size++;
        }
        cur = head;
        int counter = size / 2;
        while (counter > 0) {
            ListNode tmp = cur.next;
            cur.next = s.pop();
            cur = cur.next;
            cur.next = tmp;
            counter--;
        }
        cur.next = null;
    }

    public static void main(String[] args) {
        ListNode listA = new ListNode(9);
        listA = new ListNode(9, listA);
        listA = new ListNode(9, listA);
        listA = new ListNode(9, listA);

        ListNode listB = new ListNode(5);
        listB = new ListNode(4, listB);
        listB = new ListNode(3, listB);
        listB = new ListNode(2, listB);
        listB = new ListNode(1, listB);

       reorderList(listB);
    }
}
