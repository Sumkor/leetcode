package com.sumkor.backtrack._0090_subsets;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/30
 */
public class Solution02 {

    List<List<Integer>> results = new ArrayList<>();  // 结果集，表示所有组合
    LinkedList<Integer> combine = new LinkedList<>(); // 子集，表示一种组合
    boolean[] used; // 用于记录当前迭代的路径中，是否选择 nums 数组中的位置

    /**
     * 回溯算法（官方题解）
     * https://leetcode-cn.com/problems/subsets-ii/solution/zi-ji-ii-by-leetcode-solution-7inq/
     *
     * 考虑数组 [1,2,2]，选择前两个数，或者第一、三个数，都会得到相同的子集。
     * 也就是说，对于当前选择的数 x，若前面有与其相同的数 y，且没有选择 y，此时包含 x 的子集，必然会出现在包含 y 的所有子集中。
     * 我们可以通过判断这种情况，来避免生成重复的子集。
     * 代码实现时，可以先将数组排序；迭代时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
     *
     * 时间复杂度：O(n×2^n)，其中 n 是数组 nums 的长度。
     * 排序的时间复杂度为 O(nlogn)。最坏情况下 nums 中无重复元素，需要枚举其所有 2^n 个子集，每个子集加入答案时需要拷贝一份，耗时 O(n)，一共需要 O(n×2^n)+O(n) 的时间来构造子集。
     * 由于在渐进意义上 O(nlogn) 小于 O(n×2^n)，故总的时间复杂度为 O(n×2^n)。
     *
     * 空间复杂度：O(n)。临时数组的空间代价是 O(n)，递归时栈空间的代价为 O(n)。
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了20.41% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了80.35% 的用户
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            results.add(combine);
            return results;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrace(nums, 0);
        return results;
    }

    private void backtrace(int[] nums, int startIndex) {
        results.add(new ArrayList<>(combine));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 在递归时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做出选择
            combine.add(nums[i]);
            used[i] = true;
            // 进入递归
            backtrace(nums, i + 1);
            // 撤销选择
            combine.removeLast();
            used[i] = false;
        }
    }

    /**
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> lists = subsetsWithDup(nums);
        System.out.println("lists = " + lists);
    }
}
