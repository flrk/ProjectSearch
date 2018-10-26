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

        while(firstIndex == secondIndex){
            secondIndex = rand.nextInt(path.length);
        }

        newPath[firstIndex] = path[secondIndex];
        newPath[secondIndex] = path[firstIndex];

        return newPath;
    }
}
