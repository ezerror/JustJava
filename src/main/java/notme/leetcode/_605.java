package notme.leetcode;

/**
 * 605. 种花问题
 * https://leetcode-cn.com/problems/can-place-flowers/
 */
public class _605 {
    /**
     * 求出已经放的花之间，可以插几个花
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 前一个有花的位置， 默认为最前面虚拟种的花，所以位置是-2
        int pre = -2;
        // 可以种的花的数量
        int c = 0;
        for(int i=0; i<flowerbed.length; i++){
            if(flowerbed[i] == 0) {
                continue;
            }
            c += (i - pre - 2)/2;
            pre = i;
        }
        // flowerbed.length + 1 是虚拟的最后一个有花的位置
        c += ((flowerbed.length + 1) - pre - 2) / 2;
        return c >= n;
    }
}
