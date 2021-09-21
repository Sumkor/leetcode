package com.sumkor.tree._0226_invert;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/21
 */
public class Solution {

    /**
     * 递归交换左右子树
     *
     * 时间复杂度：每个元素都必须访问一次，所以是 O(n)
     * 空间复杂度：最坏的情况下，需要存放 O(h) 个函数调用(h是树的高度)，所以是 O(h)
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了74.90% 的用户
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            swap(root);
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /**
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     *
     * 输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.create2(4, 2, 7, 1, 3, 6, 9);
        treeNode = invertTree(treeNode);
        System.out.println("treeNode = " + treeNode);
    }
}
