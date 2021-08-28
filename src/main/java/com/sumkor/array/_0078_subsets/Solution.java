package com.sumkor.array._0078_subsets;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/28
 */
public class Solution {

    /**
     * 使用 HashMap 作为结果集，减少去重的耗时
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了8.78% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了87.60% 的用户
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> inputList = new ArrayList<>();
        for (int num : nums) {
            inputList.add(num);
        }
        HashSet<List<Integer>> outputSet = new HashSet<>();
        recur(inputList, outputSet);
        return new ArrayList<>(outputSet);
    }

    /**
     * 递归移除 inputList 中的其中一位，加入结果集
     */
    private void recur(List<Integer> inputList, HashSet<List<Integer>> outputSet) {
        if (!outputSet.contains(inputList)) {
            outputSet.add(inputList);
            if (!inputList.isEmpty()) {
                // 移除 inputList 中的其中一位，进入递归
                for (int i = 0; i < inputList.size(); i++) {
                    List<Integer> copyList = new ArrayList<>(inputList);
                    copyList.remove(i);
                    recur(copyList, outputSet);
                }
            }
        }
    }

    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了8.78% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了45.87% 的用户
     */
    public List<List<Integer>> subsets0(int[] nums) {
        List<Integer> inputList = new ArrayList<>();
        for (int num : nums) {
            inputList.add(num);
        }
        List<List<Integer>> resultList = new ArrayList<>();
        recur0(inputList, resultList);
        return resultList;
    }

    /**
     * 递归移除 inputList 中的其中一位，加入结果集
     */
    private void recur0(List<Integer> inputList, List<List<Integer>> resultList) {
        if (!resultList.contains(inputList)) {
            resultList.add(inputList);
            if (!inputList.isEmpty()) {
                // 移除 inputList 中的其中一位，进入递归
                for (int i = 0; i < inputList.size(); i++) {
                    List<Integer> copyList = new ArrayList<>(inputList);
                    copyList.remove(i);
                    recur0(copyList, resultList);
                }
            }
        }
    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println("subsets = " + subsets);
    }

    @Test
    public void equals() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(1);

        System.out.println(list1.equals(list2)); // true
        System.out.println("list1 = " + list1.hashCode());
        System.out.println("list2 = " + list2.hashCode());

        System.out.println(list1.equals(list3)); // false
    }
}
