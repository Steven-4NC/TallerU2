package controller;

import java.util.Scanner;

public class Serie {
    private Scanner sc = new Scanner(System.in);

    // S = (1/3)`1 + (3/9)'1 - (5/15)'2 + (7/21)'3 + (9/27)'5 - ....
    // Se genera un numero impar
    private int generar_impar(int numero) {
        int resultado = 1;
        if (numero > 1) {
            for (int i = 1; i < numero; i++) {
                resultado += 2;
            }
        }
        return resultado;
    }

    // se multiploca el numero por 3
    private int generar_numero(int numero) {
        return numero * 3;
    }

    // Metodo principal para ejecutar la serie
    public String ejecutar(int num_serie) {
        String serie = "S = ";
        if (num_serie > 0) {
            // controla si el signo es positivo o negativo
            int signo = 2; 
            float suma = 0.0f;
            // bucle que funciona depende le numero que le agregue el usuario
            for (int i = 0; i < num_serie; i++) {
                int impar = generar_impar(i + 1);
                int mult3 = generar_numero(impar);
                if (signo == 3) {
                    serie += "(" + impar + "/" + mult3 + ") - ";
                    // el signo se reinicia
                    signo = 0; 
                    suma -= ((float) impar / (float) mult3);
                } else {
                    serie += "(" + impar + "/" + mult3 + ") + ";
                    suma += ((float) impar / (float) mult3);
                }
                signo++;
            }
            //Se elimina el signo del final y construye la respuesta final de la serie
            serie = serie.substring(0, serie.length() - 3); 
            String resp = "La serie es:\n";
            resp += serie + "\n";
            resp += "El resultado es: " + suma;
            return resp;
        } else {
            return "Ingrese un numero que sea mayor a 0";
        }
    }

    // Metodo donde se ejecuta el programa
    public void ejecutar() {
        try {
            System.out.println("Ingrese el numero de terminos de la serie:");
            String numero = sc.nextLine(); 
            int num_serie = Integer.parseInt(numero); 
            System.out.println(ejecutar(num_serie)); 
        } catch (NumberFormatException e) {
            System.out.println("El numero ingresado no es valido.");
        }
    }

    //Metodo principal para ejecutar el programa donde serie llama al metodo ejecutar para llevar a cabo el programa
    public static void main(String[] args) {
        Serie serie = new Serie();
        serie.ejecutar(); 
    }
}
