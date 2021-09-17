package com.sumkor.tree._0094_inorder;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution02 {

    /**
     * 中序遍历，迭代法
     *
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了64.21% 的用户
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        var stack = new LinkedList<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    /**
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 输入：root = [1,2]
     * 输出：[2,1]
     *
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     *
     * [3,1,2]
     * [1,3,2]
     */
    @Test
    public void test() {
//        TreeNode treeNode = TreeNode.create(1, null, 2, null, null, 3);
//        TreeNode treeNode = TreeNode.create(1, 2);
        TreeNode treeNode = TreeNode.create(3, 1, 2);
        List<Integer> results = inorderTraversal(treeNode);
        System.out.println("results = " + results);
    }
}
