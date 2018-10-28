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

        for(int i = 0; i < indices.length; i += 2){
            newPath[indices[i]] = path[indices[i+1]];
            newPath[indices[i+1]] = path[indices[i]];
        }
        return newPath;
    }
}
