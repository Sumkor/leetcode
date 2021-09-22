package com.sumkor.binarysearch;

/**
 * 二分查找的模板
 *
 * @author Sumkor
 * @since 2021/9/22
 */
public class Template {

    /**
     * 模板一
     * https://leetcode-cn.com/leetbook/read/binary-search/xe5fpe/
     *
     * 初始条件：left = 0, right = length-1
     * 终止：left > right
     * 向左查找：right = mid-1
     * 向右查找：left = mid+1
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // End Condition: left > right
        return -1;
    }

    /**
     * 模板二
     * https://leetcode-cn.com/leetbook/read/binary-search/xerqxt/
     *
     * 关键属性
     *     一种实现二分查找的高级方法。
     *     查找条件需要访问元素的直接右邻居。
     *     使用元素的右邻居来确定是否满足条件，并决定是向左还是向右。
     *     保证查找空间在每一步中至少有 2 个元素。
     *     需要进行后处理。 当你剩下 1 个元素时，循环/递归结束。 需要评估剩余元素是否符合条件。
     *
     * 初始条件：left = 0, right = length
     * 终止：left == right
     * 向左查找：right = mid
     * 向右查找：left = mid+1
     */
    public int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length;
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    /**
     * 模板三
     * https://leetcode-cn.com/leetbook/read/binary-search/xe22ch/
     *
     * 关键属性
     *     实现二分查找的另一种方法。
     *     搜索条件需要访问元素的直接左右邻居。
     *     使用元素的邻居来确定它是向右还是向左。
     *     保证查找空间在每个步骤中至少有 3 个元素。
     *     需要进行后处理。 当剩下 2 个元素时，循环/递归结束。 需要评估其余元素是否符合条件。
     *
     * 初始条件：left = 0, right = length-1
     * 终止：left + 1 == right
     * 向左查找：right = mid
     * 向右查找：left = mid
     */
    public int binarySearch3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left + 1 == right
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }
}
