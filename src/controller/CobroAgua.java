package controller;

import java.util.Scanner;

public class CobroAgua {
    private Scanner sc = new Scanner(System.in);

    public enum RangoConsumo {
        RANGO1(0, 15, 0.00f, 3.00f), 
        RANGO2(15, 25, 0.10f, 3.00f), 
        RANGO3(25, 40, 0.20f, 3.00f + 10 * 0.10f), 
        RANGO4(40, 60, 0.30f, 3.00f + 10 * 0.10f + 15 * 0.20f), 
        RANGO5(60, Integer.MAX_VALUE, 0.35f, 3.00f + 10 * 0.10f + 15 * 0.20f + 20 * 0.30f); 

        private final int min;           
        private final int max;          
        private final float adicional;   
        private final float costoInicial; 

        RangoConsumo(int min, int max, float adicional, float costoInicial) {
            this.min = min;
            this.max = max;
            this.adicional = adicional;
            this.costoInicial = costoInicial;
        }

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

    public void ejecutar() {
       
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

        if (terceraEdad && consumo <= 15) {
            costoAgua *= 0.5; 
        } else if (terceraEdad) {
            costoAgua -= 3.00 * 0.3; 
        } else if (discapacidad) {
            costoAgua -= 3.00 * porcentajeDiscapacidad;
        }

      
        float alcantarillado = costoAgua * 0.35f; 
        float basura = 0.75f; 
        float procesamiento = 0.50f; 
        
        float total = costoAgua + alcantarillado + basura + procesamiento;

      
        System.out.println("  Detalles del valor a cobrar  ");
        System.out.println("- Costo del servisio de agua potable: $" + formatearNumero(costoAgua));
        System.out.println("- Impuesto de alcantarillado (35%): $" + formatearNumero(alcantarillado));
        System.out.println("- Tasa por la recolección de basura: $" + formatearNumero(basura));
        System.out.println("- Tasa por procesamiento de datos: $" + formatearNumero(procesamiento));
        System.out.println("- VALOR TOTAL A COBRAR: $" + formatearNumero(total));
    }

   
    private RangoConsumo determinarRango(float consumo) {
        for (RangoConsumo rango : RangoConsumo.values()) {
            if (consumo > rango.getMin() && consumo <= rango.getMax()) {
                return rango;
            }
        }
        return RangoConsumo.RANGO5; 
    }

   
    private String formatearNumero(float numero) {
        return String.format("%,.2f", numero);
    }

    
    public static void main(String[] args) {
        CobroAgua cobroAgua = new CobroAgua();
        cobroAgua.ejecutar();
    }
}
