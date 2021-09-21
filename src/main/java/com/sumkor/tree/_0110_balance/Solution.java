package com.sumkor.tree._0110_balance;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/21
 */
public class Solution {

    /**
     * 自顶向下的递归
     *
     * 具体做法类似于二叉树的前序遍历，即对于当前遍历到的节点，首先计算左右子树的高度，
     * 如果左右子树的高度差是否不超过 1，再分别递归地遍历左右子节点，并判断左子树和右子树是否平衡。
     *
     * 时间复杂度：O(n^2)，其中 n 是二叉树中的节点个数。
     * 最坏情况下，二叉树是满二叉树，需要遍历二叉树中的所有节点，时间复杂度是 O(n)。
     * 对于节点 p，如果它的高度是 d，则 height(p) 最多会被调用 d 次（即遍历到它的每一个祖先节点时）。
     * 对于平均的情况，一棵树的高度 h 满足 O(h)=O(logn)，因为 d≤h，所以总时间复杂度为 O(nlogn)。
     * 对于最坏的情况，二叉树形成链式结构，高度为 O(n)，此时总时间复杂度为 O(n^2)。
     *
     * 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了76.64% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了86.00% 的用户
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth - rightDepth <= 1 && rightDepth - leftDepth <= 1) {
            if (!isBalanced(root.left)) {
                return false;
            }
            if (!isBalanced(root.right)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 计算节点的高度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
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
     *
     *          1
     *         / \
     *        2  2
     *       /    \
     *      3     3
     *     /       \
     *    4        4
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
