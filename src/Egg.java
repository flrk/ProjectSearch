import com.hsh.Evaluable;
import com.hsh.Fitness;

public class Egg {
    private final int NOT_CALCULATED = Integer.MIN_VALUE;
    private final int[] path;
    private final Fitness fitness;
    private int fitnessValue;


    public Egg(int[] solution, Fitness fitness){
        path = solution;
        this.fitness = fitness;
        fitnessValue = NOT_CALCULATED;
    }

    public int getFitness(){
        if(!isFitnessCalculated()){
            Evaluable result = fitness.evaluate(path, -1);
            fitnessValue = result.getFitness();
        }
        return fitnessValue;
    }

    public int[] getPath(){
        return path.clone();
    }

    public Fitness getFitnessFunction(){
        return fitness;
    }

    private boolean isFitnessCalculated(){
        return fitnessValue != NOT_CALCULATED;
    }
}
