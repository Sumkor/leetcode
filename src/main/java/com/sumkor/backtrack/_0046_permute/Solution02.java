package com.sumkor.backtrack._0046_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/8
 */
public class Solution02 {

    /**
     * 重点概括：
     * 如果解决一个问题有多个步骤，每一个步骤有多种方法，题目又要我们找出所有的方法，可以使用回溯算法。
     * 回溯算法是在一棵树上的 深度优先遍历（因为要找所有的解，所以需要遍历）。
     * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     *
     * 回溯算法与动态规划的区别
     *
     * 共同点
     *     用于求解多阶段决策问题。多阶段决策问题即：
     *     求解一个问题分为很多步骤（阶段）；
     *     每一个步骤（阶段）可以有多种选择。
     *
     * 不同点
     *     动态规划只需要求我们评估最优解是多少，最优解对应的具体解是什么并不要求。因此很适合应用于评估一个方案的效果；
     *     回溯算法可以搜索得到所有的方案（当然包括最优解），但是本质上它是一种遍历算法，时间复杂度很高。
     */

    /**
     * 回溯法（优化版本）
     *
     * 递归树（每个节点表示可选取的数字）
     *            1,2,3
     *          /   |    \
     *      2,3    1,3    1,2
     *      / \    / \    / \
     *     3   2  3   1  2   1
     * (1,2,3)
     *
     * 递归树不变，优化点：去掉外层的循环，使用数组替代 map 来记录当前位是否遍历过
     *
     * 这里去掉去掉外层的循环，只是一个写法上的优化。
     * 因为这里去掉了 {@link Solution#permute} 方法中的循环，但是又在 {@link Solution02#recur} 中加上了一层循环，总的循环次数不变。
     * 写法上比较简洁，但是理解上，可能没有 {@link Solution#permute} 那么直观。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了85.84% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了15.90% 的用户
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>(); // 当前路径
        boolean[] map = new boolean[nums.length];      // 统计已加入的数据
        recur(nums, map, path, ans);
        return ans;
    }

    private void recur(int[] nums, boolean[] map, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!map[i]) {
                // 1. 做出选择
                path.add(nums[i]);
                map[i] = true;
                // 2. 在当前选择的前提下，选择下一个节点（再一次遍历 0 至 n 范围，选择未选过的）
                recur(nums, map, path, ans);
                // 3. 撤销选择（以便下次循环做出新的选择）
                path.removeLast();
                map[i] = false;
            }
        }
    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 输入：nums = [1]
     * 输出：[[1]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> results = permute(nums);
        System.out.println("results = " + results);
    }
}
