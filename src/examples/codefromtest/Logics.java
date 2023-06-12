package examples.codefromtest;
import helpers.Pair;
import java.util.List;

public interface Logics {
    boolean mineHit(Pair<Integer, Integer> coordinates);
    List<Pair<Integer, Integer>> computeAdjacentTiles(Pair<Integer, Integer> coordinates);
    List<Pair<Integer, Integer>> computeAdjacentMines(Pair<Integer, Integer> coordinates);
    void addCounters(Pair<Integer, Integer> coordinates);
    List<Pair<Integer, Integer>> getCounters();
    void addFlag(Pair<Integer, Integer> coordinates);
    List<Pair<Integer, Integer>> getFlags();
    boolean isGameWon();
}
