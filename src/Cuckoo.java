public class Cuckoo {
    private final Egg oldEgg;
    private final Egg bestEgg;
    private Egg newEgg;

    public Cuckoo(Egg oldEgg, Egg bestEgg){
        this.bestEgg = bestEgg;
        this.oldEgg = oldEgg;
    }

    public void makeFlight(){

        double stepSize = calculateStepSize();
        //System.out.println(stepSize);
        oldEgg.getPath();
        //TODO: transform oldEgg to newEgg via LevyFlight

    }

    private double calculateStepSize(){
        double lfValue = new  LevyFlight().init().doubleValue();
        double diffToBestSolution = oldEgg.getFitness() - bestEgg.getFitness();
        //System.out.println("Levy: "+lfValue+"; Diff: "+diffToBestSolution );
        return 0.01 * lfValue * diffToBestSolution;
    }

    public Egg layEgg(){
        newEgg = new Egg(new TSPSolution(oldEgg.getFitnessFunction().getDataset()).getNewRandomSolution(),oldEgg.getFitnessFunction());
        return newEgg;
    }
}