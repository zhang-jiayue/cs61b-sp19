import java.util.ArrayList;

public class isAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        ArrayList<Character> sCharacters = new ArrayList<>();
        ArrayList<Character> tCharacters = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            sCharacters.add(s.charAt(i));
            tCharacters.add(t.charAt(i));
        }
        return sCharacters.equals(tCharacters);
    }
}
