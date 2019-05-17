import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.String.format;

/***
 * Extraído de: https://rosettacode.org/wiki/Floyd-Warshall_algorithm
 *
 * Modifico: Pablo Sao
 * @version 16/05/2019
 */

public class FloydWarshall{

    public static void floydWarshall(int[][] weights, int numVertices, ArrayList ciudades) {

        double[][] dist = new double[numVertices + 1][numVertices + 1];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);



        // Agrego Pablo Sao - 16/05/2019
        for(int i = 0; i< numVertices; i++){
            for(int j=0; j< numVertices;j++){
                dist[i][j] = weights[i][j];
            }
        }

        /*for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];*/

        int[][] next = new int[numVertices + 1][numVertices + 1];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }

        for (int k = 0; k <= numVertices; k++)
            for (int i = 0; i <= numVertices; i++)
                for (int j = 0; j <= numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

        printResult(dist, next,ciudades);
    }

    public static void printResult(double[][] dist, int[][] next,ArrayList ciudades) {
        System.out.println("Ruta \t\t\t\tDist \t\t\t\tPath");
        for (int i = 0; i < (next.length-1); i++) {
            for (int j = 0; j < (next.length-1); j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;

                    int distancia = (int) dist[i][j];
                    if(distancia > 0) {
                        String path = format("%s -> %s \t\t%2d \t\t%s", ciudades.get(u-1), ciudades.get(v-1),
                                distancia, ciudades.get(u));
                        do {
                            u = next[u - 1][v - 1];
                            path += " -> " + ciudades.get(u-1);
                        } while (u != v);
                        System.out.println(path);
                    }
                }
            }
        }
    }
}
