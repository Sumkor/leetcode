package com.sumkor.array._0239_window;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/10
 */
public class Solution02 {

    /**
     * 滑动窗口 + 单调队列
     *
     * leetcode 官方题解
     * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     * 
     * 顺着大顶堆的思路继续进行优化。
     * 
     * 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i<j），并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]），那么会发生什么呢？
     * 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。
     * 因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除。
     *
     * 因此我们可以使用一个队列存储所有还没有被移除的下标。在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。
     * （因为如果队列中有两个相邻的下标，它们对应的值相等或者递增，那么令前者为 i，后者为 j，就对应了上面所说的情况，即 nums[i] 会被移除，这就产生了矛盾。）
     *
     * 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。
     * 为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，如果前者大于等于后者，那么队尾的元素就可以被永久地移除，我们将其弹出队列。
     * 我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
     * 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
     *
     * 但与方法一中相同的是，此时的最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。
     * 因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
     *
     * 为了可以同时弹出队首和队尾的元素，我们需要使用双端队列。满足这种单调性的双端队列一般称作「单调队列」。
     *
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 每一个下标恰好被放入队列一次，并且最多被弹出队列一次，因此时间复杂度为 O(n)。
     *
     * 空间复杂度：O(k)。
     * 与大顶堆方法不同的是，在本方法中我们使用的数据结构是双向的，因此「不断从队首或队尾弹出元素」保证了队列中最多不会有超过 k+1 个元素，因此队列使用的空间为 O(k)。
     *
     * 执行用时：36 ms, 在所有 Java 提交中击败了63.15% 的用户
     * 内存消耗：55.5 MB, 在所有 Java 提交中击败了28.91% 的用户
     *
     * {@link TestCase#testLong()} 耗时 10 ms
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 双端队列，存储下标
        Deque<Integer> deque = new LinkedList<>();
        // 初始化窗口
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        // 滑动窗口
        for (int i = k; i < n; ++i) {
            // 检查队尾
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast(); // 一旦加入了更大值，则队尾的更小值都无用了，需要废弃
            }
            deque.offerLast(i);
            // 检查队首
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst(); // 不在窗口范围内的最大值，都是无用的，需要废弃
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 滑动窗口 + 大顶堆（不仅用于查找最大值，还要记录下标！！）
     *
     * 对于「最大值」，我们可以想到一种非常合适的数据结构，那就是优先队列（堆），其中的大根堆可以帮助我们实时维护一系列元素中的最大值。
     *
     * 对于本题而言，初始时，我们将数组 nums 的前 k 个元素放入优先队列中。
     * 每当我们向右移动窗口时，我们就可以把一个新的元素放入优先队列中，此时堆顶的元素就是堆中所有元素的最大值。
     * 然而这个最大值可能并不在滑动窗口中，在这种情况下，这个值在数组 nums 中的位置出现在滑动窗口左边界的左侧。
     * 因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除。
     *
     * 我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。此时，堆顶元素就是滑动窗口中的最大值。
     * 为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组 (num,index)，表示元素 num 在数组中的下标为 index。
     *
     * 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。
     * 在最坏情况下，数组 nums 中的元素单调递增，那么最终优先队列中包含了所有元素，没有元素被移除。
     * 由于将一个元素放入优先队列的时间复杂度为 O(logn)，因此总时间复杂度为 O(nlogn)。
     *
     * 空间复杂度：O(n)，即为优先队列需要使用的空间。这里所有的空间复杂度分析都不考虑返回的答案需要的 O(n) 空间，只计算额外的空间使用。
     *
     * 执行用时：89 ms, 在所有 Java 提交中击败了17.27% 的用户
     * 内存消耗：57.7 MB, 在所有 Java 提交中击败了22.49% 的用户
     *
     * {@link TestCase#testLong()} 耗时 10 ms
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 大顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(n, (a, b) -> b[0] - a[0]);
        // 双指针
        int i = 0;
        int j = i + k - 1;
        while (i < capacity) {
            // 初始化窗口
            if (i == 0) {
                for (int index = 0; index < k; index++) {
                    int[] item = new int[2];
                    item[0] = nums[index];
                    item[1] = index;
                    queue.add(item);
                }
            }
            // 滑动窗口
            else {
                // 加入新值
                int[] item = new int[2];
                item[0] = nums[j];
                item[1] = j;
                queue.add(item);
                // 检查最大值
                while (queue.peek()[1] < i) {
                    queue.poll(); // 不在窗口范围内的最大值，都是无用的，需要废弃
                }
            }
            result[i] = queue.peek()[0];
            i++;
            j++;
        }
        return result;
    }

    /**
     * 滑动窗口 + 大顶堆
     *
     * 对于去重的解法，除了用哈希表，还可以用排序。
     * 如果每次都对新的子序列进行排序，很耗时！
     * 这里并不需要全量排序，只需要找出最大值。PriorityQueue 是小顶堆，这里需要改为大顶堆。
     *
     * 但是，这里随着窗口的移动，不停地出队入队，比较耗时！
     *
     * 执行结果：超出时间限制
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a, b) -> b - a);
        // 双指针
        int i = 0;
        int j = i + k - 1;
        while (i < capacity) {
            // 初始化窗口
            if (i == 0) {
                for (int index = 0; index < k; index++) {
                    queue.add(nums[index]);
                }
            }
            // 滑动窗口
            else {
                if (nums[j] != nums[i - 1]) {
                    queue.add(nums[j]);
                    queue.remove(nums[i - 1]); // 很耗时！
                }
            }
            result[i] = queue.peek();
            i++;
            j++;
        }
        return result;
    }

    /**
     * PriorityQueue 默认是小顶堆，改为大顶堆
     */
    @Test
    public void priority() {
        int[] nums = new int[]{1, 3, -1};
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a, b) -> b - a);
        /**
         * a > b return -1 // 返回负数，第一个参数放前面
         * a = b return 0
         * a < b return 1
         */
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        Integer peek = queue.peek();
        System.out.println("peek = " + peek);
    }
}
