package controller;

import java.util.Scanner;

public class Serie {
    private Scanner sc = new Scanner(System.in);

    // S = (1/3)`1 + (3/9)'1 - (5/15)'2 + (7/21)'3 + (9/27)'5 - ....

    // Método para generar el i-ésimo número impar
    private int generar_impar(int numero) {
        int resultado = 1;
        if (numero > 1) {
            for (int i = 1; i < numero; i++) {
                resultado += 2;
            }
        }
        return resultado;
    }

    // Método para multiplicar el número por 3
    private int generar_numero(int numero) {
        return numero * 3;
    }

    // Método principal que calcula la serie
    public String ejecutar(int num_serie) {
        String serie = "S = ";
        if (num_serie > 0) {
            int signo = 2; // Controla si es "+" o "-"
            float suma = 0.0f;
            for (int i = 0; i < num_serie; i++) {
                int impar = generar_impar(i + 1);
                int mult3 = generar_numero(impar);
                if (signo == 3) {
                    serie += "(" + impar + "/" + mult3 + ") - ";
                    signo = 0; // Reiniciar el signo
                    suma -= ((float) impar / (float) mult3);
                } else {
                    serie += "(" + impar + "/" + mult3 + ") + ";
                    suma += ((float) impar / (float) mult3);
                }
                signo++;
            }
            serie = serie.substring(0, serie.length() - 3); // Eliminar el último "+/-"
            String resp = "La serie es:\n";
            resp += serie + "\n";
            resp += "El resultado es: " + suma;
            return resp;
        } else {
            return "Ingrese un número mayor a 0.";
        }
    }

    // Método para gestionar la interacción con el usuario
    public void ejecutar() {
        try {
            System.out.println("Ingrese el número de términos de la serie:");
            String numero = sc.nextLine(); // Leer la entrada como String
            int num_serie = Integer.parseInt(numero); // Convertir el String a un entero
            System.out.println(ejecutar(num_serie)); // Llamar al método que calcula la serie
        } catch (NumberFormatException e) {
            System.out.println("Error: Por favor, ingrese un número válido.");
        }
    }

    // Método principal (main) para iniciar el programa
    public static void main(String[] args) {
        Serie serie = new Serie();
        serie.ejecutar(); // Llamar al método ejecutar
    }
}
