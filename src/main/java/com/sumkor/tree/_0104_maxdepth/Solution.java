package com.sumkor.tree._0104_maxdepth;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/24
 */
public class Solution {

    /**
     * 递归：传入一个节点，返回该节点的深度（实际上就是深度优先搜索算法！）
     *
     * 深度优先搜索：
     * 如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为 max(l,r)+1
     *
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     * 空间复杂度：O(height)，其中 height 表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了52.38% 的用户
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Test
    public void test() {
//        TreeNode treeNode = TreeNode.create(3);
//        TreeNode treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7);
        TreeNode treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7, null, null, null, null, 11, null, null, 4);
        int result = maxDepth(treeNode);
        System.out.println("result = " + result);
    }
}
