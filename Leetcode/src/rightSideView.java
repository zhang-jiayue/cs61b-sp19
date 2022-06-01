import java.util.ArrayList;
import java.util.List;

class rightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        // we know a node is on the right side of the tree if all of this node's ancestors is a right node, or is the only left node without a sibling.
        List<Integer> resu = new ArrayList<>();
        if(root == null) {
            return resu;
        }
        // we know a node is on the right side of the tree if all of this node's ancestors is a right node, or is the only left node without a sibling.
        TreeNode ptr = root;
        while (ptr != null) {  // if right tree is higher or of same height to the left subtree
            resu.add(ptr.val);
            if (ptr.right != null) {
                ptr = ptr.right;
            } else if (ptr.left != null) {
                ptr = ptr.left;
            } else {
                ptr = null;
            }
        }

        List<Integer> left = new ArrayList<>();
        ptr = root.left;
        while (ptr != null) {  // if left subtree is higher
            left.add(ptr.val);
            if (ptr.right != null) {
                ptr = ptr.right;
            } else if (ptr.left != null) {
                ptr = ptr.left;
            } else {
                ptr = null;
            }
        }
        List<Integer> add = new ArrayList<>();
        if (left.size() >= resu.size()) {
            add = left.subList(resu.size()-1,left.size());
        }
        resu.addAll(add);
        return resu;
    }

    public static int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right));
    }
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));
        TreeNode t1 = new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));
        System.out.println(rightSideView(t1));
    }
}
