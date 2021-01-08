package notme.leetcode;

import java.util.Arrays;

public class _189 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        new _189().rotate(nums, 4);
    }

    public void rotate(int[] nums, int k) {
        if (k == 0) return;
        int a = gcd(k, nums.length);

        for (int j = 0; j < a; j++) {
            int t = j;
            int temp = nums[j];
            do {
                int next = (t + k) % nums.length;
                int m = nums[next];
                nums[next] = temp;
                temp = m;
                t = next;
            } while (t != j);
        }
        System.out.println(Arrays.toString(nums));
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}
