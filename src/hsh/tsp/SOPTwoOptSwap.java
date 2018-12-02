package hsh.tsp;

import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

public class SOPTwoOptSwap extends Swap {
    @Override
    public int[] doSwap(int[] path, Dataset dataset) {
        return SOPUtil.repair(super.swap(path), dataset);
    }
}
