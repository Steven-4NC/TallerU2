package views;

import java.util.Scanner;

import controller.CobroAgua;
import controller.Informe;
import controller.Llamadas;
import controller.Serie;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Taller Nrº2, Steven Narvaez");
        int salida = 0;
        do{
            System.out.println("Escoja una opcion");
            System.out.println("1 Informe de ventas");
            System.out.println("2 Costo de llamadas Internacionales");
            System.out.println("3 Calcular el consumo de agua");
            System.out.println("4 Generar serie");
            System.out.println("0 salir del programa");
            salida = sc.nextInt();
            switch (salida) {
                
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
