public class OffByN implements CharacterComparator {
    private int difference;
    public OffByN(int N) {
        this.difference = N;
    }

    public boolean equalChars(char x, char y) {
        return x - y == this.difference | y - x == this.difference;
    }
}
