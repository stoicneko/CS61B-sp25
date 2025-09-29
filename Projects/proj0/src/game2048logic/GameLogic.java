package game2048logic;

import game2048rendering.Side;

import static game2048logic.MatrixUtils.*;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c     the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        while (r > 0) {
            // if (board[r][c] == 0) {
            //     return 0;
            // }
            // minR
            if (r == minR) {
                return 0;
            }
            if (board[r - 1][c] != 0) {
                // merge
                if (board[r - 1][c] == board[r][c]) {
                    board[r - 1][c] = board[r][c] + board[r][c]; // 是+不是*
                    board[r][c] = 0;
                    return r;
                }
                return 0;
            }
            // 要记得传递值
            board[r - 1][c] = board[r][c];
            board[r][c] = 0;
            r--;
        }
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // minR 怎么设置
        // moveTileUpAsFarAsPossible什么时候调用
        // 单独的判断语句
        int minR = 0;
        for (int i = 1; i < board.length; i++) {
            if (board[i - 1][c] * board[i][c] != 0) {
                if (board[i - 1][c] == board[i][c]) {
                    moveTileUpAsFarAsPossible(board, i, c, minR);
                    minR = i;
                }
            } else {
                moveTileUpAsFarAsPossible(board, i, c, minR);
            }
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            tiltColumn(board, i);
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            return;
        } else if (side == Side.SOUTH) {
            rotateLeft(board);
            rotateLeft(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
            return;
        } else {
            tiltUp(board);
            return;
        }
    }
}
