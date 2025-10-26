import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }


    @Test
    public void percolationHappensVertically() {
        int N = 3;
        Percolation p = new Percolation(N);

        // 打开第一列形成竖直通路
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);

        Cell[][] expectedState = {
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    @Test
    public void edgeOpenButNotFull() {
        int N = 4;
        Percolation p = new Percolation(N);

        // 仅打开最底层一行
        for (int c = 0; c < N; c++) {
            p.open(N - 1, c);
        }

        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.OPEN}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }


    @Test
    public void diagonalDoesNotPercolate() {
        int N = 3;
        Percolation p = new Percolation(N);

        // 打开对角线 (0,0), (1,1), (2,2)
        p.open(0, 0);
        p.open(1, 1);
        p.open(2, 2);

        Cell[][] expectedState = {
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.OPEN}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    /** 工具：断言状态矩阵中没有 INVALID（关闭但 isFull=true 的不可能状态） */
    private static void assertNoInvalid(Cell[][] state) {
        for (int r = 0; r < state.length; r++) {
            for (int c = 0; c < state[0].length; c++) {
                if (state[r][c] == Cell.INVALID) {
                    throw new AssertionError("Found INVALID at (" + r + "," + c + ")");
                }
            }
        }
    }

    /** 1) 构造器边界：N<=0 应抛出 IllegalArgumentException（如需改为 IndexOutOfBounds，可按你的实现调整） */
    @Test
    public void constructorRejectsNonPositiveN() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
        assertThrows(IllegalArgumentException.class, () -> new Percolation(-3));
    }

    /** 2) 坐标越界：isOpen/isFull/open 超出范围应抛异常（按课程常见实现为 IndexOutOfBoundsException 或 IllegalArgumentException） */
    @Test
    public void indexOutOfBounds() {
        Percolation p = new Percolation(3);
        assertThrows(IndexOutOfBoundsException.class, () -> p.isOpen(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> p.isOpen(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> p.isOpen(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> p.isOpen(0, 3));

        assertThrows(IndexOutOfBoundsException.class, () -> p.isFull(9, 9));
        assertThrows(IndexOutOfBoundsException.class, () -> p.open(9, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> p.open(0, 9));
    }

    /** 3) 幂等性与计数：重复打开同一格不应改变 numberOfOpenSites() */
    @Test
    public void idempotentOpenAndCounting() {
        Percolation p = new Percolation(4);
        assertThat(p.numberOfOpenSites()).isEqualTo(0);

        p.open(1, 1);
        assertThat(p.numberOfOpenSites()).isEqualTo(1);

        p.open(1, 1); // 再次打开同一格
        assertThat(p.numberOfOpenSites()).isEqualTo(1);

        p.open(0, 0);
        p.open(3, 3);
        assertThat(p.numberOfOpenSites()).isEqualTo(3);

        // 关闭格不应是 full
        Cell[][] s = getState(4, p);
        assertNoInvalid(s);
        assertThat(s[2][2]).isEqualTo(Cell.CLOSED);
    }

    /** 4) 顶行的开放格自动是 FULL（与 top 连通） */
    @Test
    public void topRowOpensAreFull() {
        int N = 5;
        Percolation p = new Percolation(N);

        for (int c = 0; c < N; c++) {
            p.open(0, c);
        }
        Cell[][] s = getState(N, p);
        for (int c = 0; c < N; c++) {
            assertThat(s[0][c]).isEqualTo(Cell.FULL);
        }
        assertNoInvalid(s);
        assertThat(p.percolates()).isTrue(); // 顶行整行开通自然渗透（若底行有连通可达）。此处不必强求，可按实现调整。
    }

    /** 5) 只开底行：不应渗透、不应 full（未与顶行连通） */
    @Test
    public void bottomRowOpenNotFullAndNoPercolation() {
        int N = 4;
        Percolation p = new Percolation(N);
        for (int c = 0; c < N; c++) {
            p.open(N - 1, c);
        }
        Cell[][] s = getState(N, p);
        for (int c = 0; c < N; c++) {
            assertThat(s[N - 1][c]).isEqualTo(Cell.OPEN);
        }
        assertNoInvalid(s);
        assertThat(p.percolates()).isFalse();
    }

    /** 6) 之字形连通：通过拐弯路径连到顶行，沿路 should be FULL，系统应渗透 */
    @Test
    public void zigzagPathBecomesFullAndPercolates() {
        int N = 5;
        Percolation p = new Percolation(N);

        // 构造一条从 (0,2) 向下“之”字的通路
        p.open(0, 2);
        p.open(1, 2);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 3);
        p.open(4, 3);

        Cell[][] s = getState(N, p);
        // 这些点都应 FULL（与顶行 (0,2) 连通）
        int[][] path = {
                {0,2},{1,2},{1,1},{2,1},{2,2},{3,2},{3,3},{4,3}
        };
        for (int[] rc : path) {
            assertThat(s[rc[0]][rc[1]]).isEqualTo(Cell.FULL);
        }
        assertNoInvalid(s);
        assertThat(p.percolates()).isTrue();
    }

    /** 7) 对角线仅相邻角落：不应误判为渗透（对角邻接非四联通） */
    @Test
    public void diagonalsAloneDoNotPercolate() {
        int N = 4;
        Percolation p = new Percolation(N);
        // 打开主对角与副对角，但没有四邻接连通
        for (int i = 0; i < N; i++) {
            p.open(i, i);
            p.open(i, N - 1 - i);
        }
        Cell[][] s = getState(N, p);
        assertNoInvalid(s);
        assertThat(p.percolates()).isFalse();
    }

    /** 8) 全开：所有格应为 OPEN 或 FULL，系统必须渗透 */
    @Test
    public void allOpenMustPercolate() {
        int N = 3;
        Percolation p = new Percolation(N);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                p.open(r, c);
            }
        }
        Cell[][] s = getState(N, p);
        assertNoInvalid(s);  // 绝不会出现 INVALID
        assertThat(p.numberOfOpenSites()).isEqualTo(N * N);
        assertThat(p.percolates()).isTrue();
    }

    /** 9) 局部连通性：打开一串，但中间断开，则断开处下方不应为 FULL */
    @Test
    public void localConnectivityStopsFullPropagation() {
        int N = 5;
        Percolation p = new Percolation(N);

        // 顶部开口在 (0,1)
        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 1);

        // 在 (3,1) 以下继续开，但与上方断开（故不应 full）
        p.open(4, 1);

        Cell[][] s = getState(N, p);
        // 上面 0..2 应 FULL
        assertThat(s[0][1]).isEqualTo(Cell.FULL);
        assertThat(s[1][1]).isEqualTo(Cell.FULL);
        assertThat(s[2][1]).isEqualTo(Cell.FULL);

        // (4,1) 为 OPEN 非 FULL
        assertThat(s[4][1]).isEqualTo(Cell.OPEN);

        assertNoInvalid(s);
        assertThat(p.percolates()).isFalse();
    }

    /** 10) 计数与渗透综合：构造最小渗透路径并检查 numberOfOpenSites() 精确值 */
    @Test
    public void minimalPercolationPathCounting() {
        int N = 4;
        Percolation p = new Percolation(N);

        // 打一条最短竖直路径
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);
        p.open(3, 0);

        // 以及几个无关点
        p.open(1, 2);
        p.open(2, 3);

        assertThat(p.numberOfOpenSites()).isEqualTo(6);

        Cell[][] s = getState(N, p);
        assertNoInvalid(s);
        // 竖直列应 FULL
        for (int r = 0; r < N; r++) {
            assertThat(s[r][0]).isEqualTo(Cell.FULL);
        }
        // 非连通的无关点应 OPEN 非 FULL
        assertThat(s[1][2]).isEqualTo(Cell.OPEN);
        assertThat(s[2][3]).isEqualTo(Cell.OPEN);

        assertThat(p.percolates()).isTrue();
    }
}
