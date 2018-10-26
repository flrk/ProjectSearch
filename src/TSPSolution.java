import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TSPSolution {
    private final Dataset dataset;

    public TSPSolution(Dataset ds){
        this.dataset = ds;
    }

    public int[] getNewRandomSolution(){
        Random rand = new Random();
        ArrayList<Node> nodesList = new ArrayList<>(Arrays.asList(dataset.getNodes()));
        ArrayList<Integer> result = new ArrayList<>();
        while (!nodesList.isEmpty()){
            int index = rand.nextInt(nodesList.size());
            result.add(nodesList.get(index).getId());
            nodesList.remove(index);
        }

        int[] resultArray = new int[result.size()];

        for(int i = 0; i < result.size(); ++i){
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
