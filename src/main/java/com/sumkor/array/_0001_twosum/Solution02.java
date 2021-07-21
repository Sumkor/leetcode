package com.sumkor.array._0001_twosum;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/7/21
 */
public class Solution02 {

    /**
     * 枚举数组中的每一个数 x，寻找数组中是否存在 target - x
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void test() {
        int[] nums = {3, 2, 4}; int target = 6;
        nums = twoSum(nums, target);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
