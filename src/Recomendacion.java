import java.util.ArrayList;

/***
 * Clase principal para el manejo de la aplicación
 * @author Pablo Sao
 * @version 15/05/2019
 */

public class Recomendacion {

    //Variable que contiene el menú inicial a mostrar
    final static String MENU_INICIO = "\n\t\t\t\tMenú" +
            "\n\t1.) Cargar Archivo." +
            "\n\t2.) Salir.";

    // Instrucciones para ingreso de datos
    final static String INSTRUCCIONES = "\nIngrese el número de opción del menú: ";

    public static void main(String[] args){
        // Inicializamos variable para control de menu
        int opcion = 0;
        // Realizamos ciclo para que se muestre las opciones hasta que el usuario desee salir del sistema
        while(true){

            //Mostramos el primer menu a mostrar
            System.out.println(MENU_INICIO);

            //Mostramos las instrucciones al usuario
            System.out.print(INSTRUCCIONES);

            //Leemos la opción deseada por el usuario
            opcion = Keyboard.readInt();

            switch (opcion){
                case 1:
                    System.out.print("\nIngrese el path del archivo: ");
                    String path = Keyboard.readString();

                    if(DataManager.getExists(path)){
                        //Llamado a la clase para realizar proceso de carga de información

                        String dato = DataManager.getDataFile(path,";");
                        try{
                            // PROCESO DE CARGA

                            ArrayList info = (ArrayList)DataManager.getStringTokens(";",dato);

                            if(!info.isEmpty()){
                                //Creando matriz adyacente
                                Graph grafo = new Graph();

                                if(grafo.createMatrix(info)){
                                    grafo.printMatrix();
                                    System.out.println("\nRecomendación de rutas:");
                                    grafo.algFloydWarshall();
                                }
                                else{
                                    System.out.println("\n\n\t\t\tExiste un problema con el archivo cargado.");
                                    break;
                                }
                            }
                            else{
                                System.out.println("\n\n\t\t\tEl archivo se encuentra vacio");
                            }

                        }
                        catch (Exception e){
                            System.out.println(e.toString());
                        }

                    }
                    else{
                        System.out.println("\n\t\tEl archivo no fue encontrado\n");
                    }
                    break;
                case 2:
                    System.exit(0);
            }
        }

    }
}
