public class reverseBits {
    public static int reverseBits(int n) {
        if(n == 0) {
            return 0;
        } else if (n > 0) {
            return reverseBitsPositiveInteger(n);
        } else {
            n = n & (int)(Math.pow(2, 31) - 1); //Mask the first bit of negative integer to 0;
            return reverseBitsPositiveInteger(n) + 1;
        }

    }

    public static int reverseBitsPositiveInteger(int n) {
        int resu = 0;
        for (int i = 0; i < 31; i++) {
            resu += n % 2;
            resu *= 2;
            n = n / 2;
        }
        return resu;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(0B00000010100101000001111010011100));
    }
}
