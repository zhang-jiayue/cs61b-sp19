package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private PercolationFactory pf;
    private double[] thresholds;
    private int repeatTime;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 | T <= 0) {
            throw new IllegalArgumentException("N <=0 or T <= 0 is illegal");
        }
        this.thresholds = new double[T];
        this.repeatTime = T;
        for (int i = 0; i < T; i += 1) {
            // Initialize all sites to be blocked.
            Percolation p = pf.make(N);
            /**
             * Repeat the following until the system percolates:
             * choose a blocked site at random
             * open the site
             * estimate the percolation threshold: n / N * N
             * where when we open the nth site the system percolates
             */
            int th = 0;
            while (!p.percolates()) {
                p.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
                th += 1;
            }
            /**
             * x_t = fraction of open sites
             */
            this.thresholds[i] = th / N * N;
        }


    }

    public double mean() {
        return StdStats.mean(this.thresholds) / this.repeatTime;
    }

    public double stddev() {
        return StdStats.stddev(this.thresholds) / (this.repeatTime - 1);

    }

    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.repeatTime);

    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.repeatTime);

    }

}
