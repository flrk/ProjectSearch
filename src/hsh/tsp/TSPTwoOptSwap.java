package hsh.tsp;

import com.hsh.parser.Dataset;

import java.util.Random;

public class TSPTwoOptSwap extends Swap{

    @Override
    public int[] doSwap(int[] path, Dataset dataset){
        return this.swap(path);
    }
}
