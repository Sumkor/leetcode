package com.sumkor.array._0042_rainwater;

/**
 * @author Sumkor
 * @since 2021/8/24
 */
public class Solution03 {

    /**
     * 双指针（官方题解）
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     *
     * 跟动态规划相比，我们不从左和从右分开计算，我们想办法一次完成遍历。
     *                 ↓
     * 以 [0,1,0,2,1,0,1,3,2,1,2,1] 为例。
     * 从动态规划方法的示意图中我们注意到，只要 right_max[i]>left_max[i] （元素 0 到元素 6），积水高度将由 left_max 决定，
     * 类似地 left_max[i]>right_max[i]（元素 8 到元素 11）。
     * 所以我们可以认为：
     *     如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。
     *     当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从相反的方向遍历（从右到左）。
     * 我们必须在遍历时维护 left_max 和 right_max，但是我们现在可以使用两个指针交替进行，实现 1 次遍历即可完成。
     *
     * 时间复杂度：O(n)。单次遍历的时间O(n)。
     * 空间复杂度：O(1) 的额外空间。left, right, left_max 和 right_max 只需要常数的空间。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了46.40% 的用户
     */
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            // 右端更高，则收缩左边界
            if (height[left] < height[right]) {
                // 左端的最高值，决定了当前的存水量
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            }
            // 左端更高，则收缩右边界
            else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }
}
