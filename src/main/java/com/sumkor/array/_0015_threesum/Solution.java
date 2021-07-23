package com.sumkor.array._0015_threesum;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/7/21
 */
public class Solution {

    /**
     * 暴力
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] == -nums[k]) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if (!lists.contains(list)) {
                            lists.add(list);
                        }
                    }
                }
            }
        }
        return lists;
    }

    @Test
    public void test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-1, 0, 1};
//        int[] nums = {-1, 0, 1, 0};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("lists = " + lists);
    }

    @Test
    public void testLong() throws Exception {
        File file = new File("D:\\work\\github\\leetcode\\src\\main\\java\\com\\sumkor\\array\\_0015_threesum\\input.txt");
        boolean exists = file.exists();
        System.out.println("exists = " + exists);

        String fileToString = FileUtils.readFileToString(file, "UTF8");
        String[] split = fileToString.split(",");
        int[] input = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            input[i] = Integer.parseInt(split[i]);
        }
        long start = System.currentTimeMillis();
        List<List<Integer>> lists = threeSum(input);
        System.out.println("ms:" + (System.currentTimeMillis() - start));
        System.out.println("lists = " + lists);
        System.out.println("lists.size() = " + lists.size());
    }
}
