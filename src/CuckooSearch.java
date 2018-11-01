import com.hsh.Evaluable;
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

        int t = 0;
        double c = 0.01;
        while(t < generations){
            //System.out.println("Generation "+ t + "\n Best Fitness: " + getBestNest().getEgg().getFitness());
            c = 4*c*(1-c);
            //System.out.println("C: "+c);
            int best = getBestNest().getEgg().getFitness();
            for(Nest n: nests){
                Cuckoo cuckoo = new Cuckoo(n.getEgg().getPathAsArray(), n.getEgg().getFitness());
                cuckoo.makeFlight(best, c);
                Egg newEgg = cuckoo.layEgg();
                fitness.evaluate(newEgg, -1);
                getRandomNest().placeEgg(newEgg);

            }
            fitness.evaluate(getAllEggs());
            for(Evaluable egg : getAllEggs()){
                if(!egg.isValid()){
                    System.out.println(egg.getErrorCode());
                    System.out.println(egg.getPath());
                }
            }

            Collections.sort(nests);
            removeEggsDiscoveredByHost();
            Collections.sort(nests);
            t++;
        }

        System.out.println("Final Fitness: "+getBestNest().getEgg().getFitness());
        System.out.println("Final Path: "+ Arrays.toString(getBestNest().getEgg().getPathAsArray()));
    }

    private void initializeNests(){
        TSPSolution tspSolution = new TSPSolution(fitness.getDataset());
        for (int i = 0; i < numberOfNests; ++i) {
            nests.add(new Nest(new Egg(tspSolution.getNewRandomSolution())));
        }
        fitness.evaluate(getAllEggs());
        Collections.sort(nests);
    }

    private void removeEggsDiscoveredByHost() {
        TSPSolution tspSolution = new TSPSolution(fitness.getDataset());
        for(int i = 2; i < nests.size(); ++i){
            if(random.nextDouble() < probability){
                int[] newSolution = tspSolution.getNewRandomSolution();
                Egg newEgg = new Egg(newSolution);
                fitness.evaluate(newEgg,-1);
                nests.set(i, new Nest(newEgg));
            }
        }
    }

    private ArrayList<Evaluable> getAllEggs(){
        ArrayList<Evaluable> toReturn = new ArrayList<>();
        for(Nest nest : nests){
            toReturn.add(nest.getEgg());
        }
        return toReturn;
    }

    private Nest getRandomNest(){
        return nests.get(random.nextInt(nests.size()));
    }

    private Nest getBestNest(){
        return nests.get(0);
    }
}

