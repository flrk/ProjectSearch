package hsh.tsp;

import com.hsh.parser.Dataset;
import com.hsh.parser.Node;

public class SOPUtil {
    public static int[] repair(int[] path, Dataset dataset){
        int[] result = path.clone();
        for(int i = 0; i < path.length; i++){
            Node node = dataset.getNodeByID(result[i]);
            for(int y = i + 1; y < path.length; y++){
                Node node2 = dataset.getNodeByID(result[y]);
                if(node.distance(node2) == -1){

                    int tmp = result[i];
                    result[i] = result[y];
                    result[y] = tmp;
                    node = node2;
                    y = i;
                }
            }
        }
        return result;
    }
}
