public class Cuckoo {
    private final Egg oldEgg;
    private Egg newEgg;

    public Cuckoo(Egg egg){
        oldEgg = egg;
    }

    public Egg makeFlight(){
        //TODO: transform oldEgg to newEgg via LevyFlight
        return newEgg;
    }
}