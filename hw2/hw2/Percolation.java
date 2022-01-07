package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private boolean[] open;
    private int N;
    private int numOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N <= 0 is illegal.");
        }
        // create N-by-N grid, with all sites initially blocked
        this.sites = new WeightedQuickUnionUF(N * N + 2);
        /**  sites.parent[N * N] is the virtual top site,
         *   sites.parent[N * N + 1] is the virtual bottome site
         */
        this.open = new boolean[N * N];
        this.N = N;
    }

    private int xyTo1d(int x, int y) {
        /**
         * Take integers x = row, y = col and translate them into a single integer
         * to represent a site in the N-by-N grid.
         */
        return x * this.N + y;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= this.N || col >= this.N) {
            throw new IndexOutOfBoundsException("Index < 0 or >=N is illegal");
        }
        int index = xyTo1d(row, col);
        this.open[index] = true;
        this.numOfOpenSites += 1;
        /**
         *  Check all the 4 neighbouring sites to see if any of them is open,
         *  if there is one, union the newly opened site with this neighbour.
         *  To calculate the index of 4 neighbours:
         *             x - N
         *  x - 1      itself     x + 1
         *             x + N
         */
        if (row == 0) {
            this.sites.union(index, N * N);
        }
        if (row == N - 1) {
            this.sites.union(index, N * N + 1);
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            this.sites.union(index, index - N);
        }
        if (row - 1 > 0 && isOpen(row, col - 1)) {
            this.sites.union(index, index - 1);
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            this.sites.union(index, index + 1);
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            this.sites.union(index, index + N);
        }
    }

    public boolean isOpen(int row, int col) {
        return this.open[xyTo1d(row, col)];
    }

    public boolean isFull(int row, int col) {
        /**
         * If a site is full, it is connected to the virtual top site.
         */
        return sites.connected(xyTo1d(row, col), N * N);
    }

    public int numberOfOpenSites() {
        return this.numOfOpenSites;
    }

    public boolean percolates() {
        /**
         * To check percolates, check for connection between top and bottom sites.
         */
        return sites.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) {

    }


}
