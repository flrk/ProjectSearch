import com.hsh.Fitness;

import java.util.ArrayList;
import java.util.Arrays;
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
        Collections.sort(nests);

        int t = 0;
        while(t < generations){
            System.out.println("Generation "+ t + "\n Best Fitness: " + getBestNest().getEgg().getFitness());
            for(Nest n: nests){
                Cuckoo cuckoo = new Cuckoo(n.getEgg(), getBestNest().getEgg());
                cuckoo.makeFlight();
                getRandomNest().setEgg(cuckoo.layEgg());

            }
            Collections.sort(nests);
            removeEggsDiscoveredByHost();
            Collections.sort(nests);
            t++;
        }

        System.out.println("Final Fitness: "+getBestNest().getEgg().getFitness());
        System.out.println("Final Fitness: "+ Arrays.toString(getBestNest().getEgg().getPath()));
    }

    private void initializeNests(){
        TSPSolution tspSolution = new TSPSolution(fitness.getDataset());
        for (int i = 0; i < numberOfNests; ++i) {
            nests.add(new Nest(new Egg(tspSolution.getNewRandomSolution(), fitness)));
        }
    }

    private void removeEggsDiscoveredByHost() {
        TSPSolution tspSolution = new TSPSolution(fitness.getDataset());
        for(int i = 1; i < nests.size(); ++i){
            if(random.nextDouble() < probability){
                int[] newSolution = tspSolution.getNewRandomSolution();
                Egg newEgg = new Egg(newSolution, fitness);
                Nest nest = nests.get(i);
                nest.removeEgg();
                nest.setEgg(newEgg);
            }
        }
    }

    private Nest getRandomNest(){
        return nests.get(random.nextInt(nests.size()));
    }

    private Nest getBestNest(){
        return nests.get(0);
    }
}

