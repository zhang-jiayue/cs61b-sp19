public class populateNextRightPointers2 {

// Definition for a Node.
static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
        public static Node connect(Node root) {
            if (root == null) {
                return null;
            }
            for (int i = 0; i < height(root); i++) {
                bfs(root, i);
            }
            return root;
        }
        public static void bfs(Node root, int level) {
            if (root == null) {
                return;
            }
            if (level == 0) {
                if (root.left != null && root.right != null) {
                    root.left.next = root.right;
                    findNext(root, root.right);
                } else if (root.right != null ) {   //root.left == null && root.right != null
                    findNext(root, root.right);
                } else if (root.left != null) {
                    findNext(root, root.left);
                }
                // if both child nodes are null, do nothing
            }
            bfs(root.left, level - 1);
            bfs(root.right, level - 1);
        }

        public static void findNext(Node parent, Node child) {
            Node ptr = parent.next;
            if (ptr == null) {
                child.next = null;
                return;
            }
            while (ptr != null && child.next == null) {
                if (ptr.left != null) {
                    child.next = ptr.left;
                } else {
                    child.next = ptr.right;
                }
                ptr = ptr.next;
            }
        }

        public static int height(Node root) {
            if (root == null) {
                return 0;
            }
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        connect(n1);
        System.out.println(n1);
    }
}
