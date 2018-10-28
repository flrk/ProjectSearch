import com.hsh.Evaluable;
import com.hsh.Fitness;

import java.util.ArrayList;

public class Egg extends Evaluable{
    private final int[] path;

    public Egg(int[] solution){
        path = solution.clone();
    }

    @Override
    public ArrayList<Integer> getPath() {
        ArrayList<Integer> toReturn = new ArrayList<>();
        for(int id : path){
            toReturn.add(id);
        }
        return toReturn;
    }

    public int[] getPathAsArray(){
        return path.clone();
    }

}
