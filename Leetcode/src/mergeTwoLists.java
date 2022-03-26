import java.util.LinkedList;

public class mergeTwoLists {
    private static class ListNode {
      private int val;
      private ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        LinkedList<ListNode> resu = new LinkedList<>();
        resu.addLast(new ListNode(0, null));
        mergeTwoListsHelper(resu, list1, list2);
        return resu.getFirst().next;
    }
    public static ListNode mergeTwoListsHelper(LinkedList<ListNode> dll, ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        } else if (list1 == null || list1.val > list2.val) {
            dll.peekLast().next = list2;
            dll.add(list2);
            list2 = list2.next;
        } else if (list2 == null || list1.val < list2.val) {
            dll.peekLast().next = list1;
            dll.add(list1);
            list1 = list1.next;
        } else {
            dll.peekLast().next = list1;
            dll.add(list1);
            list1 = list1.next;
            dll.peekLast().next = list2;
            dll.add(list2);
            list2 = list2.next;
        }
        return mergeTwoListsHelper(dll, list1, list2);
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode resu = mergeTwoLists(list1, list2);
        while (resu!= null) {
            System.out.println(resu.val);
            resu = resu.next;
        }

    }
}
