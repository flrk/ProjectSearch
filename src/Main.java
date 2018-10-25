import com.hsh.Fitness;
import com.hsh.parser.Dataset;
import com.hsh.parser.Node;
import com.hsh.parser.Parser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Dataset dataset = null;

        try{
            dataset = Parser.read("ressources\\a280.tsp");
        }catch(IOException e){
            e.printStackTrace();
        }

        Fitness fitness = new Fitness(dataset);

        CuckooSearch cs = new CuckooSearch(25,0.25,500,fitness);

        for(int i =0; i < 40; ++i){
            System.out.println(new LevyFlight().init().doubleValue());
        }



    }
}


