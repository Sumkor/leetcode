package com.sumkor.backtrack._0047_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution {

    /**
     * 回溯算法
     *
     * 全排列，使用数组来记录每一位是否遍历过，使用 hashmap 去重
     * 去重思路参考 {@link com.sumkor.backtrack._0078_subsets.Solution#equals()}
     *
     * 执行用时：31 ms, 在所有 Java 提交中击败了13.00% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了77.31% 的用户
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] map = new boolean[nums.length];
        recur(nums, map, path, ans);
        return new ArrayList<>(ans);
    }

    public void recur(int[] nums, boolean[] map, LinkedList<Integer> path, Set<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path)); // 去重
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!map[i]) {
                path.add(nums[i]);
                map[i] = true;
                recur(nums, map, path, ans);
                path.removeLast();
                map[i] = false;
            }
        }
    }

    /**
     * 输入：nums = [1,1,2]
     * 输出：[[1,1,2],[1,2,1],[2,1,1]]
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 1, 2};
//        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> lists = permuteUnique(nums);
        System.out.println("lists = " + lists);
    }
}
