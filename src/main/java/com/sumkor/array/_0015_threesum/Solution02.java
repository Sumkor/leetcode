package com.sumkor.array._0015_threesum;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 个人解答汇总
 * 思路都是采用 hashMap + 两层循环
 *
 * threeSum0 是最初的尝试，由于使用 lists.contains() 来去重，导致超时。
 * threeSum1 引入 HashSet 来去重，threeSum2 是它的进一步优化版本。
 * threeSum 则是参考了官方题解，利用排序来去重，进一步压缩了时间。
 *
 * 由于引入 hashMap，内存消耗较大。
 *
 * @author Sumkor
 * @since 2021/7/23
 */
public class Solution02 {

    /**
     * 使用 countMap 记录已有的值，用于快速定位
     * 使用排序，用于去重
     *
     * 执行用时：43 ms, 在所有 Java 提交中击败了14.69% 的用户
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了37.54% 的用户
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Map<Integer, Boolean> countMap = new HashMap<>(nums.length);
        int zeroCount = 0;
        // 只能提前初始化 map
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.containsKey(nums[i]));
            if (nums[i] == 0 && zeroCount < 3) {
                zeroCount++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 由于已经排序好，nums[i] 大于 0 时后面不可能有三个数加和等于 0，直接返回结果。
            if (nums[i] > 0) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 需要和上一次枚举的数不相同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 不处理三个0的情况
                if (nums[i] == 0 && nums[j] == 0) {
                    continue;
                }
                int targetNum = -(nums[i] + nums[j]);
                // 由于排过序，必须满足 nums[i] <= nums[j] <= targetNum
                if (targetNum < nums[j]) {
                    continue;
                }
                if (countMap.containsKey(targetNum)) {
                    // 若 targetNum 与当前遍历的值相同，则需要确保 targetNum 不是当前值本身
                    if (targetNum == nums[j] || targetNum == nums[i]) {
                        if (!countMap.get(targetNum)) {
                            continue;
                        }
                    }
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(targetNum);
                    lists.add(list);
                }
            }
        }
        // 最后处理三个0的情况
        if (zeroCount == 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            lists.add(list);
        }
        return lists;
    }

    /**
     * 使用 countMap 记录已有的值，用于快速定位
     * 使用 distinctSet 用于结果去重
     * 优化版
     *
     * 执行用时：297 ms, 在所有 Java 提交中击败了9.93% 的用户
     * 内存消耗：43.8 MB, 在所有 Java 提交中击败了5.02% 的用户
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Map<Integer, Boolean> countMap = new HashMap<>(nums.length);
        HashSet<List<Integer>> distinctSet = new HashSet<>(nums.length);
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                countMap.put(nums[i], false);
            }
            if (nums[i] == 0 && zeroCount < 3) {
                zeroCount++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 第一次循环，计数
                if (i == 0) {
                    countMap.put(nums[j], countMap.containsKey(nums[j])); // 对于 value，若为 true 表示重复
                }
                // 不处理三个0的情况
                if (nums[i] == 0 && nums[j] == 0) {
                    continue;
                }
                int targetNum = -(nums[i] + nums[j]);
                if (countMap.containsKey(targetNum)) {
                    // 若 targetNum 与当前遍历的值相同，则需要确保 targetNum 不是当前值本身
                    if (targetNum == nums[i] || targetNum == nums[j]) {
                        if (!countMap.get(targetNum)) {
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
                    distinctSet.add(list);
                }
            }
        }
        // 最后处理三个0的情况
        if (zeroCount == 3) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            distinctSet.add(list);
        }
        return new ArrayList<>(distinctSet);
    }

    /**
     * 使用 countMap 记录已有的值，用于快速定位
     * 使用 distinctSet 用于结果去重
     *
     * 执行用时：341 ms, 在所有 Java 提交中击败了9.42% 的用户
     * 内存消耗：44.9 MB, 在所有 Java 提交中击败了5.02% 的用户
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        // 计数 map: key=nums[i], value=count
        Map<Integer, Integer> countMap = new HashMap<>(nums.length);
        HashSet<List<Integer>> distinctSet = new HashSet<>(nums.length);
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
                    if (!distinctSet.contains(list)) { // hash 冲突时，使用 equal 对比
                        lists.add(list);
                        distinctSet.add(list);
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
                    if (!lists.contains(list)) { // 去重，很耗时！！
                        lists.add(list);
                    }
                }
            }
        }
        return lists;
    }

    @Test
    public void test() {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-1, 0, 1};
//        int[] nums = {-1, 0, 1, 0};
//        int[] nums = {0, 0, 0};
        int[] nums = {0, 0};
//        int[] nums = {-90606, 6822, 83784};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("lists = " + lists);
    }

    @Test
    public void testLong() throws Exception {
        File file = new File("C:\\TOOL\\Code\\GitHub\\leetcode\\src\\main\\java\\com\\sumkor\\array\\_0015_threesum\\input.txt");
//        File file = new File("C:\\TOOL\\Code\\GitHub\\leetcode\\src\\main\\java\\com\\sumkor\\array\\_0015_threesum\\input2.txt");
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
        System.out.println("lists.size() = " + lists.size());
//        System.out.println("lists = " + lists);
    }
}
