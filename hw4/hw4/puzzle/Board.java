package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {
    private static final int BLANK = 0;
    private int[][] goal;
    private int[][] tiles;
    private int N;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.goal = new int[N][N];
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    goal[i][j] = 0;
                    this.tiles[i][j] = tiles[i][j];
                } else {
                    this.tiles[i][j] = tiles[i][j];
                    goal[i][j] = N * i + j + 1;
                }
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i > N - 1 || i < 0
                || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return tiles[i][j];
    }

    public int size() {
        return this.N;
    }

    @Override
    /* Returns neighbors of this board.
     * Author: Josh Hug
     * From: http://joshh.ug/neighbors.html
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int resu = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != goal[i][j] && this.tiles[i][j] != 0) {
                    resu += 1;
                }
            }
        }
        return resu;
    }

    public int manhattan() {
        int resu = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /** Actual: i, j
                 *  Expected: I, J
                 *  GOAL[i][j] = 3 * j + i + 1;
                 *  tiles[i][j] = 3 * J + I + 1;
                 *  difference = 3 * (j - J) + (i - I)
                 * resu += (j - J) + (i - I)
                 */
                int actual = this.tiles[i][j];
                int expected = this.goal[i][j];
                if (actual != expected & actual != 0) {
                    int expectedJ = actual % this.N - 1;
                    // Since We don't care about the position of blank.
                    int expectedI = (int) (Math.ceil((double)actual / this.N) - 1);
                    resu += Math.abs(expectedI - i) + Math.abs(expectedJ - j);
                }
            }
        }
        return resu;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        } else if (this.getClass() != y.getClass()) {
            return false;
        } else {
            boolean resu = true;
            Board other = (Board) y;
            for (int row = 0; resu & row < this.N; row++) {
                for (int col = 0; resu & col < this.N; col++) {
                    if (this.tiles[row][col] != other.tiles[row][col]) {
                        resu = false;
                    }
                }
            }
            return resu;
        }

    }

    public int hashCode() {
        return 0;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
