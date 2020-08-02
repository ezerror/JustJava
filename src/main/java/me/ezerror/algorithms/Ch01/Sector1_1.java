package me.ezerror.algorithms.Ch01;

public class Sector1_1 {
    public static void main(String[] args) {
        // 1.1.1
        System.out.println((0 + 15) / 2);
        System.out.println(2.0e-6 * 100000000.1);
        System.out.println(true && false || true && false);
        // 1.1.2
        System.out.println((1 + 2.236) / 2);
        System.out.println(1 + 2 + 3 + 4.0);
        System.out.println(4.1 >= 4);
        System.out.println(1 + 2 + "3");
        // 1.1.3 too simple
        // 1.1.4 not suitable
        // 1.1.5
        double x, y;
        if ((x = 0.5) < 1 && x > 0 && (y = 0.6) < 1 && y > 0) System.out.println(true);
        else System.out.println(false);
        // 1.1.6
        /**     0 1 1 2 3 5 8 13 21 34 55 89
         *  f:0 1 1 2 3 5 8
         *  g:1 0 1 1 2 3 5
         */
        int f = 0, g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f += g);
            g = f - g;
        }

        // 1.1.16
        /**
         *  311361142246
         */
        System.out.println(exR1(6));
        // 1.1.18
        /**
         * 2  4 8 16 32 64
         * 25 12 6 3 1 0
         *          0+32+16+2 = 50
         */
        System.out.println(mystery(2, 25));
        System.out.println(mystery(3, 11));
        //1.19
        System.out.println(ln(5));
        // 1.1.24
        System.out.println(euclid(72,36));
        // 1.1.25
        /**
         * 需证:
         * gcd(p,q) = gcd(q,p%q);
         * gcd(q,p%q) = gcd(p%q,q%(p%q))
         */
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        else return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }


    public static double ln(int N) {
        if (N == 1) {
            return 0;
        }
        return ln(N - 1) + Math.log(N);

    }

    public static int euclid(int max,int min){
        System.out.println(max +"---"+min);
        if(min == 0) return max;
        return euclid(min,max%min);
    }
}
