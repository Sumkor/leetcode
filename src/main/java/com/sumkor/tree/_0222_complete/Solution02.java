package com.sumkor.tree._0222_complete;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/21
 */
public class Solution02 {

    /**
     * 经典解法，思路来源
     * https://www.geekxh.com/1.4.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%B3%BB%E5%88%97/407.html#_04%E3%80%81%E7%BB%8F%E5%85%B8%E8%A7%A3%E6%B3%95
     *
     * 可以将该完全二叉树可以分割成若干满二叉树和完全二叉树。
     * 对任意一个子树，遍历其左子树层高 left，右子树层高 right，当 left == right 则左子树则是满二叉树，否则右子树是满二叉树
     *
     *
     * 例子一：
     * 左子树层高 left = 3，右子树层高 right = 3，此时左子树是满二叉树。
     * 可以套公式计算出左子树的节点数，再递归计算右子树的节点数。
     *
     *           1             第0层 -> 2^0=1
     *      /         \
     *     2          3        第1层 -> 2^1=2
     *    /  \      /   \
     *   4   5     6     7     第2层 -> 2^2=4
     *  /\  / \   /
     * 8 9 10 11 12            第3层 -> 2^3=8
     *
     *
     * 例子二：
     * 左子树层高 left = 3，右子树层高 right = 2，此时右子树是满二叉树。
     * 可以套公式计算出右子树的节点数，再递归计算左子树的节点数。
     *
     *           1             第0层 -> 2^0=1
     *      /         \
     *     2          3        第1层 -> 2^1=2
     *    /  \      /   \
     *   4   5     6     7     第2层 -> 2^2=4
     *  /\  /
     * 8 9 10                  第3层 -> 2^3=8
     *
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了98.78% 的用户
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthL = depth(root.left);
        int depthR = depth(root.right);
        if (depthL == depthR) {
            return countNodes(root.right) + (int) Math.pow(2, depthL - 1);
        }
        if (depthL > depthR) {
            return countNodes(root.left) + (int) Math.pow(2, depthR - 1);
        }
        return 0;
    }

    /**
     * 获取节点的最大深度
     */
    public int depth(TreeNode root) {
        int depth = 1;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
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
