import java.util.ArrayList;

/***
 * Clase para creación de grafo y matriz de adyacencia
 * @author Pablo Sao
 * @version 15/05/2019
 */

class Edge {
    int startVertex;
    int endVertex;
    int weight;

    /***
     * Crea relación
     * @param start nodo de inicio
     * @param end nodo final
     * @param distance peso entre los nodos (distancia)
     */
    public Edge (int start, int end, int distance){
        startVertex = start;
        endVertex = end;
        weight = distance;
    }
}


public class Graph {

    private int[][] MATRIX;
    private ArrayList ciudades = new ArrayList();
    private int vertex;

    /***
     * Crea matriz de adyacencia
     * @param datos ArrayList con la información de la ciudad origen, ciudad destino, y la distancia
     * @return True si se crea de forma correcta la matriz, de lo contrario retorna false
     */
    public boolean createMatrix(ArrayList datos){


        ArrayList<Edge> listaEdge = new ArrayList<Edge>();

        //Limpiando lista
        ciudades.clear();
        int from = 0;
        int to = 0;
        int peso = 0;

        // Agregando edge
        for(int control=0; control < datos.size();control++){

            String[] info = datos.get(control).toString().split(" ");

            if(!ciudades.contains(info[0])){
                ciudades.add(info[0]);
                from = ciudades.indexOf(info[0]);
            }
            else{
                from = ciudades.indexOf(info[0]);
            }

            if(!ciudades.contains(info[1])){
                ciudades.add(info[1]);
                to = ciudades.indexOf(info[1]);
            }
            else{
                to = ciudades.indexOf(info[1]);
            }

            try{
                peso = Integer.parseInt(info[2]);
            }
            catch (Exception e){
                System.out.println("\n\n\t\t\tLa distancia no es númerica, cargue nuevamente el archivo");
                return false;
            }
            listaEdge.add(new Edge(from,to,peso));

        }

        // Creando matriz
        vertex = ciudades.size();
        MATRIX = new int[vertex + 1][vertex + 1];

        for(int control = 0; control<listaEdge.size(); control++){
            Edge actual = listaEdge.get(control);
            int startVertex = actual.startVertex;
            int endVertex = actual.endVertex;
            int weight = actual.weight;
            MATRIX[startVertex][endVertex] = weight;
        }

        return true;
    }

    /***
     * Impresión de Matriz de adyacencia
     */
    public void printMatrix(){

        for(int i = 0; i< vertex; i++){
            for(int j=0; j< vertex;j++){
                System.out.print(MATRIX[i][j] + " ");
            }
            System.out.println();
        }

    }

    /***
     * Metodo para aplicar el algoritmo de Floyd, donde imprime la recomendación de ruta
     */
    public void algFloydWarshall(){

        FloydWarshall.floydWarshall(MATRIX, vertex,ciudades);
    }
}
