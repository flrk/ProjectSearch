package hsh.problem;

import com.hsh.parser.Dataset;

public abstract class Solution {
    protected final Dataset dataset;
    public Solution(Dataset ds){
        this.dataset = ds;
    }

    abstract public int[] getNewRandomSolution();
}
