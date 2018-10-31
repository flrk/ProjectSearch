import com.hsh.Fitness;
import com.hsh.parser.Dataset;
import com.hsh.parser.Parser;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        Dataset dataset = null;

        try{
            dataset = Parser.read("ressources/a280.tsp");
        }catch(IOException e){
            e.printStackTrace();
        }

        Fitness fitness = new Fitness(dataset, false);

        CuckooSearch cs = new CuckooSearch(25,0.25,1000,fitness);
        cs.findSolution();
    }

}


