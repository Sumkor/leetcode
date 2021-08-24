package com.sumkor.tree;

/**
 * Definition for a binary tree node.
 *
 * @author Sumkor
 * @since 2021/8/24
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 创建二叉树
     *
     * [3,9,20,null,null,15,7]
     *
     *     3       第0层 -> 2^0=1
     *    / \
     *   9  20     第1层 -> 2^1=2
     *     /  \
     *    15   7   第2层 -> 2^2=4
     */
    public static TreeNode create(Integer... list) {
        if (list == null) return null;
        TreeNode root = new TreeNode(list[0]);
        // 缓存，记录树的父节点
        TreeNode[][] cache = new TreeNode[list.length / 2 + 1][list.length / 2 + 1];
        cache[0][0] = root;
        // 记录当前的数值属于树的哪一层
        int level = 1;
        int levelCount = (int) Math.pow(2, level);
        boolean isLeft = true;
        // 构造
        for (int i = 1; i < list.length; i++) {
            int index = (int) Math.pow(2, level) - levelCount;
            // 查缓存，得到当前位置的父节点
            TreeNode currRoot = cache[level - 1][index / 2];
            if (currRoot != null) {
                // 左节点
                if (isLeft) {
                    if (list[i] == null) {
                        cache[level][index] = null;
                    } else {
                        currRoot.left = new TreeNode(list[i]);
                        cache[level][index] = currRoot.left;
                    }
                    isLeft = false;
                }
                // 右节点
                else {
                    if (list[i] == null) {
                        cache[level][index] = null;
                    } else {
                        currRoot.right = new TreeNode(list[i]);
                        cache[level][index] = currRoot.right;
                    }
                    isLeft = true;
                }
            }
            // 判断是否开始下一层
            levelCount--;
            if (levelCount == 0) {
                level++;
                levelCount = (int) Math.pow(2, level);
            }
        }
        return root;
    }

    public static void main(String[] args) {
//        TreeNode treeNode = TreeNode.create(3);
        TreeNode treeNode = create(3, 9, 20, null, null, 15, 7);
//        TreeNode treeNode = create(3, 9, 20, null, null, 15, 7, null, null, null, null, 11, null, null, 4);
        System.out.println("treeNode = " + treeNode);
    }
}
