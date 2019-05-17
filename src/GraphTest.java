import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GraphTest {

    @Test
    public void test() throws Exception{
        ArrayList datos = new ArrayList();

        datos.add("Guatemala Mixco 50");
        datos.add("Guatemala Palencia 60");
        datos.add("Mixco Palencia A");

        Graph grafo = new Graph();
        if(grafo.createMatrix(datos)){
            System.out.println("\nCreación correcta");
        }
        else{
            System.out.println("\nProblema al momento de crear matriz");
        }


    }
}