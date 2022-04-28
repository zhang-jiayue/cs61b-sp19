class reverseVowels {
    public static String reverseVowels(String s) {
        int leftPtr = 0;
        int rightPtr = s.length() - 1;
        while (leftPtr < rightPtr) {
            while (leftPtr < s.length() && !isVowel(s.charAt(leftPtr))) {
                leftPtr++;
            }
            while (rightPtr > 0 && !isVowel(s.charAt(rightPtr))) {
                rightPtr--;
            }
            if(leftPtr < rightPtr) {
                s = swap(s, leftPtr, rightPtr);
                leftPtr++;
                rightPtr--;
            }
        }
        return s;
    }

    public static boolean isVowel(char word) {
        String temp = Character.toString(word);
        return temp.matches("[aeiouAEIOU]");
    }
    public static String swap(String s, int left, int right) {
        StringBuilder builder = new StringBuilder(s);
        char temp = s.charAt(left);
        builder.setCharAt(left, s.charAt(right));
        builder.setCharAt(right, temp);
        return builder.toString();
    }

    public static void main(String[] args) {
        String input = "hello";
        System.out.println(reverseVowels(input));
    }

}
