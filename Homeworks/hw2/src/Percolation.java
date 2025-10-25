import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author stoicneko
 * @version 1.0
 * @since 2025.10.25
 **/

public class Percolation {
    // 不要害怕创建实例变量
    // 如何判断某个坐标打开与否?
    // 创建一个布尔变量啊
    WeightedQuickUnionUF uf; // 实例化
    boolean[][] open;
    int N;
    int top;
    int bottom;
    int openSites;

    public Percolation(int N) {
        // Create N-by-N grid, with all sites initially blocked
        // 没有任何思路是最难受的, 都是无效思考. 跟查询信息不同, 那起码有线索可以一点点查询
        // 二维坐标转为一维坐标
        this.N = N;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf1 = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        open = new boolean[N][N];
        top = N * N;
        bottom = N * N + 1;
        openSites = 0;
    }

    // 什么时候创建辅助method?
    private int idx(int row, int col) {
        return row * N + col;
    }

    private void checkNeighbors(int row, int col) {
        // 注意边界测试
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(idx(row, col), idx(row, col + 1));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(idx(row, col), idx(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(idx(row, col), idx(row, col - 1));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(idx(row, col), idx(row - 1, col));
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (row == 0) uf.union(idx(row, col), top);
        if (row == N - 1) uf.union(idx(row, col), bottom);
        if (!isOpen(row, col)) { // 不要重复计数
            open[row][col] = true;
            openSites += 1;
            checkNeighbors(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        // 注意不能等于 N
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        } else {
            if (uf.connected(idx(row, col), top)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        if (uf.connected(bottom, top)) return true;
        return false;
    }
}
