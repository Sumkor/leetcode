package com.sumkor.backtrack._0046_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/8
 */
public class Solution {

    /**
     * 递归树
     *            1,2,3
     *          /   |    \
     *      2,3    1,3    1,2
     *      / \    / \    / \
     *     3   2  3   1  2   1
     * (1,2,3)
     *
     * 遍历 nums 数组，分别以 nums 数组的每一位作为起点进入递归
     * 使用 path 记录当前的路径，使用 map 记录当前位是否已经遍历过
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> path = new LinkedList<>(); // 当前路径
            Map<Integer, Boolean> map = new HashMap<>();   // 统计已加入的数据
            path.add(nums[i]);
            map.put(nums[i], true);
            recur(nums.length, nums, map, path, ans);
        }
        return ans;
    }

    /**
     * 使用 count == 1 记录递归深度
     * 执行用时：1 ms, 在所有 Java 提交中击败了85.94% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了7.15% 的用户
     *
     * 使用 path.size() == nums.length 记录递归深度
     * 执行用时：2 ms, 在所有 Java 提交中击败了28.38% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了77.90% 的用户
     */
    private void recur(int count, int[] nums, Map<Integer, Boolean> map, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (count == 1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            Boolean isExist = map.get(nums[i]);
            if (isExist == null || !isExist) {
                path.add(nums[i]);
                map.put(nums[i], true);
                recur(count - 1, nums, map, path, ans);
                path.removeLast();
                map.put(nums[i], false);
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
