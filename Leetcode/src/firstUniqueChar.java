class firstUniqChar {
    public static int firstUniqChar(String s) {
        /*0: has not seen before 1 : seen once 2: repeated no need to consider*/
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(letters[cur - 'a'] == 0) {
                letters[cur - 'a'] = 1;
            } else if (letters[cur - 'a'] == 1) {
                letters[cur - 'a'] = 2;
            }
        }
        int resu = -1;
        for (int i = 0; i < s.length(); i++) {
            if(letters[s.charAt(i) - 'a'] == 1) {
                resu = i;
                break;
            }
        }
        return resu;
    }

    public static void main(String[] args) {
        String input = "loveleetcode";
        System.out.println(firstUniqChar(input));
    }

}
