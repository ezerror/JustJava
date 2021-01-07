package notme.leetcode;

public class _485 {
    public static void main(String[] args) {
        System.out.println(new _485().findMaxConsecutiveOnes2(new int[]{1, 1, 0, 1}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int x = 0;
        int maxN = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                x++;
            }
            else {
                x = 0;
            }
            maxN = maxN > x ? maxN : x ;
        }
        return maxN;
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        if(nums.length == 0) return 0;
        int fast = 0,slow = 0;
        int num = 0;
        while(fast < nums.length){
            if(nums[fast]==0) {
                num = (fast - slow) > num ? fast - slow : num ;
                slow = fast + 1;
                fast++;
            }else{
                fast++;
            }
        }
        num = (fast - slow) > num ? fast - slow : num ;
        return num;
    }
}
