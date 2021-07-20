package com.sumkor.array._0350_intersect;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/17
 */
public class Solution02 {

    /**
     * 力扣官方题解
     * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/
     *
     * 排序 + 双指针
     *
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     *
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，
     * 如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。
     * 当至少有一个指针超出数组范围时，遍历结束。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(mlogm+nlogn)，其中 m 和 n 分别是两个数组的长度。
     * 对两个数组进行排序的时间复杂度是 O(mlogm+nlogn)，遍历两个数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(mlogm+nlogn)。
     *
     * 空间复杂度：O(min(m,n))，其中 mmm 和 nnn 分别是两个数组的长度。
     * 为返回值创建一个数组 intersection，其长度为较短的数组的长度。
     * 不过在 C++ 中，我们可以直接创建一个 vector，不需要把答案临时存放在一个额外的数组中，所以这种实现的空间复杂度为 O(1)。
     *
     * 对比
     *
     * 如果 nums2​ 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。
     * 那么就无法高效地对 nums2​ 进行排序，因此推荐使用 hash 法而不是双指针法。
     * 在 hash 法中，nums2​ 只关系到查询操作，因此每次读取 nums2​ 中的一部分数据，并进行处理即可。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
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
