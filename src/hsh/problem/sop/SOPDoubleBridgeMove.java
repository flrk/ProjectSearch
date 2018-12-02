package hsh.problem.sop;

import com.hsh.parser.Dataset;
import hsh.problem.Move;
import hsh.util.SOPUtil;

public class SOPDoubleBridgeMove  extends Move {

    @Override
    public int[] doMove(int[] path, Dataset dataset) {
        return SOPUtil.repair(super.move(path), dataset);
    }
}
