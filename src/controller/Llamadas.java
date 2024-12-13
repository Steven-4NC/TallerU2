package controller;

import java.util.Scanner;

public class Llamadas {
    private Scanner sc = new Scanner(System.in);

    /*Se crea un enum o un catalogo donde se va almacenar los datos de las zonas geograficas, 
    tanto la clave y el precio de minutos por llamada*/
    
    public enum EnumZona {

        AMERICA_DEL_NORTE(12, 2.75f),
        AMERICA_CENTRAL(15, 1.89f),
        AMERICA_DEL_SUR(18, 1.60f),
        EUROPA(19, 3.50f),
        ASIA(23, 4.50f),
        AFRICA(25, 3.10f),
        OCEANIA(29, 3.00f),
        RESTO_DEL_MUNDO(31, 6.00f);
        // variables que almacenan las clve de la zona y el precio de los minutos
        private  int clave;
        private  float pMinuto;

        // inicializa las variables de la clave y el precio por minutos
        EnumZona(int clave, float pMinuto) {
            this.clave = clave;
            this.pMinuto = pMinuto;
        }

        // metodo que devuelve la clave de la zona
        public int getClave() {
            return clave;
        }
        // devuelve el precio por minutos
        public float getPrecioMinuto() {
            return pMinuto;
        }
    }

    /*Ejecuta el programa donde se muestra al usuario las zonas con su clave y el precio por minutos de las zonas que estan definidas*/
    public void ejecutar() {
        System.out.println("Zonas disponibles para llamadas Internacionales:");
        for (EnumZona EnumZona : EnumZona.values()) {
            System.out.println("   Zona: " + EnumZona.name() + "----- Clave: " + EnumZona.getClave() + "----- Precio por minuto: $" + EnumZona.getPrecioMinuto());
        }

        // Se solicita al usuario que ingrese el numero de la zona seleccionada
        System.out.println("Ingrese la clave de la zona, a la cual desea realizar la llamada:");
        int clave = sc.nextInt();

        // Declara la zona seleccionada y se valida con el switch
        EnumZona zonaSelec = null;

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
            // Si el usuario ingresa un valor que no es valido se presentara el mensaje de que vuelva a ingresar una clave
            default:
                System.out.println("La clave ingresada no es correcta, vuelva a intentarlo");
                return;
        }

        // Solicitar la cantidad de minutos hablados
        System.out.println("Cuantos minutos ocupo:");
        int minutos = sc.nextInt();

        // Se calcula el costo total de la llamada 
        float costoTotal = minutos * zonaSelec.getPrecioMinuto();

        // Muestra el costo total de la llamada presentando un mensaje donde se presenta la zona que se selecciono y el precio de llamada
        System.out.println("El costo de llamada en " + zonaSelec.name() + " es: $" + formatearNumero(costoTotal));
    }

    // Metodo para convertir los numeros a dos decimales
    private String formatearNumero(float numero) {
        return String.format("%,.2f", numero);
    }

    //Metodo principal para ejecutar el programa donde llamadas llama al metodo ejecutar para llevar a cabo el programa
    public static void main(String[] args) {
        Llamadas llamadas = new Llamadas();
        llamadas.ejecutar();
    }
}
