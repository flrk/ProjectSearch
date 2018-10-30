import com.hsh.Fitness;
import com.hsh.parser.Dataset;
import com.hsh.parser.Node;
import com.hsh.parser.Parser;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Dataset dataset = null;

        try{
            dataset = Parser.read("ressources/a280.tsp");
        }catch(IOException e){
            e.printStackTrace();
        }

        Fitness fitness = new Fitness(dataset, false);

        CuckooSearch cs = new CuckooSearch(25,0.25,15000,fitness);
        cs.findSolution();
    }
}


