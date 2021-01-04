package notme.leetcode;

/**
 * <div class="notranslate"><p><strong>斐波那契数</strong>，通常用&nbsp;<code>F(n)</code> 表示，形成的序列称为 <strong>斐波那契数列</strong> 。该数列由&nbsp;<code>0</code> 和 <code>1</code> 开始，后面的每一项数字都是前面两项数字的和。也就是：</p>
 *
 * <pre>F(0) = 0，F(1)&nbsp;= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n &gt; 1
 * </pre>
 *
 * <p>给你 <code>n</code> ，请计算 <code>F(n)</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>2
 * <strong>输出：</strong>1
 * <strong>解释：</strong>F(2) = F(1) + F(0) = 1 + 0 = 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>3
 * <strong>输出：</strong>2
 * <strong>解释：</strong>F(3) = F(2) + F(1) = 1 + 1 = 2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>4
 * <strong>输出：</strong>3
 * <strong>解释：</strong>F(4) = F(3) + F(2) = 2 + 1 = 3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * 	<li><code>0 &lt;= n &lt;= 30</code></li>
 * </ul>
 * </div>

 * 链接：<a href="https://leetcode-cn.com/problems/fibonacci-number">https://leetcode-cn.com/problems/fibonacci-number</a>
 */
public class _509 {

    public static void main(String[] args) {
        System.out.println(new _509().fib3(10));
    }

    // 数组法
    public int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int result[] = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    // 递归
    public int fib2(int n) {
        return n < 2 ? n : fib(n - 1) + fib(n - 2);
    }

    // 动态规划，滚动数组
    public int fib3(int n) {
        if (n < 2) return n;
        int a, b=1, c=1;
        for (int i = 2; i < n; i++) {
            a= b;
            b =c;
            c = a+b;
        }
        return c;
    }


}
