import java.util.Arrays;
import java.util.Random;

public class DoubleBridgeMove {
    private final Random rand;

    public DoubleBridgeMove(){
        rand= new Random();
    }

    public int[] doMove(int[] path){
        int[] newPath = path.clone();
        int[] indices = new int[4];
        int step = path.length/indices.length;

        for(int i = 0; i < indices.length; ++i){
            if(i == 0){
                indices[i] = rand.nextInt(step);
                continue;
            }
            int random = rand.nextInt(step);
            random = random == 0 ? 1 : random;
            indices[i] = indices[i - 1] + random;
        }

        int[] t1 = Arrays.copyOfRange(newPath, 0, indices[0]);
        int[] t2 = Arrays.copyOfRange(newPath, indices[2], newPath.length);
        int[] t3 = Arrays.copyOfRange(newPath, indices[1], indices[2]);
        int[] t4 = Arrays.copyOfRange(newPath, indices[0], indices[1]);

        int[] result = ArrayUtil.concat(t1,t2);
        int[] t3t4 = ArrayUtil.concat(t3, t4);
        result = ArrayUtil.concat(result, t3t4);

        return result;
    }

}
