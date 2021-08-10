package com.sumkor.array._0239_window;

import com.sumkor.FileHelper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/8/10
 */
public class TestCase {

    private Object instance;
    private Method method;

    @Before
    public void init() throws Exception {
//        Class<Solution> clazz = Solution.class;
        Class<Solution02> clazz = Solution02.class;
        instance = clazz.newInstance();
        method = clazz.getDeclaredMethod("maxSlidingWindow", int[].class, int.class);
//        method = clazz.getDeclaredMethod("maxSlidingWindow1", int[].class, int.class);
        method.setAccessible(true);
    }

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     */
    @Test
    public void test() throws Exception {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    /**
     * 输入：nums = [1], k = 1
     * 输出：[1]
     */
    @Test
    public void test02() throws InvocationTargetException, IllegalAccessException {
        int[] nums = new int[]{1};
        int k = 1;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    /**
     * 输入：nums = [1,-1], k = 1
     * 输出：[1,-1]
     */
    @Test
    public void test03() throws InvocationTargetException, IllegalAccessException {
        int[] nums = new int[]{1, -1};
        int k = 1;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    /**
     * 输入：nums = [9,11], k = 2
     * 输出：[11]
     */
    @Test
    public void test04() throws InvocationTargetException, IllegalAccessException {
        int[] nums = new int[]{9, 11};
        int k = 2;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    /**
     * [1,3,1,2,0,5]  3
     * [3,3,2,5]
     */
    @Test
    public void test05() throws InvocationTargetException, IllegalAccessException {
        int[] nums = new int[]{1, 3, 1, 2, 0, 5};
        int k = 3;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    /**
     * [9,10,9,-7,-4,-8,2,-6]  5
     * [10,10,9,2]
     */
    @Test
    public void test06() throws InvocationTargetException, IllegalAccessException {
        int[] nums = new int[]{9, 10, 9, -7, -4, -8, 2, -6};
        int k = 5;

        int[] result = (int[]) method.invoke(instance, nums, k);
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }

    @Test
    public void testLong() throws Exception {
        int[] input = FileHelper.readFileToArray("com\\sumkor\\array\\_0239_window\\input.txt");

        long start = System.currentTimeMillis();
        int[] result = (int[]) method.invoke(instance, input, 26779);
        System.out.println("ms:" + (System.currentTimeMillis() - start));

        System.out.println("result.length = " + result.length);
//        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }
}
