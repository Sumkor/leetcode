package com.sumkor.array._0018_foursum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/7/25
 */
public class Solution {

    /**
     * 排序 + 双指针
     *
     * 执行用时：18 ms, 在所有 Java 提交中击败了47.06% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了95.83% 的用户
     *
     * 时间复杂度：O(n^3)，其中 n 是数组的长度。排序的时间复杂度是 O(nlogn)，枚举四元组的时间复杂度是 O(n^3)，因此总时间复杂度为 O(n^3+nlog n)=O(n^3)。
     * 空间复杂度：O(logn)，其中 n 是数组的长度。空间复杂度主要取决于排序额外使用的空间。此外排序修改了输入数组 nums，实际情况中不一定允许，因此也可以看成使用了一个额外的数组存储了数组 nums 的副本并排序，空间复杂度为 O(n)。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return lists;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 使用双指针，遍历剩下的区间
                int right = n - 1;
                for (int left = j + 1; left < n; left++) {
                    if (left > j + 1 && nums[left] == nums[left - 1]) {
                        continue;
                    }
                    while (left < right && nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    }
                    // 双指针相遇则结束
                    if (left == right) {
                        break;
                    }
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        lists.add(list);
                    }
                }
            }
        }
        return lists;
    }

    @Test
    public void test() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> lists = fourSum(nums, target);
        System.out.println("lists = " + lists);
    }
}
