package controller;

import java.util.Scanner;

public class Llamadas {
    private Scanner sc = new Scanner(System.in);

    /**
     * Enum para representar las zonas con clave y precio por minuto.
     */
    public enum EnumZona {

        AMERICA_DEL_NORTE(12, 2.75f),
        AMERICA_CENTRAL(15, 1.89f),
        AMERICA_DEL_SUR(18, 1.60f),
        EUROPA(19, 3.50f),
        ASIA(23, 4.50f),
        AFRICA(25, 3.10f),
        OCEANIA(29, 3.00f),
        RESTO_DEL_MUNDO(31, 6.00f);

        private  int clave;
        private  float pMinuto;

        // Constructor del enum
        EnumZona(int clave, float pMinuto) {
            this.clave = clave;
            this.pMinuto = pMinuto;
        }

        // Métodos para obtener clave y precio
        public int getClave() {
            return clave;
        }

        public float getPrecioMinuto() {
            return pMinuto;
        }
    }

    /**
     * Método principal para calcular el costo de la llamada.
     */
    public void ejecutar() {
        // Mostrar las zonas disponibles
        System.out.println("Zonas disponibles para llamadas Internacionales:");
        for (EnumZona EnumZona : EnumZona.values()) {
            System.out.println("   Zona: " + EnumZona.name() + "----- Clave: " + EnumZona.getClave() + "----- Precio por minuto: $" + EnumZona.getPrecioMinuto());
        }

        // Solicitar la clave de la zona
        System.out.println("Ingrese la clave de la zona, a la cual desea realizar la llamada:");
        int clave = sc.nextInt();

        // Declarar la zona seleccionada
        EnumZona zonaSelec = null;

        // Usar switch para determinar la zona
        switch (clave) {
            case 12:
                zonaSelec = EnumZona.AMERICA_DEL_NORTE;
                break;
            case 15:
                zonaSelec = EnumZona.AMERICA_CENTRAL;
                break;
            case 18:
                zonaSelec = EnumZona.AMERICA_DEL_SUR;   
                break;
            case 19:
                zonaSelec = EnumZona.EUROPA;
                break;
            case 23:
                zonaSelec = EnumZona.ASIA;
                break;
            case 25:
                zonaSelec = EnumZona.AFRICA;
                break;
            case 29:
                zonaSelec = EnumZona.OCEANIA;
                break;
            case 31:
                zonaSelec = EnumZona.RESTO_DEL_MUNDO;
                break;
            default:
                System.out.println("La clave ingresada no es correcta, vuelva a intentarlo");
                return;
        }

        // Solicitar la cantidad de minutos hablados
        System.out.println("Cuantos minutos ocupo:");
        int minutos = sc.nextInt();

        // Calcular el costo total
        float costoTotal = minutos * zonaSelec.getPrecioMinuto();

        // Mostrar el costo total
        System.out.println("El costo de llamada en " + zonaSelec.name() + " es: $" + formatearNumero(costoTotal));
    }

    /**
     * Método para formatear números con dos decimales.
     */
    private String formatearNumero(float numero) {
        return String.format("%,.2f", numero);
    }

    /**
     * Método principal para iniciar el programa.
     */
    public static void main(String[] args) {
        Llamadas llamadas = new Llamadas();
        llamadas.ejecutar();
    }
}
