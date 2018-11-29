package hsh.tsp;

import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

import java.util.*;

public class TSPSolution extends Solution {
    public TSPSolution(Dataset ds) {
        super(ds);
    }

    @Override
    public int[] getNewRandomSolution(){

        List<Node> nodesList = new ArrayList<>(Arrays.asList(dataset.getNodes()));
        Collections.shuffle(nodesList);

        int[] resultArray = new int[nodesList.size()];

        for(int i = 0; i < nodesList.size(); ++i){
            resultArray[i] = nodesList.get(i).getId();
        }
        return resultArray;
    }
}
