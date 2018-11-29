package hsh.cs;


import hsh.tsp.DoubleBridgeMove;

public class EscapingCuckoo extends Cuckoo {

    public EscapingCuckoo(int[] path, int oldFitness){
        this.path = path.clone();
        this.oldFitness = oldFitness;
    }

    @Override
    public void makeFlight(int best, double c){
        egg = new Egg(new DoubleBridgeMove().doMove(path));
    }
}
