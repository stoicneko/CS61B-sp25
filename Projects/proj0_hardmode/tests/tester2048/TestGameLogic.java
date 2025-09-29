package tester2048;

import game2048logic.GameLogic;
import game2048rendering.Side;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;
import static tester2048.TestUtils.boardToString;
import static tester2048.TestUtils.checkTilt;

/** Tests the tilt() method in all directions.
 *
 * @author Josh Hug, Omar Khan, Erik Kizior, Josh Hug
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGameLogic {
    /*
     * ******************
     * *  TESTING TILT  *
     * ******************
     * <p>
     * The following tests determine the correctness of your `tilt`
     * method.
     */


    /** Checks that a tilt that causes no change returns false. */
    @Test
    @Tag("gamelogic")
    @Order(1)
    @DisplayName("Test invalid tilt output")
    public void testNoMove() {
        int[][] board = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move tiles up (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(2)
    @DisplayName("Test Up tilt")
    public void testUpNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles up (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(3)
    @DisplayName("Adjacent Up tilt")
    public void testUpAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move non-adjacent tiles up (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(4)
    @DisplayName("Up tilt with gap")
    public void testUpNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move non-adjacent tiles up (no merging); case 2: both tiles move. */
    @Test
    @Tag("gamelogic")
    @Order(5)
    @DisplayName("Up tilt with gaps")
    public void testMoveUpNonAdjacentNoMerge2() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }
    /** Move tiles right (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(6)
    @DisplayName("Right tilt")
    public void testRightNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 2, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 2},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles right (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(7)
    @DisplayName("Adjacent right tilt")
    public void testRightAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 4, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles right (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(8)
    @DisplayName("Adjacent right tilt with gap")
    public void testRightNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 4},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles right (no merging); case 2: both tiles move. */
    @Test
    @Tag("gamelogic")
    @Order(9)
    @DisplayName("Adjacent right tilt with gaps")
    public void testRightNonAdjacentNoMerge2() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 4, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move tiles down (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(10)
    @DisplayName("Down tilt")
    public void testDownNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 4, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 4, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles down (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(11)
    @DisplayName("Adjacent down tilt")
    public void testDownAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move non-adjacent tiles down (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(12)
    @DisplayName("Down tilt with gaps")
    public void testDownNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    @Test
    @Tag("gamelogic")
    @Order(13)
    @DisplayName("Left tilt")
    public void testLeftNoMerge() {
        int[][] board = new int[][]{
                {4, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move adjacent tiles left (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(14)
    @DisplayName("Adjacent left tilt")
    public void testLeftAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 4, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** Move non-adjacent tiles left (no merging). */
    @Test
    @Tag("gamelogic")
    @Order(15)
    @DisplayName("Left tilt with gaps")
    public void testLeftNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("Boards should match:").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /*
     * ******************
     * *  TESTING TILT  *
     * ******************
     * <p>
     * The following tests determine the correctness of your `tilt`
     * method.
     */

    /** Checks that the right two pieces merge when 3 adjacent pieces have same value. */
    @Test
    @Tag("gamelogic")
    @Order(16)
    @DisplayName("3 tile merge")
    public void testTripleMerge1() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Checks that the right two pieces merge when 3 adjacent pieces have same value. */
    @Test
    @Tag("gamelogic")
    @Order(17)
    @DisplayName("3 tile merge")
    public void testTripleMerge2() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Checks two adjacent merges work. */
    @Test
    @Tag("gamelogic")
    @Order(18)
    @DisplayName("adjacent merge")
    public void testQuadrupleMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("gamelogic")
    @Order(19)
    @DisplayName("One merge per North tilt")
    public void testSingleMergeUp() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("gamelogic")
    @Order(20)
    @DisplayName("One merge per South tilt")
    public void testSingleMergeSouth() {
        int[][] before = new int[][]{
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("gamelogic")
    @Order(21)
    @DisplayName("One merge per East tilt")
    public void testSingleMergeEast() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 2, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 4},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("gamelogic")
    @Order(22)
    @DisplayName("One merge per West tilt")
    public void testSingleMergeWest() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 2, 0, 4},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 4, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }


    /** Merge adjacent tiles up. */
    @Test
    @Tag("gamelogic")
    @Order(23)
    @DisplayName("Up tilt with merge")
    public void testUpAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Merge non-adjacent tiles up. */
    @Test
    @Tag("gamelogic")
    @Order(24)
    @DisplayName("Up tilt with gap and merge")
    public void testUpNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Move and merge adjacent tiles up. */
    @Test
    @Tag("gamelogic")
    @Order(25)
    @DisplayName("Up tilt with gaps and merge")
    public void testUpAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** Merge adjacent tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(26)
    @DisplayName("Adjacent right merge")
    public void testRightAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Merge non-adjacent tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(27)
    @DisplayName("Right merge with gap")
    public void testRightNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Move and merge adjacent tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(28)
    @DisplayName("Adjacent merge with gaps")
    public void testRightAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Move and merge non-adjacent tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(29)
    @DisplayName("Right merge with gaps")
    public void testRightNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Don't merge non-adjacent divided tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(30)
    @DisplayName("Right no merge with gaps")
    public void testRightNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 4, 0, 2},
                {2, 0, 4, 2},
                {2, 4, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** Merge adjacent tiles down. */
    @Test
    @Tag("gamelogic")
    @Order(31)
    @DisplayName("Adjacent down merge")
    public void testDownAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Merge non-adjacent tiles down. */
    @Test
    @Tag("gamelogic")
    @Order(32)
    @DisplayName("Down merge")
    public void testDownNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Move and merge adjacent tiles down. */
    @Test
    @Tag("gamelogic")
    @Order(33)
    @DisplayName("Adjacent down move and merge")
    public void testDownAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Move and merge non-adjacent tiles down. */
    @Test
    @Tag("gamelogic")
    @Order(34)
    @DisplayName("Down move and merge")
    public void testDownNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Don't merge non-adjacent divided tiles right. */
    @Test
    @Tag("gamelogic")
    @Order(35)
    @DisplayName("Down no merge with gaps")
    public void testDownNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 2, 2, 0},
                {4, 0, 4, 0},
                {2, 4, 0, 0},
                {0, 2, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 0},
                {4, 4, 4, 0},
                {2, 2, 2, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Merge adjacent tiles left. */
    @Test
    @Tag("gamelogic")
    @Order(36)
    @DisplayName("Left adjacent merge")
    public void testLeftAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** Merge non-adjacent tiles left. */
    @Test
    @Tag("gamelogic")
    @Order(37)
    @DisplayName("Left merge")
    public void testLeftNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** Move and merge adjacent tiles left. */
    @Test
    @Tag("gamelogic")
    @Order(38)
    @DisplayName("Adjacent merge and move")
    public void testLeftAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** Move and merge non-adjacent tiles left. */
    @Test
    @Tag("gamelogic")
    @Order(39)
    @DisplayName("Merge and move with gaps")
    public void testLeftNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /*
     * *************************
     * *****  N X N TESTS  *****
     * *************************
     * <p>
     * The following tests will call the `tilt` method on boards of different sizes.
     */

    /** Tilting an empty 1 by 1 */
    @Test
    @Tag("gamelogic")
    @Order(40)
    @DisplayName("The ants go marching")
    public void testOne() {
        int[][] before = new int[][] {
                {0},
        };
        int[][] after = new int[][] {
                {0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    @Test
    @Tag("gamelogic")
    @Order(41)
    @DisplayName("Non-merged tilts for N = 1, 2, 3")
    public void testSmallNonMergedTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {0},
        };
        after = new int[][] {
                {0},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {0, 0},
                {2, 2},
        };
        after = new int[][] {
                {2, 2},
                {0, 0},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {0, 2},
                {2, 0},
        };
        after = new int[][] {
                {2, 0},
                {2, 0},
        };
        checkTilt(before, after, Side.WEST);

        before = new int[][] {
                {4, 0, 4},
                {2, 16, 2},
                {0, 0, 8},
        };
        after = new int[][] {
                {0, 0, 4},
                {4, 0, 2},
                {2, 16, 8},
        };
        checkTilt(before, after, Side.SOUTH);
    }


    /** Tilts for N = 1, 2, 3 */
    @Test
    @Tag("gamelogic")
    @Order(42)
    @DisplayName("Tilts for N = 1, 2, 3")
    public void testSmallTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {4},
        };
        after = new int[][] {
                {4},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {2, 2},
                {0, 2},
        };
        after = new int[][] {
                {4, 0},
                {2, 0},
        };
        checkTilt(before, after, Side.WEST);

        before = new int[][] {
                {8, 0, 2},
                {0, 0, 2},
                {0, 0, 2},
        };
        after = new int[][] {
                {0, 0, 0},
                {0, 0, 2},
                {8, 0, 4},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** Tilt and gameOver for N = 20 */
    @Test
    @Tag("gamelogic")
    @Order(43)
    public void testLarge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 4, 4, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        checkTilt(before, after, Side.WEST);
    }
}

