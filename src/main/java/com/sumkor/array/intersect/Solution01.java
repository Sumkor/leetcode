package com.sumkor.array.intersect;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/7/14
 */
public class Solution01 {

    /**
     * 力扣官方题解
     * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/
     *
     * 哈希表
     *
     * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。
     * 对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
     *
     * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，
     * 然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。
     * 需要遍历两个数组并对哈希表进行操作，哈希表操作的时间复杂度是 O(1)，因此总时间复杂度与两个数组的长度和呈线性关系。
     *
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。
     * 对较短的数组进行哈希表的操作，哈希表的大小不会超过较短的数组的长度。
     * 为返回值创建一个数组 intersection，其长度为较短的数组的长度。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) { // 首先遍历较短的数组
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) { // 然后遍历较长的数组得到交集
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count); // 更新计数
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    @Test
    public void test() {
        int[] nums1 = {4,9};
        int[] nums2 = {9,4};
        int[] intersect = intersect(nums1, nums2);
        System.out.println("intersect = " + Arrays.toString(intersect));
    }
}
