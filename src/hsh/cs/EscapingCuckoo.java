package hsh.cs;


import hsh.tsp.Mutation;

public class EscapingCuckoo extends Cuckoo {

    public EscapingCuckoo(int[] path, int oldFitness, Mutation mutation){
        super(path, oldFitness, mutation);
    }

    @Override
    public void makeFlight(int best, double c){
        egg = new Egg(mutation.doMove(path));
    }
}
