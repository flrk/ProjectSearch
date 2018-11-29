package hsh.tsp;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class DoubleBridgeMove {
    private final Random rand;

    public DoubleBridgeMove(){
        rand= new Random();
    }

    public int[] doMove(int[] path){
        int[] newPath = path.clone();
        int[] indices = new int[4];
        int step = path.length;

        TreeSet<Integer> indi = new TreeSet<>();

        while(indi.size() < 4){
            indi.add(rand.nextInt(step));
        }

        for(int i = 0; i< indices.length; ++i){
            if(i == 0){
                indices[i] = indi.pollFirst();
                continue;
            }
            int index = indi.pollFirst();
            indices[i] = ((indices[i - 1] + 2) <= index) ? index : (((index + 1) <= newPath.length) ? (index + 1) : newPath.length);
        }

        int[] t1 = Arrays.copyOfRange(newPath, 0, indices[0]);
        int[] t2 = Arrays.copyOfRange(newPath, indices[0], indices[1]);
        int[] t3 = Arrays.copyOfRange(newPath, indices[1], indices[2]);
        int[] t4 = Arrays.copyOfRange(newPath, indices[2], indices[3]);
        int[] t5 = Arrays.copyOfRange(newPath, indices[3], newPath.length);

        int[] result;

        if(rand.nextBoolean() == true){
            result = ArrayUtil.concat(t1,t4);
            int[] t3t4 = ArrayUtil.concat(t3, t2);
            result = ArrayUtil.concat(result, t3t4);
            result = ArrayUtil.concat(t5,result);
        }else{
            result = ArrayUtil.concat(t1,t3);
            int[] t4t2 = ArrayUtil.concat(t4, t2);
            result = ArrayUtil.concat(result, t4t2);
            result = ArrayUtil.concat(t5,result);

        }

        return result;
    }

}
