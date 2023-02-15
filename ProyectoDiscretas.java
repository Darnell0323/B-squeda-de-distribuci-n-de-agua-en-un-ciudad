/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectodiscretas;

/**
 *
 * @author exito
 */
import java.text.DecimalFormat;
import java.util.*;





public class ProyectoDiscretas {
     public int vertices;
    public double principal[][];

    public ProyectoDiscretas(int vertices, double principal[][]) {
        this.vertices = vertices;
        this.principal = principal;
    }
    public class camino{
        int a,b;
        public camino(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
    public void prim(int pivote) {
        boolean[] visitadas = new boolean[vertices];// vector que lleva cuenta por que barrios ha pasado ya la tuberia
        List<Double> pesos = new LinkedList<>();//lista de pesos que almecena los pesos que va llevando de ciudad a ciudad
        List<Integer> orden = new LinkedList<>();//lista que contiene el camino que lleva
        List<camino> camino = new LinkedList<>();//lista que contiene el camino que lleva
        camino cam;
        boolean escape = true; //para que el do while itere
        do {
            double menor = Double.MAX_VALUE;
                    
            int indice = -1;//menor va almacenar el peso menor, y indice va almacenar el barrio a donde va con ese menor peso

            if (!orden.contains(pivote)) {
                orden.add(pivote);//va almacenar el camino
            }
            
            for (int i = 0; i < vertices; i++) {
                if (visitadas[i] != true && principal[pivote][i] != 0 && principal[pivote][i] < menor ) {/*si el barrio no esta visitado  
                   , el peso es mayor a 0 y ese peso debe que ser menor a "menor"(esta buscando la arista de menor peso)
                    */
                    
                    menor = principal[pivote][i];//va guardar el peso de la menor arista desde el pivote
                    indice = i;//indice va ser el barrio donde va esa arista
                   
                  
                }
                
            }
            
            if (indice != -1) {
                
                visitadas[pivote] = true;//va marcar el barrio como visitado
                pesos.add(menor);//va agregar el menor a peso a la lista   
                cam = new camino(pivote,indice);
                camino.add(cam);
                pivote = indice;//pivote va ser igual a al barrio donde conecta la menor arista
                

            }
            if (indice == -1) {
                boolean aux = true;
                visitadas[pivote] = true;//ya visito el barrio
                if (orden.indexOf(pivote) > 0) {
                    pivote = orden.get(orden.indexOf(pivote) - 1);/*para cuando se genera un ciclo lo que hace 
                    es mirar que barrio conecta con el barrio anterior que no haya visitado y tenga el menor peso
                    */ 
                }


                for (int i = 0; i < vertices && aux == true; i++) {
                    aux = visitadas[i];//es for sirve para que se compruebe de que todos los barrios han sido visitados
                }
                if (aux) {
                    escape = false;//cuando ya recorre todos los barrios se sale del do while
                }
            }
        } while (escape);
        System.out.println("Camino");
       for (int i = 0; i < camino.size(); i++) {
            System.out.print("("+camino.get(i).a + " - " +camino.get(i).b+")  " );//imprime los barrios que recorrio
       }
       System.out.println("");
        System.out.println("Pesos de barrio a barrio");
       double suma=0;
       for (int i = 0; i < pesos.size(); i++) {
           System.out.print(pesos.get(i) + " - ");//imprime los pesos que gasto en el recorrido
           suma +=pesos.get(i);
           
       }
        System.out.println("");
       DecimalFormat df = new DecimalFormat("#.###");//objeto de la clase decimalformat(redondea el n umero de decimales a un maximo de decimales
    String shortenedDecimal = df.format(suma);//coloca en un string el redonde de s
       
        System.out.println("");
        System.out.println("peso total:" +shortenedDecimal);
    }

    public static void main(String[] args) {
        double matrix[][] = {{0,1.6,0,2.5,0,0,0,0,0,0,0,0,0,0,5.8,0,0},
                            {1.6,0,3.9,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,3.9,0,0,0,0,0,0,0,0,0,0,2.3,4.1,2.5,0,0},
    {2.5,0,0,0,3.1,0,0,0,0,0,0,0,0,0,2.6,0,0},
    {0,0,0,3.1,0,0,0,0,0,0,0,0,0,0,0,0,3.8},
    {0,0,0,0,0,0,0,2.7,0,0,0,3.1,3.2,0,3.1,0,0},
    {0,0,0,0,0,0,0,3.1,0,0,0,0,0,0,0,0,1.7},
    {0,0,0,0,0,2.7,3.1,0,0,4.9,6.4,0,0,0,0,0,2.5},
    {0,0,0,0,0,0,0,0,0,3.1,0,2,0,0,0,1.9,0},
    {0,0,0,0,0,0,0,4.9,3.1,0,3.6,5,0,0,0,0,0},
    {0,0,0,0,0,0,0,6.4,0,3.6,0,0,0,0,0,0,0},
    {0,0,0,0,0,3.1,0,0,2,5,0,0,4,3.7,0,4.1,0},
    {0,0,2.3,0,0,3.2,0,0,0,0,0,4,0,0,0,0,0},
    {0,0,4.1,0,0,0,0,0,0,0,0,3.7,0,0,0,0,0},
    {5.8,0,2.5,2.6,0,3.1,0,0,0,0,0,0,0,0,0,0,4},
    {0,0,0,0,0,0,0,0,1.9,0,0,4.1,0,0,0,0,0},
    {0,0,0,0,3.8,0,1.7,2.5,0,0,0,0,0,0,4,0,0}};
       ProyectoDiscretas grafoP = new ProyectoDiscretas(17, matrix);// crea un objeto tipo ProyectoDiscretas  para poder usar en el main los metodos 
       Scanner leer = new Scanner(System.in);
          System.out.println("digite el barrio de donde quiere empezar");
        grafoP.prim(leer.nextInt());//llama el metodo prim pasandole el barrio donde quiere empezar
       
       // Print the solution
       
   }
}