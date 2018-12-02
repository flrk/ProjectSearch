package hsh.tsp;

import com.hsh.parser.Dataset;

import java.util.Random;

public abstract class Swap {
    private final Random rand;

    public Swap() {
        this.rand = new Random();
    }

    protected int[] swap(int path[]){
        int[] newPath = path.clone();
        int firstIndex = rand.nextInt(path.length);
        int secondIndex = rand.nextInt(path.length);

        while(Math.abs(firstIndex-secondIndex) <= 1){
            secondIndex = rand.nextInt(path.length);
        }

        if(firstIndex > secondIndex){
            int tmp = secondIndex;
            secondIndex = firstIndex;
            firstIndex = tmp;
        }

        ArrayUtil.reversePart(newPath,firstIndex,secondIndex);
        return newPath;
    }

    abstract public int[] doSwap(int[] path, Dataset dataset);
}
