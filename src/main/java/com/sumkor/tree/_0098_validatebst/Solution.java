package com.sumkor.tree._0098_validatebst;

import com.sumkor.tree.TreeNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution {

    /**
     * 深度优先搜索
     *
     * 有效二叉搜索树定义如下：
     * 1. 节点的左子树只包含 小于 当前节点的数。
     * 2. 节点的右子树只包含 大于 当前节点的数。
     * 3. 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 对于任意一个节点，我们不光需要左节点值小于该节点，并且左子树上的所有节点值都需要小于该节点（右节点一致）
     * 所以我们在此引入上界与下界，用以保存之前的节点中出现的最大值与最小值。
     *
     *       8
     *     /   \
     *    4     10
     *   / \    / \
     *  2   7  9   11
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了67.50% 的用户
     *
     * 时间复杂度: O(n)，其中 n 为二叉树的节点个数。
     * 在递归调用的时候二叉树的每个节点最多被访问一次，因此时间复杂度为 O(n)。
     *
     * 空间复杂度: O(n)，其中 n 为二叉树的节点个数。
     * 递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，即二叉树的高度。
     * 最坏情况下二叉树为一条链，树的高度为 n ，递归最深达到 n 层，故最坏情况下空间复杂度为 O(n)。
     */
    public boolean isValidBST(TreeNode root) {
        return doValidate(root, null, null);
    }

    private boolean doValidate(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        // 检查当前节点
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        // 检查左子树
        if (root.left != null) {
            /*if ((max != null && root.left.val >= max) || (min != null && root.left.val <= min) || root.left.val >= root.val) {
                return false;
            }*/
            if (!doValidate(root.left, min, root.val)) {
                return false;
            }
        }
        // 检查右子树
        if (root.right != null) {
            if (!doValidate(root.right, root.val, max)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 输入：root = [2,1,3]
     * 输出：true
     *
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     *
     * [2,2,2]
     * false
     *
     * [5,4,6,null,null,3,7]
     * false
     *
     * [3,1,5,0,2,4,6,null,null,null,3]
     * false
     *
     * [989,982,null,972,null,947,null,920,null,903,null,894,null,881,null,866,null,864,null,842,null,841,null,796,null,726,null,647,null,613,719,593,null,null,null,590,null,558,null,554,null,538,null,512,null,504,null,468,505,467,null,null,null,456,null,413,null,331,null,330,407,320,null,null,null,312,null,306,null,301,null,274,null,251,null,235,null,231,null,222,null,181,null,93,null,83,null,73,null,64,null,62,null,60,null,28,null,21,null,20,null,-32,null,-52,null,-70,null,-87,null,-98,null,-102,null,-115,null,-116,null,-139,null,-183,null,-224,null,-241,null,-263,null,-284,null,-294,null,-296,null,-320,null,-330,null,-392,null,-398,null,-407,null,-431,null,-445,null,-460,null,-463,null,-492,null,-507,null,-518,null,-539,null,-552,null,-558,null,-559,null,-587,null,-673,null,-736,null,-757,null,-766,null,-767,null,-823,null,-830,null,-867,null,-875,null,-891,null,-905,null,-910,null,-924,null,-960,null,-985,null,-988]
     * true
     *
     * [-2147483648,null,2147483647]
     * true
     */
    @Test
    public void test() {
//        TreeNode treeNode = TreeNode.create(2, 1, 3);
//        TreeNode treeNode = TreeNode.create(5, 1, 4, null, null, 3, 6);
//        TreeNode treeNode = TreeNode.create(2, 2, 2);
//        TreeNode treeNode = TreeNode.create(5, 4, 6, null, null, 3, 7);
//        TreeNode treeNode = TreeNode.create(989,982,null,972,null,947,null,920,null,903,null,894,null,881,null,866,null,864,null,842,null,841,null,796,null,726,null,647,null,613,719,593,null,null,null,590,null,558,null,554,null,538,null,512,null,504,null,468,505,467,null,null,null,456,null,413,null,331,null,330,407,320,null,null,null,312,null,306,null,301,null,274,null,251,null,235,null,231,null,222,null,181,null,93,null,83,null,73,null,64,null,62,null,60,null,28,null,21,null,20,null,-32,null,-52,null,-70,null,-87,null,-98,null,-102,null,-115,null,-116,null,-139,null,-183,null,-224,null,-241,null,-263,null,-284,null,-294,null,-296,null,-320,null,-330,null,-392,null,-398,null,-407,null,-431,null,-445,null,-460,null,-463,null,-492,null,-507,null,-518,null,-539,null,-552,null,-558,null,-559,null,-587,null,-673,null,-736,null,-757,null,-766,null,-767,null,-823,null,-830,null,-867,null,-875,null,-891,null,-905,null,-910,null,-924,null,-960,null,-985,null,-988);
        TreeNode treeNode = TreeNode.create(3, 1, 5, 0, 2, 4, 6, null, null, null, 3);
//        TreeNode treeNode = TreeNode.create(-2147483648, null, 2147483647);
        boolean valid = isValidBST(treeNode);
        System.out.println("valid = " + valid);
    }
}
