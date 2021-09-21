package com.sumkor.tree._0110_balance;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/21
 */
public class Solution02 {

    /**
     * 自底向上的递归（官方题解）
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/ping-heng-er-cha-shu-by-leetcode-solution/
     *
     * 自顶向下的递归，因此对于同一个节点，计算节点的高度的函数会被重复调用，导致时间复杂度较高。
     * 自底向上的递归，则对于每个节点，函数 height 只会被调用一次。
     *
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。
     * 如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。
     * 使用自底向上的递归，每个节点的计算高度和判断是否平衡都只需要处理一次，最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。
     *
     * 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。
     * 空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了85.65% 的用户
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 子树不平衡
        } else {
            return Math.max(leftHeight, rightHeight) + 1; // 子树平衡，返回子树高度（非负整数）
        }
    }

    /**
     * 官方解还可以进一步优化，如果左子树已经返回 -1 了就不需要再递归右子树了，直接返回 -1 就可以了。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了94.93% 的用户
     */
    public boolean isBalanced1(TreeNode root) {
        return height1(root) >= 0;
    }

    public int height1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height1(root.left);
        if (leftHeight == -1) {
            return -1; // 子树不平衡
        }
        int rightHeight = height1(root.right);
        if (rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 子树不平衡
        } else {
            return Math.max(leftHeight, rightHeight) + 1; // 子树平衡，返回子树高度（非负整数）
        }
    }

    /**
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     *
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     *
     * 输入：root = []
     * 输出：true
     *
     * [1,2,2,3,null,null,3,4,null,null,4]
     * false
     */
    @Test
    public void test() {
//        TreeNode treeNode = TreeNode.create2(3, 9, 20, null, null, 15, 7);
//        TreeNode treeNode = TreeNode.create2(1, 2, 2, 3, 3, null, null, 4, 4);
        TreeNode treeNode = TreeNode.create2(1, 2, 2, 3, null, null, 3, 4, null, null, 4);
        boolean balanced = isBalanced(treeNode);
        System.out.println("balanced = " + balanced);
    }
}
