package hsh.cs;

import com.hsh.Evaluable;
import hsh.csmath.LevyFlight;
import hsh.tsp.Mutation;

import java.util.ArrayList;


abstract class Cuckoo extends Evaluable {
    Egg egg;
    int[] path;
    int oldFitness;
    Mutation mutation;

    public Cuckoo(int[] path, int oldFitness, Mutation mutation) {
        this.path = path;
        this.oldFitness = oldFitness;
        this.egg = new Egg(path);
        this.mutation = mutation;
    }

    abstract public void makeFlight(int best, double c);

    double getNumberOfSteps(){
        return 1.0/5.0;
    }

    double getLastInterval(double numberOfSteps){
        return 1.0 - numberOfSteps;
    }

    double getNormalizedStepSize(int best, double c){
        double lowerBound = 0;
        double upperBound = 10000;

        double stepSize = Math.max(lowerBound, Math.min(upperBound,calculateStepSize(best,c)));
        double norm = (stepSize - lowerBound)/(upperBound - lowerBound);
        return (norm == 0.0) ? 0.05 : norm;
    }

    private double calculateStepSize(int best, double c){
        double lfValue = new LevyFlight().init().doubleValue();
        double diffToBestSolution = oldFitness - best;
        return c * lfValue * diffToBestSolution;
    }

    public Egg layEgg(){
        return egg;
    }

    @Override
    public ArrayList<Integer> getPath() {
        return egg.getPath();
    }
}