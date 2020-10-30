
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AppEstadoEnlace {
    // Fnunción para hallar el vértice con la mínima distancia 

    int minDistance(int dist[], Boolean procesado[], int b) {
        // Inicializar el valor principal
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < b; v++) {
            if (procesado[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    //Imprimir las distancias de solución
    void printSolution(int dist[], int b) {
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("Distancias mínimas desde origen");
        System.out.println("--------------------------------");
        System.out.println("Vértice \t\t Distancia desde el origen");
        for (int i = 0; i < b; i++) {
            System.out.println((i + 1) + " \t\t\t " + dist[i]);
        }

    }

    // Función para ejecutar dijkstra en base a la matriz de adyacencia de un grafo
    void dijkstra(int grafo[][], int origen) {
        int b = grafo[0].length;
        int dist[] = new int[b];
        /* El arreglo dist[] es el que almacena las menores distancias del
        origen a cada vértice ( i ) procesado, entonces le asignará el valor true
        y eso lo incluirá en el arreglo de solución*/
        Boolean procesado[] = new Boolean[b];
        // Inicializar todas las ditancias como infinito y procesado como falso
        for (int i = 0; i < b; i++) {
            dist[i] = Integer.MAX_VALUE;
            procesado[i] = false;
        }
        // La distancia del vértice de origen hacia sí mismo es siempre 0
        dist[origen] = 0;

        // Find shortest path for all vertices 
        for (int count = 0; count < b; count++) {
            // Elegir la distancia menor de los vértices no procesados
            // u siempre es el origen en la primera iteración

            int u = minDistance(dist, procesado, b);

            // Marcar el vértice como procesado
            procesado[u] = true;

            // Actualizar la distancia de los vértices adyacentes al seleciconado 
            for (int v = 0; v < b; v++) /*Actualizar dist[v] solo si no está procesado, hay un espacio entre u y v y
            el peso total desde el origen a v es menor que el valor actual de dist[v]*/ {
                if (!procesado[v] && grafo[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                }
            }
        }

        // Imprimir el arreglo de solución
        printSolution(dist, b);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //Ya tenemos el "lector" -> in

        System.out.println("Por favor cantidad de vértices");//Se pide los vertices

        String sVertices = in.readLine();
        //Leer la cantidad de vertices del grafo con readLine(). Retorna un String con el dato

        int cVertices = Integer.parseInt(sVertices);

        int grafo[][] = new int[cVertices][cVertices];

        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                int peso;
                if (i == j) {
                    peso = 0;
                } else {
                    System.out.println("Ingrese el peso de la conexión entre los nodos " + i + " y " + j);
                    peso = Integer.parseInt(in.readLine());
                }
                grafo[i][j] = peso;
            }
        }
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("Matriz de adyacencia");
        System.out.println("--------------------");

        //Imprimir la matriz de adyacencia
        for (int x = 0; x < grafo.length; x++) {
            for (int y = 0; y < grafo[x].length; y++) {
                System.out.print("|" + grafo[x][y] + "|");
            }
            System.out.println("");
        }
        //Ejecutar dijkstra para la matriz ingresada
        AppEstadoEnlace t = new AppEstadoEnlace();
        t.dijkstra(grafo, 0);
    }
}
