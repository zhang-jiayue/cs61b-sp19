public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);

    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            return deque.removeLast() == deque.removeFirst() & isPalindromeHelper(deque);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 | word.length() == 1) {
            return true;
        } else {
            return cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))
                    & isPalindrome(word.substring(1, word.length() - 1), cc);
        }
    }

}
