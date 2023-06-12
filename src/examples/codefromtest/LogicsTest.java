package examples.codefromtest;

import helpers.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogicsTest {

    private final static int SIZE = 3;
    private final static int NUMBER_OF_MINES = 1;
    private Logics logics;
    private List<Pair<Integer, Integer>> mines;

    @BeforeEach
    void setUp() {
        mines = new ArrayList<Pair<Integer, Integer>>();
        mines.add(new Pair<>(0, 0));
        logics = new LogicsImpl(SIZE, mines);
    }

    @Test
    void testMineHit() {
        assertTrue(logics.mineHit(new Pair<>(0, 0)));
    }
    @Test
    void testMineMiss() {
        assertFalse(logics.mineHit(new Pair<>(0, 1)));
    }

    @Test
    void testBoundaries() {
        assertThrows(IndexOutOfBoundsException.class, ()-> logics.mineHit(new Pair<>(-1, -1)));
        assertThrows(IndexOutOfBoundsException.class, ()-> logics.mineHit(new Pair<>(SIZE+1, SIZE+1)));
    }

    @Test
    void testComputeAdjacentTiles() {
        List<Pair<Integer, Integer>> adjacentTiles = new ArrayList<Pair<Integer, Integer>>();
        adjacentTiles.add(new Pair<>(0, 1));
        adjacentTiles.add(new Pair<>(1, 0));
        adjacentTiles.add(new Pair<>(1, 1));
        assertEquals(adjacentTiles, this.logics.computeAdjacentTiles(new Pair<>(0, 0)));
    }

    @Test
    void testComputeAdjacentMines() {
        assertEquals(1, logics.computeAdjacentMines(new Pair<>(0, 1)).size());
        assertEquals(mines, logics.computeAdjacentMines(new Pair<>(0, 1)));
    }

    @Test
    void testRecursiveCountersGeneration() {
        this.logics.addCounters(new Pair<>(2, 2));
        assertEquals(8, this.logics.getCounters().size());
    }

    @Test
    void testFlagPlacement() {
        this.logics.addFlag(new Pair<>(2, 2));
        assertEquals(1, this.logics.getFlags().size());
    }

    @Test
    void testWindCondition() {
        this.logics.addCounters(new Pair<>(2, 2));
        assertTrue(this.logics.isGameWon());
    }
}