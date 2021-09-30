package com.sumkor.binarysearch._0033_search;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/30
 */
public class Solution02 {

    /**
     * 官方题解（思路一样，写法比较简洁）
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
     *
     * 数组本身不是有序的，进行旋转后只保证了数组的局部是有序的。
     * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
     *
     * 时间复杂度：O(logn)，其中 n 为 nums 数组的大小。整个算法时间复杂度即为二分查找的时间复杂度 O(logn)。
     * 空间复杂度：O(1)。我们只需要常数级别的空间存放变量
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了12.32% 的用户
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 左边是有序的
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 右边是有序的
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     * [3,1] 0
     */
    @Test
    public void test() {
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int target = 0;

//        int[] nums = new int[]{1};
//        int target = 0;

        int[] nums = new int[]{3, 1};
        int target = 0;

        int result = search(nums, target);
        System.out.println("result = " + result);
    }

}
