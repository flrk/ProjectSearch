import com.hsh.Evaluable;

import java.util.ArrayList;
import java.util.Arrays;

public class Cuckoo extends Evaluable {
    private Egg egg;
    private int[] path;
    private int oldFitness;

    public Cuckoo(int[] path, int oldFitness){
        this.path = path.clone();
        this.oldFitness = oldFitness;
    }

    public void makeFlight(int best, double c){
        double lowerBound = 0;
        double upperBound = 10000;

        double stepSize = Math.max(lowerBound, Math.min(upperBound,calculateStepSize(best,c)));
        double norm = (stepSize - lowerBound)/(upperBound - lowerBound);
        norm = (norm == 0.0) ? 0.05 : norm;

        double steps = 1.0/5.0;
        double lastInterval = 1.0 - steps;

        int[] newPath = path;
        if(norm <= lastInterval){
            TwoOptSwap twoOptSwap = new TwoOptSwap();
            for(double i = 0.0; i < norm; i += steps){
                newPath = twoOptSwap.doSwap(newPath);
            }
        }else{
            newPath = new DoubleBridgeMove().doMove(newPath);
        }

        egg = new Egg(newPath);
    }

    private double calculateStepSize(int best, double c){
        double lfValue = new  LevyFlight().init().doubleValue();
        lfValue = Math.abs(lfValue);
        double diffToBestSolution = oldFitness - best;
        //System.out.println("Levy: "+lfValue+"; Old: "+oldFitness+"; Diff: "+diffToBestSolution + " " + (0.01 * lfValue * diffToBestSolution) );
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