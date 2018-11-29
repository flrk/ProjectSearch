package cs;

import java.util.ArrayList;
import java.util.List;

public class Nest implements Comparable<Nest>{
    private final List<Egg> eggs;

    public Nest() {
        eggs = new ArrayList<>();
    }

    public Nest(Egg egg){
        this();
        eggs.add(egg);
    }

    public Egg getEgg(){
        return eggs.get(0);
    }


    public void placeEgg(Egg newEgg){
        if(eggs.isEmpty()){
            eggs.add(newEgg);
        } else if(isNewEggBetter(newEgg.getFitness())){
            eggs.set(0,newEgg);
        }
    }

    public void removeEgg(){
        eggs.remove(0);
    }

    private boolean isNewEggBetter(int fitness){
        return fitness < eggs.get(0).getFitness();
    }

    @Override
    public int compareTo(Nest other) {
        if(other.getEgg().getFitness() == -1 || !other.getEgg().isValid()) return -1;
        if(this.getEgg().getFitness() == -1 || !this.getEgg().isValid()) return 1;
        return this.getEgg().getFitness() - other.getEgg().getFitness();
    }
}
