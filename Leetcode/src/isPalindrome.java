import java.util.ArrayList;
import java.util.Locale;

class isPalindrome {
    public static boolean isPalindrome(String s) {
        if(s.length() == 0 || s.length() == 1) {
            return true;
        }
        // convert uppercase to lowercase
        s = s.toLowerCase();
        return isPalindromeHelper(s);
    }

    public static boolean isPalindromeHelper(String s) {
        if(s.length() == 0 || s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < s.length() && !isValidChar(s.charAt(left))) {
            left++;
        }
        while (right >= 0 && !isValidChar(s.charAt(right))) {
            right--;
        }
        if (right <= left) {
            return true;    //No valid charactors left
        }
        return s.charAt(left) == s.charAt(right) && isPalindromeHelper(s.substring(left + 1, right));
    }

    public static boolean isValidChar(char c) {
        if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(input));
    }
}
