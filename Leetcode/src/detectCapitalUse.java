class detectCapitalUse {
    public static boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        int i = 0;
        char first = word.charAt(0);
        boolean resu = true;
        if (96 < first && first < 123) {
            while (resu && i < word.length()) {
                char cur = word.charAt(i);
                resu = resu && (96 < cur && first < cur);
                i++;
            }
        } else {    // the initial is a upper case letter
            i = 1;
            if (word.charAt(1) > 96 && word.charAt(1) < 123) {
                while (resu && i < word.length()) {
                    char cur = word.charAt(i);
                    resu = resu && (96 < cur && cur < 123);
                    i++;
                }
            } else {    //all capital letters
                while (resu && i < word.length()) {
                    char cur = word.charAt(i);
                    resu = resu && (64 < cur && cur < 91);
                    i++;
                }
            }

        }
        return resu;
    }

    public static void main(String[] args) {
        String input = "ffffffffffffffffffffF";
        boolean resu = detectCapitalUse(input);
        System.out.println(resu);

    }
}