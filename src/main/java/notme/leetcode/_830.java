package notme.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 较大分组的位置
 * https://leetcode-cn.com/problems/positions-of-large-groups/
 */
public class _830 {
    public static void main(String[] args) {
        System.out.println(new _830().largeGroupPositions("aaa"));
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        s += "A";
        List<List<Integer>> list = new ArrayList<>();
        int no = 0;
        char x = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (x != s.charAt(i)) {
                x = s.charAt(i);
                if (i - no > 2) {
                    list.add(Arrays.asList(no, i - 1));
                }
                no = i;
            }
        }
        return list;
    }


    public List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = s.length();
        int num = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    ret.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            }
            else {
                num++;
            }
        }
        return ret;
    }
}
