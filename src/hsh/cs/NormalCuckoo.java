package hsh.cs;


import hsh.tsp.DoubleBridgeMove;
import hsh.tsp.TwoOptSwap;

public class NormalCuckoo extends Cuckoo {

    public NormalCuckoo(int[] path, int oldFitness){
        this.path = path.clone();
        this.oldFitness = oldFitness;
    }

    @Override
    public void makeFlight(int best, double c){

        double norm = getNormalizedStepSize(best,c);
        double steps = getNumberOfSteps();
        double lastInterval = getLastInterval(steps);

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
}
