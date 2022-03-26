class isValid {
    public static boolean isValid(String s) {
        if(s.length() < 2 || s.length() % 2 == 1) {
            return false;
        }
        if(s.length() == 2) {
            return isPairOfBrackets(s.charAt(0), s.charAt(1));
        }
        //Fisrt, we find the first occurence of substring (), {} or []
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] != '_') {
                if (isPairOfBrackets(chars[i], chars[i + 1])) {
                    // The first pair of valid parentheses found, now we check left and right of this parentheses.
                    chars[i] = '_';
                    chars[i + 1] = '_';
                    int left = i - 1;
                    int right = i + 2;
                    while (left >= 0 && right < s.length() && chars[left] != '_' && chars[right] != '_') {
                        if(isPairOfBrackets(chars[left], chars[right])) {
                            chars[left] = '_';
                            chars[right] = '_';
                            left--;
                            right++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int numberOfCharsLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != '_') {
                numberOfCharsLeft += 1;
            }
        }
        if(numberOfCharsLeft == 0) {
            return true;
        }
        if(numberOfCharsLeft == s.length()) {
            return false;
        }
        char [] braketsLeft = new char[numberOfCharsLeft];
        numberOfCharsLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != '_') {
                braketsLeft[numberOfCharsLeft] = chars[i];
                numberOfCharsLeft ++;
            }
        }
        return isValid(new String(braketsLeft));
    }

    public static boolean isPairOfBrackets(char left, char right) {
        if (left == '{' && right == '}' || left == '[' && right == ']' || left == '(' && right == ')') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String input = "([)]";
        System.out.println(isValid(input));
    }

}