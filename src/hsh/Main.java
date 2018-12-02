package hsh;

import com.hsh.Fitness;
import com.hsh.parser.Dataset;
import com.hsh.parser.Parser;
import hsh.cs.CuckooSearch;
import hsh.tsp.Mutation;
import hsh.tsp.SOPDoubleBridgeMove;
import hsh.tsp.SOPSolution;
import hsh.tsp.SOPTwoOptSwap;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Es muss ein Weg zum Datensatz übergeben werden!");
            System.exit(1 );
        }

        Dataset dataset = null;

        try{
            dataset = Parser.read(args[0]);
        }catch(IOException e){
            e.printStackTrace();
        }

        Fitness fitness = new Fitness(dataset, false);



        CuckooSearch cs = new CuckooSearch(75,0.25,2000, fitness);
        cs.findSolution();

        fitness.finish();
    }
}


