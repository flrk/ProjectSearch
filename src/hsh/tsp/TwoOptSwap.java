package hsh.tsp;

import java.util.Random;

public class TwoOptSwap {
    private final Random rand;

    public TwoOptSwap(){
        rand = new Random();
    }

    public int[] doSwap(int[] path){
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
}
