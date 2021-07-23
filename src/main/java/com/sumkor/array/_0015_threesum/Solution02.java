package com.sumkor.array._0015_threesum;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sumkor
 * @since 2021/7/23
 */
public class Solution02 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        // 计数 map: key=nums[i], value=count
        Map<Integer, Integer> countMap = new HashMap<>(nums.length);
        Map<Integer, List<Integer>> distinctMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                countMap.put(nums[i], 1);
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 第一次循环，计数
                if (i == 0) {
                    if (countMap.containsKey(nums[j])) {
                        Integer count = countMap.get(nums[j]);
                        countMap.put(nums[j], ++count);
                    } else {
                        countMap.put(nums[j], 1);
                    }
                }

                // 不处理三个0的情况
                if (nums[i] == 0 && nums[j] == 0) {
                    continue;
                }
                int targetNum = -(nums[i] + nums[j]);
                if (countMap.containsKey(targetNum)) {
                    // 若 targetNum 与当前遍历的值相同，则需要确保 targetNum 不是当前值本身
                    if (targetNum == nums[i] || targetNum == nums[j]) {
                        if (countMap.get(targetNum) == 1) {
                            continue;
                        }
                    }
                    // 从小到大
                    int min = Math.min(Math.min(nums[i], nums[j]), targetNum);
                    int max = Math.max(Math.max(nums[i], nums[j]), targetNum);
                    List<Integer> list = new ArrayList<>();
                    list.add(min);
                    list.add(-min - max);
                    list.add(max);
                    lists.add(list);
                    if (distinctMap.containsKey(list.hashCode())) {
                        List<Integer> duplicateList = distinctMap.get(list.hashCode());
                        if (duplicateList.get(0) != min && duplicateList.get(2) != max) {
                            lists.add(list);
                        }
                    } else {
                        lists.add(list);
                        distinctMap.put(list.hashCode(), list);
                    }
                }
            }
        }
        // 最后处理三个0的情况
        if (countMap.get(0) != null && countMap.get(0) >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            lists.add(list);
        }
        lists = lists.stream().distinct().collect(Collectors.toList());
        return lists;
    }

    /**
     * 超出时间限制
     */
    public List<List<Integer>> threeSum0(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        // 初始化 map: key=nums[i], value=count
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer count = map.get(nums[i]);
                map.put(nums[i], ++count);
            } else {
                map.put(nums[i], 1);
            }
        }
        // 提前处理三个0的情况
        if (map.get(0) != null && map.get(0) >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            lists.add(list);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 不处理三个0的情况
                if (nums[i] == 0 && nums[j] == 0) {
                    continue;
                }
                int targetNum = -(nums[i] + nums[j]);
                if (map.containsKey(targetNum)) {
                    // 若 targetNum 与当前遍历的值相同，则需要确保 targetNum 不是当前值本身
                    if (targetNum == nums[i] || targetNum == nums[j]) {
                        if (map.get(targetNum) == 1) {
                            continue;
                        }
                    }
                    // 从小到大
                    int min = Math.min(Math.min(nums[i], nums[j]), targetNum);
                    int max = Math.max(Math.max(nums[i], nums[j]), targetNum);
                    List<Integer> list = new ArrayList<>();
                    list.add(min);
                    list.add(-min - max);
                    list.add(max);
                    if (!lists.contains(list)) {
                        lists.add(list);
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
//        int[] nums = {0, 0, 0};
//        int[] nums = {-99927,2246,97681};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("lists = " + lists);
    }

    @Test
    public void testLong() throws Exception {
//        File file = new File("D:\\work\\github\\leetcode\\src\\main\\java\\com\\sumkor\\array\\_0015_threesum\\input.txt");
        File file = new File("D:\\work\\github\\leetcode\\src\\main\\java\\com\\sumkor\\array\\_0015_threesum\\input2.txt");
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
