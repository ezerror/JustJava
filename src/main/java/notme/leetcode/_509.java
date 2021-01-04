package notme.leetcode;

/**
 * 斐波那契数
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
