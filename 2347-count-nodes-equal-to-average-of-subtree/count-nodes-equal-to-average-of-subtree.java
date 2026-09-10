class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        calculateSubtree(root);
        return count;
    }

    private int[] calculateSubtree(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = calculateSubtree(node.left);
        int[] right = calculateSubtree(node.right);

        int sum = node.val + left[0] + right[0];
        int totalNodes = 1 + left[1] + right[1];

        if (sum / totalNodes == node.val) {
            count++;
        }

        return new int[]{sum, totalNodes};
    }
}
