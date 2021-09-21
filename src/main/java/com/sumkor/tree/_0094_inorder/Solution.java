package com.sumkor.tree._0094_inorder;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution {

    /**
     * 中序遍历，递归法
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }

    /**
     * 官方题解（写法比较简洁）
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
     *
     * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，
     * 而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
     * 因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
     *
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了40.98% 的用户
     */
    public void inorder(TreeNode cur, List<Integer> result) {
        if (cur != null) {
            inorder(cur.left, result);
            result.add(cur.val);
            inorder(cur.right, result);
        }
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了94.43% 的用户
     */
    private void inorder0(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder0(root.left, ans);
        }
        if (root.right != null) {
            ans.add(root.val);
            inorder0(root.right, ans);
        }
        if (root.right == null) {
            ans.add(root.val);
        }
    }

    /**
     * 对 inorder0 的改进，实际上就是官方题解的写法
     */
    private void inorder1(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorder0(root.left, ans);
        }
        ans.add(root.val);
        if (root.right != null) {
            inorder0(root.right, ans);
        }
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
//        TreeNode treeNode = TreeNode.create2(1, null, 2, 3);
//        TreeNode treeNode = TreeNode.create2(1, 2);
        TreeNode treeNode = TreeNode.create2(3, 1, 2);
        List<Integer> results = inorderTraversal(treeNode);
        System.out.println("results = " + results);
    }
}
