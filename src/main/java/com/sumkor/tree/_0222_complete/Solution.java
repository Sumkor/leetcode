package com.sumkor.tree._0222_complete;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author Sumkor
 * @since 2021/9/21
 */
public class Solution {

    /**
     * 广度优先搜索（优化版）
     *
     * 时间复杂度和空间复杂度都是 O(n)，其中 n 是二叉树的节点个数
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了10.20% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了29.95% 的用户
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var deque = new LinkedList<TreeNode>();
        deque.addLast(root);
        int count = 1;
        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.removeFirst();
            if (treeNode.left != null) {
                deque.add(treeNode.left);
                count++;
            }
            if (treeNode.right != null) {
                deque.add(treeNode.right);
                count++;
            }
        }
        return count;
    }

    /**
     * 广度优先搜索
     *
     * 执行用时：6 ms, 在所有 Java 提交中击败了6.24% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了15.92% 的用户
     */
    public int countNodes0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var rootDeque = new LinkedList<TreeNode>();
        var currDeque = new LinkedList<TreeNode>();
        rootDeque.addLast(root);
        int count = 1;
        while (!rootDeque.isEmpty()) {
            TreeNode treeNode = rootDeque.removeFirst();
            if (treeNode.left != null) {
                currDeque.add(treeNode.left);
                count++;
            }
            if (treeNode.right != null) {
                currDeque.add(treeNode.right);
                count++;
            }
            if (rootDeque.isEmpty()) {
                rootDeque = currDeque;
                currDeque = new LinkedList<>();
            }
        }
        return count;
    }

    /**
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     *
     * 输入：root = []
     * 输出：0
     *
     * 输入：root = [1]
     * 输出：1
     */
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.create2(1, 2, 3, 4, 5, 6);
//        TreeNode treeNode = TreeNode.create2(1);
        int count = countNodes(treeNode);
        System.out.println("count = " + count);
    }
}
