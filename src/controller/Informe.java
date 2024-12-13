package controller;

import java.util.Scanner;

public class Informe {
    private Scanner sc = new Scanner(System.in); 
    // se crea la clase empleado donde vamos a definir los atributos que va a tener la clase empleado
    public class Empleado {
        /* Se declara las variables que van a almacenar el nombre de los empleados cuantos autos an vendido y los valores fijos como son el sueldo
        la utilidad si se calcula es 0.5 y la comision que es un valor fijo y el total de comision que es la suma de la comision obtenida 
        por x cantidad de autos que cumplan las condicionees*/
        private String nombre;         
        private int numAutosVendidos;  
        private float tVentas;         
        private float sueldo = 2500.0f;
        private float utilidad = 0.05f; 
        private float comision = 250.0f;
        private float totalComision = 0.0f;

        // Constructor
        public Empleado(String nombre) {
            this.nombre = nombre.toUpperCase();
        }

        // Registrar datos
        public void registrarAuto(float precioAuto) {
            // Sumar el precio del auto al total de ventas
            this.tVentas += precioAuto;

            // Verificar condiciones para la comisión
            if (precioAuto > 10000 && (precioAuto * utilidad) >= 500) {
                this.totalComision += comision; // Agregar comisión si cumple
            }
        }

        public void autosVendidosEmpleado(int numAutosVendidos) {
            this.numAutosVendidos = numAutosVendidos; // Asignar el valor inicialmente
        
            // Verificar si el número es mayor a 10
            if (numAutosVendidos > 10) {
                sc.nextLine(); // Limpiar el buffer antes de leer la respuesta
                System.out.println("¿Seguro que la cantidad de autos vendidos es mayor a 10?");
                String respuesta = sc.nextLine().toLowerCase(); // Leer respuesta en minúsculas
        
                if (respuesta.equals("no")) {
                    // Pedir un nuevo número si la respuesta es "no"
                    System.out.println("Ingrese la cantidad correcta:");
                    this.numAutosVendidos = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                }
            }
        }
        
        

        public void totalVentasEmpleado(float tVentas) {
            this.tVentas = tVentas;
        }

        // Calcular comisiones y utilidades
        public float calcularComision() {
            return totalComision; // Retorna la comisión acumulada
        }

        public float calcularUtilidad() {
            return tVentas * utilidad;
        }

        public float calcularSueldoFinal() {
            return sueldo + calcularComision() + calcularUtilidad();
        }

        // Obtener datos
        public String getNombre() {
            return nombre;
        }

        public int getAutosVendidos() {
            return numAutosVendidos;
        }

        public float getTotalVentas() {
            return tVentas;
        }
    }

    /**
     * Método principal para gestionar empleados.
     */
    public void ejecutar() {
        System.out.println("¿Cuantos empleados desea ingresar?");
        int cantidadEmpleados = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        String informeGeneral = ""; // Variable para acumular el informe de todos los empleados

        // Procesar empleados secuencialmente
        for (int i = 1; i <= cantidadEmpleados; i++) {
            System.out.println("Ingrese el nombre del empleado " + i + ":");
            String nombre = sc.nextLine();

            Empleado empleado = new Empleado(nombre); // Crear empleado

            System.out.println("Ingrese el numero de autos vendidos:");
            int autosVendidos = sc.nextInt();
            empleado.autosVendidosEmpleado(autosVendidos);

            // Solicitar precio de cada auto
            for (int j = 1; j <= empleado.getAutosVendidos(); j++) {
                System.out.println("Ingrese el precio del auto " + j + ":");
                float precioAuto = sc.nextFloat();
                empleado.registrarAuto(precioAuto); // Registrar el auto y validar condiciones
            }
            sc.nextLine(); // Limpiar el buffer

            // Agregar los datos de este empleado al informe general
            informeGeneral += generarInformeEmpleado(empleado) + "\n";
        }

        // Mostrar el informe general
        System.out.println("INFORME GENERAL DE TODOS LOS EMPLEADOS");
        System.out.println(informeGeneral);
    }

    /**
     * Método para generar el informe de un empleado.
     */
    private String generarInformeEmpleado(Empleado empleado) {
        String informe = " - Empleado: " + empleado.getNombre() + "\n";
        informe += " - Autos vendidos: " + empleado.getAutosVendidos() + "\n";
        informe += " - Total ventas: $" + formatearNumero(empleado.getTotalVentas()) + "\n";
        informe += " - Comision: $" + formatearNumero(empleado.calcularComision()) + "\n";
        informe += " - Utilidad: $" + formatearNumero(empleado.calcularUtilidad()) + "\n";
        informe += " - Sueldo total: $" + formatearNumero(empleado.calcularSueldoFinal()) + "\n";
        return informe;
    }

    /**
     * Método para formatear números en formato humano (co1n separadores de miles y dos decimales).
     */
    private String formatearNumero(float numero) {
        return String.format("%,.2f", numero); // Usar formato directo para separar miles y dos decimales
    }

    /**
     * Método principal para iniciar el programa.
     */
    public static void main(String[] args) {
        Informe informe = new Informe();
        informe.ejecutar();
    }
}
