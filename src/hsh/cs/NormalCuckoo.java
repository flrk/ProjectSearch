package hsh.cs;


import hsh.tsp.Mutation;

public class NormalCuckoo extends Cuckoo {

    public NormalCuckoo(int[] path, int oldFitness, Mutation mutation){
        super(path, oldFitness, mutation);
    }

    @Override
    public void makeFlight(int best, double c){

        double norm = getNormalizedStepSize(best,c);
        double steps = getNumberOfSteps();
        double lastInterval = getLastInterval(steps);

        int[] newPath = path;
        if(norm <= lastInterval){
            for(double i = 0.0; i < norm; i += steps){
                newPath = mutation.doSwap(newPath);
            }
        }else{
            newPath = mutation.doMove(newPath);
        }

        egg = new Egg(newPath);
    }
}
