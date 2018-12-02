package hsh.problem.sop;

import com.hsh.parser.Dataset;
import hsh.problem.Swap;
import hsh.util.SOPUtil;

public class SOPTwoOptSwap extends Swap {
    @Override
    public int[] doSwap(int[] path, Dataset dataset) {
        return SOPUtil.repair(super.swap(path), dataset);
    }
}
