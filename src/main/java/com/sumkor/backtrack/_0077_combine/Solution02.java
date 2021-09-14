package com.sumkor.backtrack._0077_combine;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/2
 */
public class Solution02 {

    /**
     * 回溯法
     * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     *
     * 组合问题，相对于排列问题而言，不计较一个组合内元素的顺序性（即 [1, 2, 3] 与 [1, 3, 2] 认为是同一个组合），
     * 因此很多时候需要按某种顺序展开搜索，这样才能做到不重不漏。
     *
     * 递归结构：在以 n 结尾的候选数组里，选出若干个元素。
     * 每一个结点递归地在做同样的事情，区别在于搜索起点，因此需要一个变量 begin ，表示在区间 [begin, n] 里选出若干个数的组合。
     * 叶子结点的信息体现在从根结点到叶子结点的路径上，因此需要一个表示路径的变量 path，它是一个列表。
     *
     * 递归树（n = 4, k = 2）
     *                   1,2,3,4
     *              /         |      \
     *         2,3,4         3,4      4
     *       /   |   \      /   \      \
     *   (1,2)(1,3)(1,4)  (2,3)(2,4)  (3,4)
     *
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new LinkedList<>();
        // 从 1 开始是题目的设定
        dfs(n, k, 1, path, res);
        return res;
    }

    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了49.20% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了43.64% 的用户
     */
    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // System.out.println("递归之前 => " + path);

            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);

            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
            // System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 回溯法（剪枝）
     *
     * 分析搜索起点的上界，其实是在深度优先遍历的过程中剪枝，剪枝可以避免不必要的遍历，剪枝剪得好，可以大幅度节约算法的执行时间。
     *
     * 容易知道：搜索起点和当前还需要选几个数有关，而当前还需要选几个数与已经选了几个数有关，即与 path 的长度相关。我们举几个例子分析：
     *
     * 例如：n = 6 ，k = 4。
     * path.size() == 1 的时候，接下来要选择 3 个数，搜索起点最大是 4，最后一个被选的组合是 [4, 5, 6]；
     * path.size() == 2 的时候，接下来要选择 2 个数，搜索起点最大是 5，最后一个被选的组合是 [5, 6]；
     * path.size() == 3 的时候，接下来要选择 1 个数，搜索起点最大是 6，最后一个被选的组合是 [6]；
     *
     * 再如：n = 15 ，k = 4。
     * path.size() == 1 的时候，接下来要选择 3 个数，搜索起点最大是 13，最后一个被选的是 [13, 14, 15]；
     * path.size() == 2 的时候，接下来要选择 2 个数，搜索起点最大是 14，最后一个被选的是 [14, 15]；
     * path.size() == 3 的时候，接下来要选择 1 个数，搜索起点最大是 15，最后一个被选的是 [15]；
     *
     * 可以归纳出：
     * 搜索起点的上界 = n - 接下来要选择的元素个数 + 1
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了82.36% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了12.70% 的用户
     */
    private void dfs1(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 优化搜索起点的范围
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs1(n, k, i + 1, path, res);
            path.removeLast();
        }
    }

    /**
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     */
    @Test
    public void test() {
        List<List<Integer>> results = combine(4, 2);
        System.out.println("results = " + results);
    }

}
