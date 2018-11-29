package hsh.tsp;

import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class SOPSolution extends Solution{
    public SOPSolution(Dataset ds) {
        super(ds);
    }

    @Override
    public int[] getNewRandomSolution() {
        List<Node> nodesList = new ArrayList<>(Arrays.asList(dataset.getNodes()));
        ArrayList<Node> path = new ArrayList<>();
        int[] result = new int[nodesList.size()];

        path.add(nodesList.get(0));
        for(Node n : dataset.getNodes()){
            if(n.getId() != 1 && n.getId() != nodesList.size()) {
                int start = 0;
                int end = path.size();
                for (int i = 0; i < path.size(); i++) {
                    Node inPath = path.get(i);
                    if (n.distance(inPath) == -1) {
                        start = i + 1;
                    }
                    if (inPath.distance(n) == -1) { // n has to be before inPath
                        end = i;
                        break;
                    }
                }
                int randomIndex = (int) ((Math.random() * (end - start)) + start);
                if (randomIndex == 0) {
                    path.add(n);
                } else {
                    path.add(randomIndex, n);
                }
            }
        }
        path.add(nodesList.get(nodesList.size()-1));

        int index = 0;
        for(Node n : path){
            result[index++] = n.getId();
        }
        return result;
    }
}
