package com.sumkor.tree._0700_searchbst;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/18
 */
public class Solution {

    /**
     * 深度优先搜索
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了17.13% 的用户
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode searchNode = null;
        searchNode = searchBST(root.left, val);
        if (searchNode != null) {
            return searchNode;
        }
        searchNode = searchBST(root.right, val);
        if (searchNode != null) {
            return searchNode;
        }
        return null;
    }

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.create2(4, 2, 7, 1, 3);
        int val = 2;
        TreeNode result = searchBST(treeNode, val);
        System.out.println("result = " + result);
    }

}
