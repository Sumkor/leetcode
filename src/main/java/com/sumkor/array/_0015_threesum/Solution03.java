package com.sumkor.array._0015_threesum;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/7/23
 */
public class Solution03 {

    /**
     * 排序 + 双指针
     * https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
     *
     * 如何做到不重复？
     * 枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a,b,c)这个顺序会被枚举到，而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。
     *
     * 如何不使用三重循环？
     * 可以发现，如果我们固定了前两重循环枚举到的元素 a 和 b，那么只有唯一的 c 满足 a+b+c=0。
     * 当第二重循环往后枚举一个元素 b′ 时，由于 b′>b，那么满足 a+b′+c′=0 的 c′ 一定有 c′<c，即 c′ 在数组中一定出现在 c 的左侧。
     * 也就是说，我们可以从小到大枚举 b，同时从大到小枚举 c，即第二重循环和第三重循环实际上是并列的关系。
     * 有了这样的发现，我们就可以保持第二重循环不变，而将第三重循环变成一个从数组最右端开始向左移动的指针。
     *
     * 这个方法就是我们常说的「双指针」，
     * 当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法，将枚举的时间复杂度从 O(N2) 减少至 O(N)。
     * 为什么是 O(N) 呢？
     * 这是因为在枚举的过程每一步中，「左指针」会向右移动一个位置（也就是题目中的 b），而「右指针」会向左移动若干个位置，
     * 这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)，均摊下来，每次也向左移动一个位置，因此时间复杂度为 O(N)。
     *
     * 时间复杂度：O(N^2)，其中 N 是数组 nums 的长度。
     * 空间复杂度：O(logN)。我们忽略存储答案的空间，额外的排序的空间复杂度为 O(logN)。
     * 然而我们修改了输入的数组 nums，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(N)。
     *
     * 执行用时：23 ms, 在所有 Java 提交中击败了74.37% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了19.80% 的用户
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[first] + nums[second] + nums[third] > 0) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-1, 0, 1};
//        int[] nums = {-1, 0, 1, 0};
        int[] nums = {0, 0, 0};
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
        System.out.println("lists = " + lists);
        System.out.println("lists.size() = " + lists.size());
    }
}
