import java.util.Arrays;

public class countBits {
    /* We can use 2 bits to represent the first 2 ^ 2 integers, and 3 bits to represent the first 2 ^ 3 integers (0-7).
     * The number of 1s in these integers are [0, 1, 1, 2, 1, 2, 2, 3].
     * If we add a one to the front of binary representations of the first 8 integers, we have the next 8 integers in the natrual order,
     * for the next 8 integers is (0-7) + 8.
     * The number of 1s in the next 2 ^ n integers is 1 + number of ones in the previous integers.
     */
    public static int[] countBits(int n) {
        int iterations = (int)(Math.ceil(log2(n))) + 1;     // Evaluates to 7 in the 100 example.
        /* First we calculate the upper limits of how many times we add one to all the previous result.
         * For example, if n = 100, then we need ceiling log2(100) = 7 bits to represent 100.
         * We start by calculate the number of 1s in all the positive integers that can be represented by 7 bits.
         */
        int[] resu = new int[n + 1];
        for (int i = 0; i <= iterations; i++) {
            // Fill the array for the first 2 ^ i integers.
            //Loop 0: 0-0 Loop 1: 1-1 Loop2: 2-3 Loop 3:4-7 Loop4: 8-15
            for (int j = (int)Math.pow(2, i - 1); j < (int)Math.pow(2, i)  && j <= n; j++) {
                if(j != 0){
                    resu[j] = resu[j - (int)Math.pow(2, i - 1)] + 1;
                }
            }
        }
        return resu;
    }
    //O(logn) helper method
    public static int count(int n) {
        int resu = 0;
        if (n == 0) {
            return resu;
        } else {
            int counter = 32;
            while (counter > 0) {
                resu += n % 2;
                n = n / 2;
            }
        }
        return resu;
    }
    public static double log2(int x) {
        return (Math.log(x) / Math.log(2));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(2)));
    }
}
