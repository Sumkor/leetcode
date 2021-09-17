package com.sumkor.tree._0102_levelorder;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/24
 */
public class Solution {

    /**
     * 广度优先算法
     *
     * 使用 list 集合，记录每一层的根节点。
     * 每次遍历的时候，取其左右子节点，作为下一次遍历的根节点。
     *
     * 使用 ArrayList 的结果：
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.73% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了66.57% 的用户
     *
     * 使用 LinkedList 的结果：
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.73% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了8.87% 的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ans.add(Collections.singletonList(root.val));
        // 将二叉树分层，存储每一层的根节点
        List<TreeNode> currLevelNodes = Collections.singletonList(root);
        // 按层遍历
        while (!currLevelNodes.isEmpty()) {
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            List<Integer> nextLevelValues = new ArrayList<>();
            for (TreeNode currLevelNode : currLevelNodes) {
                if (currLevelNode.left != null) {
                    nextLevelNodes.add(currLevelNode.left);
                    nextLevelValues.add(currLevelNode.left.val);
                }
                if (currLevelNode.right != null) {
                    nextLevelNodes.add(currLevelNode.right);
                    nextLevelValues.add(currLevelNode.right.val);
                }
            }
            if (!nextLevelValues.isEmpty()) {
                ans.add(nextLevelValues);
            }
            currLevelNodes = nextLevelNodes;
        }
        return ans;
    }

    /**
     * 广度优先算法（官方题解，区别在于复用队列，总体思路一致）
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
     *
     * 使用队列，在 while 循环的每一轮中，都是将当前层的所有结点出队列，再将下一层的所有结点入队列，这样就实现了层序遍历。
     *
     * 记树上所有节点的个数为 n。
     * 时间复杂度：每个点进队出队各一次，故渐进时间复杂度为 O(n)。
     * 空间复杂度：队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.73% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了32.52% 的用户
     */
    public List<List<Integer>> levelOrder0(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }

    @Test
    public void test() {
//        TreeNode treeNode = TreeNode.create(3);
//        TreeNode treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7);
        TreeNode treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7, null, null, null, null, 11, null, null, 4);
        List<List<Integer>> lists = levelOrder(treeNode);
        System.out.println("lists = " + lists);
    }
}
