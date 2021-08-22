package com.sumkor.dp._0120_triangle;

import com.sumkor.FileHelper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/22
 */
public class TestCase {

    private Object instance;
    private Method method;

    @Before
    public void init() throws Exception {
//        Class<Solution> clazz = Solution.class;
//        Class<Solution02> clazz = Solution02.class;
        Class<Solution03> clazz = Solution03.class;
        instance = clazz.newInstance();
        method = clazz.getDeclaredMethod("minimumTotal", List.class);
        method.setAccessible(true);
    }

    /**
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );
        int result = (int)method.invoke(instance, triangle);
        System.out.println("result = " + result);
    }

    @Test
    public void test02() throws InvocationTargetException, IllegalAccessException {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(-10)
        );
        int result = (int)method.invoke(instance, triangle);
        System.out.println("result = " + result);
    }

    /**
     * 贪心算法，每次都找局部最小值。无法通过该用例！
     *
     * [[-1],[2,3],[1,-1,-3]]
     * -1
     */
    @Test
    public void test03() throws InvocationTargetException, IllegalAccessException {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(-1),
                Arrays.asList(2, 3),
                Arrays.asList(1, -1, -3)
        );
        int result = (int)method.invoke(instance, triangle);
        System.out.println("result = " + result);
    }

    /**
     * result = -8717
     */
    @Test
    public void testLong() throws Exception {
        String inputString = FileHelper.readFileToString("com\\sumkor\\dp\\_0120_triangle\\input.txt");
        inputString = inputString.substring(2, inputString.length() - 2);
        String[] splits = inputString.split("],\\[");

        List<List<Integer>> triangle = new ArrayList<>();
        for (String split : splits) {
            if (split.equals("")) continue;
            String[] sub_splits = split.split(",");
            List<Integer> subList = new ArrayList<>();
            for (String sub_split : sub_splits) {
                if (sub_split.equals("")) continue;
                subList.add(Integer.parseInt(sub_split));
            }
            triangle.add(subList);
        }

        // System.out.println("triangle = " + triangle);
        long start = System.currentTimeMillis();
        int result = (int)method.invoke(instance, triangle);
        System.out.println("ms:" + (System.currentTimeMillis() - start));
        System.out.println("result = " + result);
    }
}
