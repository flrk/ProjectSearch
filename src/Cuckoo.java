public class Cuckoo {
    private final Egg oldEgg;
    private Egg newEgg;

    public Cuckoo(Egg egg){
        oldEgg = egg;
    }

    public void makeFlight(){
        double lfValue = new  LevyFlight().init().doubleValue();
        //TODO: get best Egg
        double stepsize = 0.01 * lfValue; //(oldEgg.getFitness() - bestEgg.getFitness());
        System.out.println(stepsize);
        //TODO: transform oldEgg to newEgg via LevyFlight

    }

    public Egg layEgg(){
        return newEgg;
    }
}