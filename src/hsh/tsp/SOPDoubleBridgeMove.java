package hsh.tsp;

import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

public class SOPDoubleBridgeMove  extends Move {

    @Override
    public int[] doMove(int[] path, Dataset dataset) {
        return SOPUtil.repair(super.move(path), dataset);
    }
}
