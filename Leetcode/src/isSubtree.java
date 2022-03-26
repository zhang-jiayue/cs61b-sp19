import com.sun.source.tree.Tree;

public class isSubtree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else {
            return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        FindRoot f = new FindRoot();
        traverse(root, subRoot, f);
        return f.resu;


    }

    public static void traverse(TreeNode root, TreeNode subRoot, FindRoot f) {
        if (root == null) {
            return;
        }
        if (root.val == subRoot.val) {
            f.visit(root, subRoot);
        }
        traverse(root.left, subRoot, f);
        traverse(root.right, subRoot, f);

    }

    public static class FindRoot {
        boolean resu = false;
        public void visit(TreeNode t, TreeNode s) {
            if (t.val == s.val) {
                resu = resu || isSameTree(t, s);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));
        TreeNode t2 = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println(isSubtree(t1, t2));
    }
}
