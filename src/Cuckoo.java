public class Cuckoo {
    private final Egg oldEgg;
    private final Egg bestEgg;
    private Egg newEgg;

    public Cuckoo(Egg oldEgg, Egg bestEgg){
        this.bestEgg = bestEgg;
        this.oldEgg = oldEgg;
    }

    public void makeFlight(){
        double lowerBound = -200;
        double upperBound = 200;

        double stepSize = Math.max(lowerBound, Math.min(upperBound,calculateStepSize()));

        double norm = (stepSize - lowerBound)/(upperBound - lowerBound);

        TwoOptSwap twoOptSwap = new TwoOptSwap();
        int[] newPath = oldEgg.getPath();
        if(norm < 0.8){
            for(double i = 0.0; i < norm; i += 0.2){
                newPath = twoOptSwap.doSwap(newPath);
            }
        }else{
            newPath = new DoubleBridgeMove().doMove(newPath);
        }

        newEgg = new Egg(newPath,oldEgg.getFitnessFunction());


    }

    private double calculateStepSize(){
        double lfValue = new  LevyFlight().init().doubleValue();
        double diffToBestSolution = oldEgg.getFitness() - bestEgg.getFitness();
        //System.out.println("Levy: "+lfValue+"; Diff: "+diffToBestSolution );
        return 0.01 * lfValue * diffToBestSolution;
    }

    public Egg layEgg(){
        newEgg = new Egg(newEgg.getPath() ,oldEgg.getFitnessFunction());
        return newEgg;
    }
}