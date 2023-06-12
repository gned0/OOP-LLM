package examples.codefromtest;

import helpers.Pair;
import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    private final int size;
    private final List<Pair<Integer, Integer>> mines;
    private final List<Pair<Integer, Integer>> counters;
    private final List<Pair<Integer, Integer>> flags;

    public LogicsImpl(int size, List<Pair<Integer, Integer>> mines) {
        this.size = size;
        this.mines = mines;
        this.counters = new ArrayList<>();
        this.flags = new ArrayList<>();
    }

    @Override
    public boolean mineHit(Pair<Integer, Integer> coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x < 0 || y < 0 || x >= size || y >= size) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }

        return mines.contains(coordinates);
    }

    @Override
    public List<Pair<Integer, Integer>> computeAdjacentTiles(Pair<Integer, Integer> coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        List<Pair<Integer, Integer>> adjacentTiles = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (isValidCoordinate(newX, newY) && !(newX == x && newY == y)) {
                    adjacentTiles.add(new Pair<>(newX, newY));
                }
            }
        }

        return adjacentTiles;
    }

    @Override
    public List<Pair<Integer, Integer>> computeAdjacentMines(Pair<Integer, Integer> coordinates) {
        List<Pair<Integer, Integer>> adjacentMines = new ArrayList<>();

        for (Pair<Integer, Integer> mine : mines) {
            if (computeAdjacentTiles(coordinates).contains(mine)) {
                adjacentMines.add(mine);
            }
        }

        return adjacentMines;
    }

    @Override
    public void addCounters(Pair<Integer, Integer> coordinates) {
        counters.addAll(computeAdjacentTiles(coordinates));
    }

    @Override
    public List<Pair<Integer, Integer>> getCounters() {
        return counters;
    }

    @Override
    public void addFlag(Pair<Integer, Integer> coordinates) {
        flags.add(coordinates);
    }

    @Override
    public List<Pair<Integer, Integer>> getFlags() {
        return flags;
    }

    @Override
    public boolean isGameWon() {
        return counters.containsAll(mines);
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public int getSize() {
        return size;
    }
}
