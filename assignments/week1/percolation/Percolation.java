import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int size;
    private WeightedQuickUnionUF unionFind;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.grid = new boolean[n][n];
        this.size = n;
        // the unionFind set should have n * n plus a head and a bottom;
        this.unionFind = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
            if (!check(row, col)) {
                throw new IndexOutOfBoundsException();
            }
            grid[row - 1][col - 1] = true;
            int id = (row - 1) * size + col;
            // if this site is in first row, it should connect to the head;
            if (row == 1) {
                unionFind.union(0, id);
            }
            // if this site is in bottom row,, it should connected to the bottom;
            if (row == size) {
                unionFind.union(size * size + 1, id);
            }
            // if the neighbor site is open, connect them
            if (col > 1 && isOpen(row, col - 1)) {
                unionFind.union(id, id - 1);
            }
            if (col < size && isOpen(row, col + 1)) {
                unionFind.union(id, id + 1);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                unionFind.union(id, id - size);
            }
            if (row < size && isOpen(row + 1, col)) {
                unionFind.union(id, id + size);
            }
    }

    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!check(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return unionFind.connected((row - 1) * size + col, 0);
    }

    public boolean percolates() {
        // check if the head is connected with the bottom;
        return unionFind.connected(0, size * size + 1);
    }

    private boolean check(int row, int col) {
        return row > 0 && col > 0 && row <= size && col <= size;
    }
}