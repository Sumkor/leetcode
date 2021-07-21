package com.sumkor.array._0015_threesum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/7/21
 */
public class Solution {

    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                if (map.containsKey(-nums[j])) {
                    int[] ints = map.get(-nums[j]);
                    List<Integer> list = new ArrayList<>();
                    list.add(ints[0]);
                    list.add(ints[1]);
                    list.add(nums[j]);
                    lists.add(list);
                    continue;
                }
                // 过滤重复项 TODO
                if (map.containsKey(nums[j])) {
                    continue;
                }
                map.put(nums[i] + nums[j], new int[]{nums[i], nums[j]});
            }
        }
        return lists;
    }

    @Test
    public void test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-1, 0, 1};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("lists = " + lists);
    }

}
