import com.hsh.Fitness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class CuckooSearch {
    private final ArrayList<Nest> nests;
    private final double probability;
    private final Fitness fitness;
    private final int generations;
    private final int numberOfNests;
    private final Random random;

    public CuckooSearch(int nestNumber, double probability, int generations, Fitness fitness){
        this.nests = new ArrayList<>();
        this.numberOfNests = nestNumber;
        this.probability = probability;
        this.generations = generations;
        this.fitness = fitness;
        this.random = new Random();
    }

    public void findSolution(){
        initializeNests();
        int t = 0;
        while(t < generations){
            /*Cuckoo cuckoo = new Cuckoo(getRandomNest().getEgg());
            cuckoo.makeFlight();
            getRandomNest().setEgg(cuckoo.layEgg());*/
            Collections.sort(nests);
            t = generations;
        }
    }

    private void initializeNests(){
        TSPSolution tspSolution = new TSPSolution(fitness.getDataset());
        for (int i = 0; i < numberOfNests; ++i) {
            nests.add(new Nest(new Egg(tspSolution.getNewRandomSolution(), fitness)));
        }
    }

    private Nest getRandomNest(){
        return nests.get(random.nextInt(nests.size()));
    }
}

