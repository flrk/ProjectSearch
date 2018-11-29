package cs;

import tsp.DoubleBridgeMove;
import tsp.TwoOptSwap;
import com.hsh.Fitness;

public class SmartCuckoo extends Cuckoo {
    private Fitness fitness;

    public SmartCuckoo(int[] path, int oldFitness, Fitness fitness){
        this.path = path.clone();
        this.oldFitness = oldFitness;
        this.fitness = fitness;
    }

    @Override
    public void makeFlight(int best, double c){
        int[] currentBestPath = path.clone();
        int currentBestFitness = best;

        double steps = getNumberOfSteps();
        double lastInterval = getLastInterval(steps);

        for(int j = 0; j < 10; ++j){
            double norm = getNormalizedStepSize(best,c);

            int[] newPath = path.clone();
            if(norm <= lastInterval){
                TwoOptSwap twoOptSwap = new TwoOptSwap();
                for(double i = 0.0; i < norm; i += steps){
                    newPath = twoOptSwap.doSwap(newPath);
                }
            }else{
                newPath = new DoubleBridgeMove().doMove(newPath);
            }
            Egg egg = new Egg(newPath);
            fitness.evaluate(egg, -1);
            if(currentBestFitness > egg.getFitness()){
                currentBestFitness = egg.getFitness();
                currentBestPath = newPath;
            }
        }

        egg = new Egg(currentBestPath);
    }
}
