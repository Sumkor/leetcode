package com.sumkor.array._0015_threesum;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 黄泽滨 【huangzebin@i72.com】
 * @since 2021/7/23
 */
public class Solution03 {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
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
