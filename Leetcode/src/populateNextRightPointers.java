public class populateNextRightPointers {
    public static Node connect(Node root) {
        // populate each next pointer using level order traversal
        for (int i = 0; i < height(root); i++) {
            visitLevel(i, root, null);
        }
        return root;
    }

    public static void visitLevel(int level, Node root, Node prev) {
        if (root == null) {
            return;
        }
        if (level == 0) {   // the next pointer point to this node
            if (prev == null) {
                prev = root;
            } else {
                prev.next = root;
                prev = root;
            }
        }
        visitLevel(level - 1, root.left, prev);
        visitLevel(level - 1, root.right, prev);
    }
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            this.val = _val;
            this.left = _left;
            this.right = _right;
            this.next = _next;
        }
    }

    public static void main(String[] args) {
        Node t1 = new Node(1, new Node(2, new Node(4,null,null,null), new Node(5, null,null,null), null), new Node(3, new Node(6, null, null, null), new Node(7, null, null, null), null), null);
        connect(t1);
        System.out.println(t1);
    }
}
