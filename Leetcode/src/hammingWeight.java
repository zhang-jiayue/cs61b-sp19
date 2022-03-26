public class hammingWeight {
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int resu = 0;
        if (n == 0) {
            return 0;
        } else if (n < 0) {
            n = n & (int)(Math.pow(2, 31) - 1);   //Mask the first bit of the binary representation of a negative number to 0, so that Java divide it as a positive number.
            resu = count(n) + 1;    //Add the first 1 to resu.

        } else {
            resu = count(n);
        }
        return resu;
    }

    private static int count(int n) {
        int counter = 32;
        int resu = 0;
        while (counter > 0) {   //Count the 32 bits.
            resu += n % 2;
            n = n / 2;
            counter -= 1;
        }
        return resu;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(-3));
    }
}
