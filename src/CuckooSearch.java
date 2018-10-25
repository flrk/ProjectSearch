import com.hsh.Fitness;


public class CuckooSearch {
    private final Nest[] nests;
    private final double probability;
    private final Fitness fitness;
    private final int generations;

    public CuckooSearch(int nestNumber, double probability, int generations, Fitness fitness){
        this.nests = new Nest[nestNumber];
        this.probability = probability;
        this.generations = generations;
        this.fitness = fitness;
    }

    public void findSolution(){
        initializeNests();
        int t = 0;
        while(t < generations){

            t++;
        }
    }

    private void initializeNests(){
        for (Nest nest : nests) {
            nest.setEgg(new Egg(getNewRandomSolution(), fitness));
        }
    }

    private int[] getNewRandomSolution(){
        //TODO: generate random hamiltonian circle
        return new int[fitness.getDataset().getSize()];
    }
}

