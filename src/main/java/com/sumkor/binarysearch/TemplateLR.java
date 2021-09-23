package com.sumkor.binarysearch;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/23
 */
public class TemplateLR {

    /**
     * 寻找左侧边界的二分搜索
     *
     * 适用题目：在升序数组 nums 中搜索一个数 target，如果存在，返回其左侧边界索引。
     * 例如：nums = [1,2,2,2,3]，target = 2，则左侧边界索引为 1。
     *
     * 说明：
     *
     * 为什么该算法能够搜索左侧边界？
     * 答：关键在于对于 nums[mid] == target 这种情况的处理，找到 target 时不要立即返回，
     * 而是缩小「搜索区间」的上界 right，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左侧边界的目的。
     *
     * 其他说明见
     * @see com.sumkor.binarysearch.Template#binarySearch2(int[], int)
     */
    public int leftBound(int[] nums, int target) {
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
                right = mid;    // 保留 mid，收缩右开边界
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
     * 寻找右侧边界的二分搜索
     *
     * 适用题目：在升序数组 nums 中搜索一个数 target，如果存在，返回其右侧边界索引。
     * 例如：nums = [1,2,2,2,3]，target = 2，则左侧边界索引为 3。
     *
     * 说明：
     *
     * 搜索区间为 [left, right) 左闭右开的区间。
     *
     * while(left < right) 的终止条件是 left == right，写成区间的形式就是 [right, right)，可以正确终止。
     *
     * 为什么 left = mid + 1，right = mid ？
     * 答：同左侧边界搜索，因为我们的「搜索区间」是 [left, right) 左闭右开，
     * 所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
     *
     * 为什么这个算法能够找到右侧边界？
     * 答：类似地，当 nums[mid] == target 时，不要立即返回，而是增大「搜索区间」的下界 left，使得区间不断向右收缩，达到锁定右侧边界的目的。
     *
     * 为什么返回结果是 left - 1 ？
     * 答：因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left - 1] 可能是 target。
     * 至于为什么 left 的更新必须是 left = mid + 1，见上一个提问。
     */
    public int rightBound(int[] nums, int target) {
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
                left = mid + 1; // 过滤 mid，收缩左开边界
            } else if (nums[mid] < target) {
                left = mid + 1; // 过滤 mid（使用 mid + 1 作为新的左闭边界）
            } else if (nums[mid] > target) {
                right = mid;    // 保留 mid（依旧使用 mid 作为右开边界，这里 mid 可以保留，反正最后也不会选）
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (nums[left - 1] == target) {
            return left - 1;
        }
        return - 1;
    }

    /**
     * 寻找右侧边界的二分搜索，改为标准写法
     */
    public int rightBound0(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // Post-processing:
        // End Condition: left > right
        if (nums[left - 1] == target) {
            return left - 1;
        }
        return - 1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 2, 2, 5};
        int target = 2;

        int i1 = leftBound(nums, target);
        System.out.println("i1 = " + i1);

        int i2 = rightBound(nums, target);
        System.out.println("i2 = " + i2);
        int i3 = rightBound0(nums, target);
        System.out.println("i3 = " + i3);
    }

    /**
     * 来梳理一下这些细节差异的因果逻辑：
     * https://mp.weixin.qq.com/s/uA2suoVykENmCQcKFMOSuQ
     *
     * 第一个，最基本的二分查找算法：
     * @see com.sumkor.binarysearch.Template#binarySearch(int[], int)
     *
     * 因为我们初始化 right = nums.length - 1
     * 所以决定了我们的「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为我们只需找到一个 target 的索引即可
     * 所以当 nums[mid] == target 时可以立即返回
     *
     *
     * 第二个，寻找左侧边界的二分查找：
     * @see com.sumkor.binarysearch.TemplateLR#leftBound(int[], int)
     *
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid+1 和 right = mid
     *
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     *
     *
     * 第三个，寻找右侧边界的二分查找：
     * @see com.sumkor.binarysearch.TemplateLR#rightBound(int[], int)
     *
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid+1 和 right = mid
     *
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     *
     * 又因为收紧左侧边界时必须 left = mid + 1
     * 所以最后无论返回 left 还是 right，必须减一
     */
}
