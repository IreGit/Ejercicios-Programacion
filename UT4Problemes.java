
import java.util.Scanner;


import static java.util.Arrays.binarySearch;
import static java.util.Arrays.fill;


/**
 *
 * @author Irene Payá Hernández
 */
public class UT4Problemes {
private Scanner teclado = new Scanner(System.in);
   
    public static void main(String[] args) {
       UT4Problemes programa = new UT4Problemes();
       programa.inicio();
    }
    public void inicio(){
        String[] libros = new String[1000];
        fill(libros,"");
        String libro;
        int indice = 0, opcion;
        System.out.println("GESTIÓN DE TÍTULOS DE BIBLIOTECA");
        System.out.println("--------------------------------");
        System.out.println("1. Añadir un título");
        System.out.println("2. Eliminar un título");
        System.out.println("3. Buscar un título");
        System.out.println("4. Ver todos los títulos");
        System.out.println("5. Salir");
        while((opcion = menu5Opciones())!= 5){
            switch(opcion){
                case 1:
                    System.out.print("\nIntroduce el título del libro: ");
                    libro = teclado.nextLine();
                    indice = añadirString(libros,libro,indice);
                    break;
                case 2:
                    System.out.print("\nIntroduce el título del libro a eliminar: ");
                    libro = teclado.nextLine();
                    
            }
        }
        System.out.println("FIN DEL PROGRAMA");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int menu5Opciones(){
        int opcion;
        System.out.print("\nSelecciona una opción: ");
        while(!teclado.hasNextInt() || (opcion = teclado.nextInt()) > 5 || opcion<1){
            System.out.print("ERROR. Selecciona una opción válida: ");
            teclado.nextLine();
        }
        teclado.nextLine();
        return opcion;
    }
    public int añadirString(String[] array, String texto, int indice){
        if(binarySearch(array,texto) < 0){
            array[indice] = texto;
            indice++; 
            System.out.println("El libro ha sido añadido con éxito");
        }else{
            System.out.println("Este libro ya está en la colección");
        }
        return indice;
    }
    public void eliminarString(String[] array, String texto){
        int indice;
        if((indice = binarySearch(array,texto))>0){
            array[indice] = "";
            
        }
    }
    public void buscarString(String[] array, String texto){
        
    }
    public void leerArray(String[] array){
        
    }
    
}
