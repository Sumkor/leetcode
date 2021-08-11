package com.sumkor.string._0438_findanagrams;

import com.sumkor.FileHelper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/10
 */
public class TestCase {

    private Object instance;
    private Method method;

    @Before
    public void init() throws Exception {
        Class<Solution> clazz = Solution.class;
//        Class<Solution02> clazz = Solution02.class;
        instance = clazz.newInstance();
        method = clazz.getDeclaredMethod("findAnagrams", String.class, String.class);
//        method = clazz.getDeclaredMethod("findAnagrams1", String.class, String.class);
//        method = clazz.getDeclaredMethod("findAnagrams2", String.class, String.class);
        method.setAccessible(true);
    }
    
    /**
     * 输入: s: "cbaebabacd" p: "abc"
     * 输出: [0, 6]
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test() throws InvocationTargetException, IllegalAccessException {
        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> list = (List<Integer>) method.invoke(instance, s, p);
        System.out.println("list = " + list);
    }

    /**
     * 输入: s: "abab" p: "ab"
     * 输出: [0, 1, 2]
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test02() throws InvocationTargetException, IllegalAccessException {
        String s = "abab";
        String p = "ab";

        List<Integer> list = (List<Integer>) method.invoke(instance, s, p);
        System.out.println("list = " + list);
    }

    /**
     * 输入: "ababababab" "aab"
     * 输出: [0,2,4,6]
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test03() throws InvocationTargetException, IllegalAccessException {
        String s = "ababababab";
        String p = "aab";

        List<Integer> list = (List<Integer>) method.invoke(instance, s, p);
        System.out.println("list = " + list);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLong() throws InvocationTargetException, IllegalAccessException {
        String s = FileHelper.readFileToString("com\\sumkor\\string\\_0438_findanagrams\\inputS.txt");
        String p = FileHelper.readFileToString("com\\sumkor\\string\\_0438_findanagrams\\inputP.txt");

        System.out.println("s.length() = " + s.length());
        System.out.println("p.length() = " + p.length());

        long start = System.currentTimeMillis();
        List<Integer> list = (List<Integer>) method.invoke(instance, s, p);
        System.out.println("ms:" + (System.currentTimeMillis() - start));
//        System.out.println("list = " + list);
    }
}
