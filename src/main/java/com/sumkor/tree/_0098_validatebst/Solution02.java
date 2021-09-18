package com.sumkor.tree._0098_validatebst;

import com.sumkor.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution02 {

    /**
     * 中序遍历（官方题解）
     * https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的
     */
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

}
