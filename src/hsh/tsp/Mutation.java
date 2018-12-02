package hsh.tsp;

import com.hsh.Fitness;

public class Mutation {
    private final Swap swap;
    private final Move move;
    private final Fitness fitness;

    public Mutation(Swap swap, Move move, Fitness fitness) {
        this.swap = swap;
        this.move = move;
        this.fitness = fitness;
    }

    public int[] doMove(int[] array){
        return this.move.doMove(array, fitness.getDataset());
    }

    public int[] doSwap(int[] array){
        return this.swap.doSwap(array, fitness.getDataset());
    }
}
