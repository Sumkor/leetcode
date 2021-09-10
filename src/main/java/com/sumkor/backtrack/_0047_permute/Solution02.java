package com.sumkor.backtrack._0047_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution02 {

    /**
     * 回溯算法（剪枝）
     *
     * 全排列，使用数组来记录每一位是否遍历过，使用 hashmap 去重，对递归树进行剪枝
     * 但是这里的剪枝是不充分的，对于 [1,1,2] 有效，但是对 [1,2,1] 无效
     *
     * 递归树（每个节点表示选择后剩余的数字）
     *                1,1,2
     *         /         |     \
     *      1,2         1,2    1,1
     *     /   \        / \    / \
     *    2     1      2   1  1   1
     * (1,1,2)(1,2,1)        (2,1,1)
     *
     * 执行用时：17 ms, 在所有 Java 提交中击败了15.85% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了85.04% 的用户
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
                // 剪枝：上一个节点未选择，并且当前节点与上一个节点相同
                if (i > 0 && !map[i - 1] && nums[i] == nums[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                map[i] = true;
                System.out.println("递归前 》 " + path);

                recur(nums, map, path, ans);

                path.removeLast();
                map[i] = false;
                System.out.println("递归后 》 " + path);
            }
        }
    }

    /**
     * 输入：nums = [1,1,2]
     * 输出：[[1,1,2],[1,2,1],[2,1,1]]
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * [3,3,0,3]
     * [[0,3,3,3],[3,0,3,3],[3,3,0,3],[3,3,3,0]]
     */
    @Test
    public void test() {
//        int[] nums = new int[]{1, 1, 2};
//        int[] nums = new int[]{1, 2, 3};
//        int[] nums = new int[]{3, 3, 0, 3};
        int[] nums = new int[]{1, 2, 1};
        List<List<Integer>> lists = permuteUnique(nums);
        System.out.println("lists = " + lists);
    }
}
