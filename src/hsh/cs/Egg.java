package hsh.cs;

import com.hsh.Evaluable;

import java.util.ArrayList;

public class Egg extends Evaluable implements Comparable<Egg>{
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

    @Override
    public int compareTo(Egg other) {
        return this.getFitness() - other.getFitness();
    }

}
