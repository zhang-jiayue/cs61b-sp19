import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class addTwoNumbers2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(){

        }
        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int x, ListNode n) {
            val = x;
            next = n;
        }
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            Stack<Integer> s1 = new Stack<>();
            Stack<Integer> s2 = new Stack<>();

            ListNode cur = l1;
            while (cur != null) {
                s1.push(cur.val);
                cur = cur.next;
            }

            cur = l2;
            while (cur != null) {
                s2.push(cur.val);
                cur = cur.next;
            }

            ListNode resu = null;
            int carry = 0;
            int mod10 = 0;
            while(!s1.isEmpty() & !s2.isEmpty()) {
                mod10 = s1.pop() + s2.pop();
                if (mod10 + carry >= 10) {
                    mod10 = mod10 + carry - 10;
                    carry = 1;
                } else {
                    mod10 = mod10 % 10;
                    carry = 0;
                }

                resu = new ListNode(mod10, resu);
            }
            while(!s1.isEmpty()) {
                mod10 = s1.pop();
                resu = new ListNode(s1.pop(), resu);
            }
            while (!s2.isEmpty()) {
                resu = new ListNode(s2.pop(), resu);
            }
            return resu;
    }

    public static void main(String[] args) {
        ListNode listA = new ListNode(9);

        ListNode listB = new ListNode(9);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(9, listB);
        listB = new ListNode(1, listB);

        System.out.println(addTwoNumbers2(listA, listB).val);
    }
}