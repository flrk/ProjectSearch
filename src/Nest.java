import java.util.ArrayList;
import java.util.List;

public class Nest {
    private final List<Egg> eggs;

    public Nest(){
        eggs = new ArrayList<>();
    }

    public Egg getEgg(){
        return eggs.get(0);
    }

    public void setEgg(Egg newEgg){
        eggs.set(0,newEgg);
    }
}
