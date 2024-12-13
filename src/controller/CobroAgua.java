package controller;
import java.util.Scanner;
public class CobroAgua {
    private Scanner sc = new Scanner(System.in);
    /* Se crea un catalogo donde se define el rango de consumo de agua el minimo y maximo, 
    y el costo adicional dependiendo del cosumo de agua mas el costo adicional */

    public enum RangoConsumo {
        RANGO1(0, 15, 0.00f, 3.00f), 
        RANGO2(15, 25, 0.10f, 3.00f), 
        RANGO3(25, 40, 0.20f, 3.00f + 10 * 0.10f), 
        RANGO4(40, 60, 0.30f, 3.00f + 10 * 0.10f + 15 * 0.20f), 
        RANGO5(60, Integer.MAX_VALUE, 0.35f, 3.00f + 10 * 0.10f + 15 * 0.20f + 20 * 0.30f); 
        
        //declara las variables donde se define el minimo el maximo y el adiccional y costo inicial
        private final int min;           
        private final int max;          
        private final float adicional;   
        private final float costoInicial; 

        // aqui es donde se asiganan los valores al enum
        RangoConsumo(int min, int max, float adicional, float costoInicial) {
            this.min = min;
            this.max = max;
            this.adicional = adicional;
            this.costoInicial = costoInicial;
        }
        // devuelven los valores de los atributos de todas las variables del enum
        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public float getAdicional() {
            return adicional;
        }

        public float getCostoInicial() {
            return costoInicial;
        }
    }
    // Este es el metodo principal donde se ejecuta el programa
    public void ejecutar() {
        /* Se pide al usuario que ingrese los siguientes datos y 
        si cumple ciertas condiciones se aplicaran descuento*/
        System.out.println("Ingrese el valor del consumo de agua");
        float consumo = sc.nextFloat();

        System.out.println("¿Usted es de la tercera edad?");
        boolean terceraEdad = sc.next().equalsIgnoreCase("si");

        System.out.println("¿Cuenta con alguna discapacidad?");
        boolean discapacidad = sc.next().equalsIgnoreCase("si");
        float porcentajeDiscapacidad = 0;
        if (discapacidad) {
            System.out.println("Ingrese cual es el porcentaje de su discapacidad");
            porcentajeDiscapacidad = sc.nextFloat() / 100; 
        }

        // valida a que rango de consumo pertence y depende de eso se ejecutara el case 
        float costoAgua = 0.0f;
        switch (determinarRango(consumo)) {
            case RANGO1:
                costoAgua = RangoConsumo.RANGO1.getCostoInicial();
                break;
            case RANGO2:
                costoAgua = RangoConsumo.RANGO2.getCostoInicial() + (consumo - RangoConsumo.RANGO2.getMin()) * RangoConsumo.RANGO2.getAdicional();
                break;
            case RANGO3:
                costoAgua = RangoConsumo.RANGO3.getCostoInicial() + (consumo - RangoConsumo.RANGO3.getMin()) * RangoConsumo.RANGO3.getAdicional();
                break;
            case RANGO4:
                costoAgua = RangoConsumo.RANGO4.getCostoInicial() + (consumo - RangoConsumo.RANGO4.getMin()) * RangoConsumo.RANGO4.getAdicional();
                break;
            case RANGO5:
                costoAgua = RangoConsumo.RANGO5.getCostoInicial() + (consumo - RangoConsumo.RANGO5.getMin()) * RangoConsumo.RANGO5.getAdicional();
                break;
        }
        /* condicciones para aplicar desceuntos en las tarigas finales 
         *
        */
        if (terceraEdad && consumo <= 15) {
            costoAgua *= 0.5; 
        } else if (terceraEdad) {
            costoAgua -= 3.00 * 0.3; 
        } else if (discapacidad) {
            costoAgua -= 3.00 * porcentajeDiscapacidad;
        }

        //  se declara las siguiente variables para realizar los calculos
        float alcantarillado = costoAgua * 0.35f; 
        float basura = 0.75f; 
        float procesamiento = 0.50f; 
        // Se suma todos los calculos para lanzar el precio final 
        float total = costoAgua + alcantarillado + basura + procesamiento;

        // Se da un informe de del detalle que se va a tener que cobrar donde se especifican varios puntos 
        System.out.println("  Detalles del valor a cobrar  ");
        System.out.println("- Costo del servisio de a27gua potable: $" + formatearNumero(costoAgua));
        System.out.println("- Impuesto de alcantarillado (35%): $" + formatearNumero(alcantarillado));
        System.out.println("- Tasa por la recolección de basura: $" + formatearNumero(basura));
        System.out.println("- Tasa por procesamiento de datos: $" + formatearNumero(procesamiento));
        System.out.println("- VALOR TOTAL A COBRAR: $" + formatearNumero(total));
    }

   //Determina el rango de cosusmo de lo que ingresa el usuario 
    private RangoConsumo determinarRango(float consumo) {
        for (RangoConsumo rango : RangoConsumo.values()) {
            if (consumo > rango.getMin() && consumo <= rango.getMax()) {
                return rango;
            }
        }
        return RangoConsumo.RANGO5; 
    }

    // Metodo para convertir los numeros a dos decimales
    private String formatearNumero(float numero) {
        return String.format("%,.2f", numero);
    }

    //Metodo principal para ejecutar el programa donde cobroagua llama al metodo ejecutar para llevar a cabo el programa
    public static void main(String[] args) {
        CobroAgua cobroAgua = new CobroAgua();
        cobroAgua.ejecutar();
    }
}
