package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState{
    private static final int BLANK = 0;
    private static final int [][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private static final Board GOAL = new Board(Board.goal);
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    public int tileAt(int i, int j) {
        if (i > this.tiles.length - 1 || i < 0
                || j < 0 || j > this.tiles.length - 1 ){
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return tiles[i][j];
    }

    public int size(){
        return tiles.length * tiles[0].length;
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
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (this.tiles[i][j] != GOAL.tiles[i][j]) {
                    resu += 1;
                }
            }
        }
        return resu;
    }

    public int manhattan() {
        int resu = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                /** Actual: i, j
                 *  Expected: I, J
                 *  GOAL[i][j] = 3 * j + i + 1;
                 *  tiles[i][j] = 3 * J + I + 1;
                 *  difference = 3 * (j - J) + (i - I)
                 * resu += (j - J) + (i - I)
                 */
                int actual = tiles[i][j];
                int expected = goal[i][j];
                int expectedI = actual % 3 - 1;
                int expectedJ;
                if (actual != expected & actual!= 0) {
                    // Since We don't care about the position of blank.
                    if (actual <= 3) {
                        expectedJ = 0;
                    } else if(actual <= 6) {
                        expectedJ = 1;
                    } else {
                        expectedJ = 2;
                    }
                    resu += Math.abs(expectedI - i) + Math.abs(expectedJ - j);
                }
            }
        }
        return resu;
    }

    public int estimatedDistanceToGoal() {
        return hamming();
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        } else if (this.getClass() != y.getClass()) {
            return false;
        } else if (!(y instanceof Board)) {
            return false;
        } else {
            return hamming() == 0;
        }

    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
