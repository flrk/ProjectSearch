package hsh;

import com.hsh.Fitness;
import com.hsh.parser.Dataset;
import com.hsh.parser.Parser;
import hsh.cs.CuckooSearch;
import hsh.tsp.SOPSolution;

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

        SOPSolution sopSolution = new SOPSolution(dataset);

        for(int i = 0; i < 100; i++){
            int[] test = sopSolution.getNewRandomSolution();
            fitness.evaluate(test, 1);
        }

        System.out.println(fitness.getAbsolutBest());





       //
       // CuckooSearch cs = new CuckooSearch(25,0.25,500, fitness);
       // cs.findSolution();
    }
}


