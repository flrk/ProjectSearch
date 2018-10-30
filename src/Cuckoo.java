import com.hsh.Evaluable;

import java.util.ArrayList;

public class Cuckoo extends Evaluable {
    private Egg egg;
    private int[] path;
    private int oldFitness;

    public Cuckoo(int[] path, int oldFitness){
        this.path = path.clone();
        this.oldFitness = oldFitness;
    }

    public void makeFlight(int best){
        double lowerBound = -75;
        double upperBound = 75;

        double stepSize = Math.max(lowerBound, Math.min(upperBound,calculateStepSize(best)));
        double norm = (stepSize - lowerBound)/(upperBound - lowerBound);

        TwoOptSwap twoOptSwap = new TwoOptSwap();
        int[] newPath = path;
        if(norm <= 0.8){
            for(double i = 0.0; i < norm; i += 0.2){
                newPath = twoOptSwap.doSwap(newPath);
            }
        }else{

            newPath = new DoubleBridgeMove().doMove(newPath);
        }

        egg = new Egg(newPath);
    }

    private double calculateStepSize(int best){
        double lfValue = new  LevyFlight().init().doubleValue();
        double diffToBestSolution = oldFitness - best;
        //System.out.println("Levy: "+lfValue+"; Diff: "+diffToBestSolution + " " + (0.01 * lfValue * diffToBestSolution) );
        return 0.01 * lfValue * diffToBestSolution;
    }

    public Egg layEgg(){
        return egg;
    }


    @Override
    public ArrayList<Integer> getPath() {
        return egg.getPath();
    }
}