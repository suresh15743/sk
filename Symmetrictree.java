class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return false;
        return Symmetric(root.left,root.right);
    }
    public static boolean Symmetric(TreeNode left,TreeNode right)
    {
        if(left == null || right == null) return left == right;
        if(left.val != right.val) return false;
        return Symmetric(left.left,right.right) 
               && Symmetric(left.right,right.left);
    }
}
