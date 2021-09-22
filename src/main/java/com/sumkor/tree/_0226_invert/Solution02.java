package com.sumkor.tree._0226_invert;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class Solution02 {

    /**
     * 迭代交换左右子树（难度较大）
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了55.48% 的用户
     */
    public TreeNode invertTree(TreeNode root) {
        var stack = new LinkedList<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
            // 当左子树遍历完之后，从栈中弹出元素，进行左右交换
            if (curr == null) {
                while (!stack.isEmpty()) {
                    TreeNode node = stack.pop();
                    // 交换左右子树
                    TreeNode temp = node.right;
                    node.right = node.left;
                    node.left = temp;
                    // 如果当前节点的原右子树不为空，则需要重新迭代该子树
                    if (temp != null) {
                        curr = temp;
                        break;
                    }
                }
            }
        }
        return root;
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
     *
     *
     * [2,null,3,4,null,1]
     * [2,3,null,null,4,null,1]
     *
     *       2
     *        \
     *         3
     *        /
     *       4
     *      /
     *     1
     *
     *
     * [3,2,null,null,4,1]
     * [3,null,2,4,null,null,1]
     *
     *     3
     *    /
     *   2
     *    \
     *     4
     *    /
     *   1
     */
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.create2(4, 2, 7, 1, 3, 6, 9);
//        TreeNode treeNode = TreeNode.create2(2, null, 3, 4, null, 1);
//        TreeNode treeNode = TreeNode.create2(3, 2, null, null, 4, 1);
        treeNode = invertTree(treeNode);
        System.out.println("treeNode = " + treeNode);
    }
}
