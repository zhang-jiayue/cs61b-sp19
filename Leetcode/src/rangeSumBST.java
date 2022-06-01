
class rangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        // post-order traverse the tree and compare the value stored in each node, then add it to the sum if it's within the range
        return helper(0, root, low, high);
    }
    public int helper(int sum, TreeNode root, int low, int high){
        if(root == null) {
            return 0;
        }
        sum = helper(sum, root.left, low, high);
        sum = helper(sum, root.right, low, high);
        if(root.val > low && root.val < high) {
            return sum += root.val;
        }
        return sum;
    }
    public class TreeNode {
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
}