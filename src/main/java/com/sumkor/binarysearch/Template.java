package com.sumkor.binarysearch;

import org.junit.Test;

/**
 * 二分查找的模板
 *
 * @author Sumkor
 * @since 2021/9/22
 */
public class Template {

    /**
     * 适用题目：在升序数组 nums 中搜索一个数 target，如果存在，返回其索引，否则返回 -1。
     * 例如：nums = [1,2,3,4,5]，target = 2，则返回索引为 1。
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int target = 5;

        int i1 = binarySearch(nums, target);
        System.out.println("i1 = " + i1);

        int i2 = binarySearch2(nums, target);
        System.out.println("i2 = " + i2);

        int i3 = binarySearch3(nums, target);
        System.out.println("i3 = " + i3);
    }

    /**
     * 模板一. 基本的二分搜索
     * https://leetcode-cn.com/leetbook/read/binary-search/xe5fpe/
     *
     * 初始条件：left = 0, right = length-1
     * 终止：left > right
     * 向左查找：right = mid-1
     * 向右查找：left = mid+1
     *
     * 说明：
     *
     * 初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length。
     * 说明搜索区间为 [left, right] 两端都闭的区间。
     *
     * while(left <= right) 的终止条件是 left == right + 1，写成区间的形式就是 [right + 1, right]，
     * 例如带个具体的数字进去 [3, 2]，可见这时候搜索区间为空，因为没有数字既大于等于 3 又小于等于 2 的。
     *
     * while(left < right) 的终止条件是 left == right，写成区间的形式就是 [right, right]。
     * 例如带个具体的数字进去 [2, 2]，这时候搜索区间非空，还有一个数 2，但此时 while 循环终止了。
     * 也就是说这区间 [2, 2] 被漏掉了，索引 2 没有被搜索，如果这时候直接返回 -1 就可能出现错误。
     *
     * 当然，如果你非要用 while(left < right) 也可以，我们已经知道了出错的原因，最后再检查下 left 就好了。
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;

        // 终止条件，无剩余
        while (left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;  // 过滤 mid
            } else {
                right = mid - 1; // 过滤 mid
            }
        }

        // End Condition: left > right
        return -1;
    }

    /**
     * 模板二. 剩余一个数的二分搜索
     * https://leetcode-cn.com/leetbook/read/binary-search/xerqxt/
     *
     * 初始条件：left = 0, right = length
     * 终止：left == right
     * 向左查找：right = mid
     * 向右查找：left = mid+1
     *
     * 说明：
     *
     * 初始化 right = nums.length 而不是 nums.length - 1 。因此每次循环的「搜索区间」是 [left, right) 左闭右开。
     * 因此，while(left < right) 的终止条件是 left == right，写成区间的形式就是 [right, right)，左边比右边的范围大，说明是空的区间，可以正确终止。
     *
     * 注意，这里要求左闭右开，而 left 变量的取值区间是闭区间 [0, nums.length]，所以需要在 left == nums.length 的时候返回 -1。
     *
     * 为什么 left = mid + 1，right = mid ？
     * 答：这个很好解释，因为我们的「搜索区间」是 [left, right) 左闭右开，
     * 所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
     */
    public int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;

        // 终止条件，剩余 left 作最后的判断
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // 过滤 mid（使用 mid + 1 作为新的左闭边界）
            } else {
                right = mid;    // 保留 mid（依旧使用 mid 作为右开边界，这里 mid 可以保留，反正最后也不会选）
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
     * 模板三. 剩余两个数的二分搜索
     * https://leetcode-cn.com/leetbook/read/binary-search/xe22ch/
     *
     * 初始条件：left = 0, right = length-1
     * 终止：left + 1 == right
     * 向左查找：right = mid
     * 向右查找：left = mid
     *
     * 说明：
     *
     * 搜索区间为 [left, right] 两端都闭的区间。
     *
     * while(left + 1 < right) 的终止条件是 left + 1 == right，写成区间的形式就是 [right - 1, right]，也就是说在条件终止时，还有两个数可供选择。
     * 因此，最后需要检查下 left 和 right 的值。
     *
     * 为什么 left = mid，right = mid ？
     * 答：因为我们的「搜索区间」是 [left, right] 左闭右闭，
     * 当 nums[mid] 被检测之后，下一步的搜索区间保留 mid 分割成两个区间，即 [left, mid] 或 [mid, right]。
     * 当到达终止条件是 left + 1 == right，需要检查两个边界判断是否符合，其中一个边界是应该被过滤的 mid 值。
     */
    public int binarySearch3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;

        // 终止条件，剩余 left 和 right 作最后的判断
        while (left + 1 < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;  // 保留 mid
            } else {
                right = mid; // 保留 mid
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
