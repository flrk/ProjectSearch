package hsh.cs;


import com.hsh.Evaluable;
import com.hsh.Fitness;
import hsh.tsp.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Consumer;


public class CuckooSearch {
    private final ArrayList<Nest> nests;
    private final double probability;
    private final Fitness fitness;
    private final int generations;
    private final int numberOfNests;
    private final Random random;
    private Mutation mutation;
    private Solution solution;


    public CuckooSearch(int nestNumber, double probability, int generations, Fitness fitness){
        this.nests = new ArrayList<>();
        this.numberOfNests = nestNumber;
        this.probability = probability;
        this.generations = generations;
        this.fitness = fitness;
        this.random = new Random();
        if(fitness.getDataset().getType().equals("TSP")){
            this.solution = new TSPSolution(this.fitness.getDataset());
            this.mutation = new Mutation(new TSPTwoOptSwap(), new TSPDoubleBridgeMove(), this.fitness);
        }else{
            this.solution = new SOPSolution(this.fitness.getDataset());
            this.mutation = new Mutation(new SOPTwoOptSwap(), new SOPDoubleBridgeMove(), this.fitness);
        }
    }

    public void findSolution(){
        initializeNests();

        int t = 0;
        double c = 0.01;
        while(t < generations){
            c = 4*c*(1-c);
            final double cc = c;
            final int best = getBestNest().getEgg().getFitness();

            ArrayList<Cuckoo> cuckoos = hatchOut(best);
            cuckoos.stream().parallel().forEach(flight(cc, best));
            fitness.evaluate(getAllEggs());

            Collections.sort(nests);
            removeEggsDiscoveredByHost();
            Collections.sort(nests);
            t++;
        }
    }

    private Consumer<Cuckoo> flight(final double cc, final int best){
        return (Cuckoo cuckoo) -> {
            cuckoo.makeFlight(best, cc);
            Egg newEgg = cuckoo.layEgg();
            fitness.evaluate(newEgg, -1);
            getRandomNest().placeEgg(newEgg);
        };
    }

    private ArrayList<Cuckoo> hatchOut(int best){
        ArrayList<Cuckoo> cuckoos = new ArrayList<>();
        boolean firstBestNest = true;
        for(Nest n : nests){
            Cuckoo cuckoo;
            if(n.getEgg().getFitness() == best && firstBestNest){
                cuckoo = new NormalCuckoo(n.getEgg().getPathAsArray(), n.getEgg().getFitness(), mutation);
                firstBestNest = false;
            }else if(n.getEgg().getFitness() == best && !firstBestNest){
                cuckoo = new EscapingCuckoo(n.getEgg().getPathAsArray(), n.getEgg().getFitness(), mutation);
            }else{
                cuckoo = new SmartCuckoo(n.getEgg().getPathAsArray(), n.getEgg().getFitness(), fitness, mutation);
            }
            cuckoos.add(cuckoo);
        }
        return cuckoos;
    }

    private void initializeNests(){
        for (int i = 0; i < numberOfNests; ++i) {
            nests.add(new Nest(new Egg(this.solution.getNewRandomSolution())));
        }
        fitness.evaluate(getAllEggs());
        Collections.sort(nests);
    }

    private void removeEggsDiscoveredByHost() {
        for(int i = 1; i < nests.size(); ++i){
            if(random.nextDouble() < probability){
                Egg newEgg;
                if(random.nextDouble() < 0.6){
                    newEgg = new Egg(this.solution.getNewRandomSolution());
                }else{
                    newEgg = new Egg(getBestNest().getEgg().getPathAsArray());
                }
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
