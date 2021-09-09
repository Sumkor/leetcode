package com.sumkor.backtrack._0046_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/8
 */
public class Solution02 {

    /**
     * 回溯法（优化版本）
     * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
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
