import java.util.Arrays;

public class findTheDifference {
    public static char findTheDifference(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        int resu = binaryCompare(sa, ta, 0, sa.length);
        return ta[resu];
    }

    public static int binaryCompare(char[] a, char[] b, int low, int high) {
        int mid = (low + high) / 2;
        if (low > high) {
            return -1;
        }
        if (high == low) {
            return low;
        }
        if (a[mid] == b[mid]) {
            return binaryCompare(a, b, mid + 1, high);
        } else {
            return binaryCompare(a, b, low, mid - 1);
        }
    }

    public static void main(String[] args) {
        String a = "abcd";
        String b = "abcde";
        char resu = findTheDifference(a, b);
        System.out.println(resu);

    }
}
