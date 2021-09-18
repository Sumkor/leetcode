package com.sumkor.tree._0700_searchbst;

import com.sumkor.tree.TreeNode;

/**
 * @author Sumkor
 * @since 2021/9/18
 */
public class Solution02 {

    /**
     * 递归法（官方题解）
     * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-sou-suo-by-leetcode/
     *
     * 时间复杂度：O(H)，其中 H 是树高。平均时间复杂度为 O(logN)，最坏时间复杂度为 O(N)
     * 空间复杂度：O(H)，递归栈的深度为 H。平均情况下深度为 O(logN)，最坏情况下深度为 O(N)
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了5.16% 的用户
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    /**
     * 迭代法（官方题解）
     *
     * 时间复杂度：O(H)，其中 H 是树高。平均时间复杂度为 O(logN)，最坏时间复杂度为 O(N)
     * 空间复杂度：O(1)\mathcal{O}(1)O(1)，恒定的额外空间
     */
    public TreeNode searchBST0(TreeNode root, int val) {
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

}
