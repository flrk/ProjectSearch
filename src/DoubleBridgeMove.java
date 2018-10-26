import java.util.Random;

public class DoubleBridgeMove {
    private final Random rand;

    public DoubleBridgeMove(){
        rand= new Random();
    }

    public int[] doMove(int[] path){
        int[] newPath = path.clone();
        int[] indices = new int[4];

        for(int i = 0; i < indices.length; ++i){
           indices[i] = rand.nextInt(path.length);
        }

        for(int i = 0; i < indices.length; i += 2){
            newPath[indices[i]] = path[indices[i+1]];
            newPath[indices[i+1]] = path[indices[i]];
        }
        return newPath;
    }
}
