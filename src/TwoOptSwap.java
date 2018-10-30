import java.util.Arrays;
import java.util.Random;

public class TwoOptSwap {
    private final Random rand;

    public TwoOptSwap(){
        rand = new Random();
    }

    public int[] doSwap(int[] path){

        int[] newPath = path.clone();
        int firstIndex = rand.nextInt(path.length - 1) + 1;
        int secondIndex = rand.nextInt(path.length - 1) + 1;

        while(firstIndex == secondIndex){
            secondIndex = rand.nextInt(path.length - 1) + 1;
        }

        if(firstIndex > secondIndex){
            int tmp = secondIndex;
            secondIndex = firstIndex;
            firstIndex = tmp;
        }

        int[] t1 = Arrays.copyOfRange(path,0, firstIndex);
        int[] t2 = Arrays.copyOfRange(path, firstIndex, secondIndex);
        ArrayUtil.reverse(t2);
        int[] t3 = Arrays.copyOfRange(path, secondIndex, path.length);

        newPath = ArrayUtil.concat(t1,t2);
        newPath = ArrayUtil.concat(newPath, t3);

        return newPath;
    }



}
