import java.util.ArrayList;
import java.util.List;

public class Nest implements Comparable<Nest>{
    private final List<Egg> eggs;

    public Nest(){
        eggs = new ArrayList<>();
    }

    public Nest(Egg egg){
        this();
        eggs.add(egg);
    }

    public Egg getEgg(){
        return eggs.get(0);
    }

    public void setEgg(Egg newEgg){
        if(isNewEggBetter(newEgg)){
            eggs.set(0,newEgg);
        }
    }

    private boolean isNewEggBetter(Egg newEgg){
        return newEgg.getFitness() < eggs.get(0).getFitness();
    }

    @Override
    public int compareTo(Nest other) {
        return this.getEgg().getFitness() - other.getEgg().getFitness();
    }
}
