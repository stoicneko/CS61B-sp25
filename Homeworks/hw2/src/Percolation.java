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
    private final WeightedQuickUnionUF ufPercolation; // 实例化, 包含VT和VB
    private final WeightedQuickUnionUF ufNoBottom; // 实例化, 不包含VB

    private final boolean[][] open;

    private final int N;
    private final int top;
    private final int bottom;

    private int openSites;

    // 简化checkNeighbors
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Percolation(int N) {
        // Create N-by-N grid, with all sites initially blocked
        // 没有任何思路是最难受的, 都是无效思考. 跟查询信息不同, 那起码有线索可以一点点查询
        // 二维坐标转为一维坐标
        this.N = N;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        ufPercolation = new WeightedQuickUnionUF(N * N + 2);
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
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
        // 注意边界测试, 优化判断结构
        int i = idx(row, col);
        for (int[] d : DIRS) {
            int r = row + d[0];
            int c = col + d[1];
            if (r >= 0 && r < N && c >= 0 && c < N && isOpen(r, c)) {
                int j = idx(r, c);
                ufPercolation.union(i, j);
                ufNoBottom.union(i, j);
            }
        }
    }

    public void open(int row, int col) {
        int i = idx(row, col);
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (row == 0) {
            ufPercolation.union(i, top);
            ufNoBottom.union(i, top);
        }
        if (row == N - 1) {
            ufPercolation.union(i, bottom);
        }
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
        }
        // 如果用uf1这里会误判, backwash
        return ufNoBottom.connected(idx(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return ufPercolation.connected(bottom, top);
    }
}
