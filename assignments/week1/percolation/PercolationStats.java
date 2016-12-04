import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private final int size;
    private final int trials;
    private final double[] result;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.trials = trials;
        this.result = new double[trials];
        run();
    }

    private void run() {
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(size);
            double opened = 0;
            while (!percolation.percolates()) {
                int[] site = randomSite();
                if (!percolation.isOpen(site[0], site[1])) {
                    percolation.open(site[0], site[1]);
                    opened++;
                }
            }
            result[i] = opened / (size * size);
        }
    }

    private int[] randomSite() {
        int row = StdRandom.uniform(1, size + 1);
        int col = StdRandom.uniform(1, size + 1);
        return new int[]{row, col};
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // sample standard deviation of percolation threshold
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("please enter valid input");
        }
        int testSize = 0, testTimes = 0;
        try {
            testSize = Integer.parseInt(args[0]);
            testTimes = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Input format: PercolationStats x y, x and y shoud be positive integer");
        }
        PercolationStats percolationStats = new PercolationStats(testSize, testTimes);
        System.out.printf("mean                    = %s%n", percolationStats.mean());
        System.out.printf("stddev                  = %s%n", percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + 
          percolationStats.confidenceHi());
    }
}