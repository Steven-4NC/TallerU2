package views;

import java.util.Scanner;

import controller.CobroAgua;
import controller.Informe;
import controller.Llamadas;
import controller.Serie;

public class Main {
    //Creamos una isntacia de scanner para leer lo que el usuario ingresa por el teclado
    private static Scanner sc = new Scanner(System.in);
    // Metodo principal el cual se encarga de la ejecucion de todo el programa
    public static void main(String[] args) {
        System.out.println("Taller Nrº2, Steven Narvaez");
        int salida = 0;
        /*Iniciamos un bucle que presenta las distintas opciones del programa 
        el cual se ejecuta hasta que el usuario ingrese el valor de 0 para salir del bucle*/ 
        do{
            System.out.println("Escoja una opcion");
            System.out.println("1 Informe de ventas");
            System.out.println("2 Costo de llamadas Internacionales");
            System.out.println("3 Calcular el consumo de agua");
            System.out.println("4 Generar serie");
            System.out.println("0 salir del programa");
            salida = sc.nextInt();
            switch (salida) {
                //Creamos los objetos y se llama a los metodos para ejecutarlos segun las eleccion del usuario
                case 1:
                    Informe informe = new Informe(); 
                    informe.ejecutar(); 
                    break;
                case 2:
                    Llamadas llamadas = new Llamadas();
                    llamadas.ejecutar();                   
                    break;
                case 3:
                    CobroAgua cobroAgua = new CobroAgua();
                    cobroAgua.ejecutar();
                    break;
                case 4:
                    Serie serie = new Serie();
                    serie.ejecutar(); 
                    break;
                // Si el usurio ingresa un numero que no esta dentro del rango de 0 a 5 entonces lazara un mensaje de opcion no valida
                // y si el usuario manda algun numero negativo igual se enviara un mensaje que dira que la opcion no es valida
                default:
                    if (salida > 5 || salida < 0) {
                        System.out.println("Opccion no valida");                        
                    }
                    break;
            }
        }while(salida != 0 );
        System.out.println("Salio del programa");
    }
}
