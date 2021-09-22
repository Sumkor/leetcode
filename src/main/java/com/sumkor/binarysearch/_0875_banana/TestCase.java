package com.sumkor.binarysearch._0875_banana;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class TestCase {

    private Object instance;
    private Method method;

    @Before
    public void init() throws Exception {
        var clazz = Solution.class;
//        var clazz = Solution02.class;
        instance = clazz.getDeclaredConstructor().newInstance();
        method = clazz.getDeclaredMethod("minEatingSpeed", int[].class, int.class);
        method.setAccessible(true);
    }

    /**
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     */
    @Test
    public void test() throws Exception {
        int[] piles = new int[]{3, 6, 7, 11};
        int h = 8;
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("speed = " + speed);
    }

    /**
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     */
    @Test
    public void test02() throws Exception {
        int[] piles = new int[]{30, 11, 23, 4, 20};
        int h = 5;
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("speed = " + speed);
    }

    /**
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     */
    @Test
    public void test03() throws Exception {
        int[] piles = new int[]{30, 11, 23, 4, 20};
        int h = 6;
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("speed = " + speed);
    }

    /**
     * [312884470] 312884469
     * 2
     */
    @Test
    public void test04() throws Exception {
        int[] piles = new int[]{312884470};
        int h = 312884469;
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("speed = " + speed);
    }

    /**
     * [312884470] 968709470
     * 1
     */
    @Test
    public void test05() throws Exception {
        int[] piles = new int[]{312884470};
        int h = 968709470;
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("speed = " + speed);
    }

    /**
     * [332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184]
     * 823855818
     */
    @Test
    public void testLong() throws Exception {
        int[] piles = new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
        int h = 823855818;
        long start = System.currentTimeMillis();
        int speed = (int) method.invoke(instance, piles, h);
        System.out.println("ms = " + (System.currentTimeMillis() - start));
        System.out.println("speed = " + speed);
    }


}
