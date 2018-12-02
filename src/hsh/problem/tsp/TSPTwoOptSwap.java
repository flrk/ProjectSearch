package hsh.problem.tsp;

import com.hsh.parser.Dataset;
import hsh.problem.Swap;

public class TSPTwoOptSwap extends Swap {

    @Override
    public int[] doSwap(int[] path, Dataset dataset){
        return this.swap(path);
    }
}
