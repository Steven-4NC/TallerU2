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

        // Inicia a crear un empleado y inlcuimos un metodo que convierte el nombre a mayusculas
        public Empleado(String nombre) {
            this.nombre = nombre.toUpperCase();
        }

        // regista los datos de los autos vendidos 
        public void registrarAuto(float precioAuto) {
            // Sumar el precio del auto al total de ventas
            this.tVentas += precioAuto;

            /* Declaramos una condicion donde si el precio del auto es mayor a 10.000 y la utilidad es mayor o igual a 500 que es el 5%
            le asiganaremos una comision solo por ese auto, 
            esto quiere decir que la comision solo se asignara por el numero de autos que cumplan ambas condiciones*/
            if (precioAuto > 10000 && (precioAuto * utilidad) >= 500) {
                this.totalComision += comision;
            }
        }
        /* Almacena todos los autos vendidos por cada empleado, aqui si el numero de autos es mayor a 10 entonces se hace una pregunta 
         * al usuario si la cantidad ingresada es correcta esto para evitar errores como ingresar 1000 autos y sea una cantidad muy grande
         * y no sea la que el usurio desee
        */
        public void autosVendidosEmpleado(int numAutosVendidos) {
            this.numAutosVendidos = numAutosVendidos;
        
            // Condicion que verifica si es mayor 
            if (numAutosVendidos > 10) {
                sc.nextLine(); 
                System.out.println("¿Seguro que la cantidad de autos vendidos es mayor a 10?");
                String respuesta = sc.nextLine().toLowerCase(); 
                // Si la respuesta del usuario es no entonces se volvera a ejecutar este bloque caso contrario seguira normal
                if (respuesta.equals("no")) {
                    System.out.println("Ingrese la cantidad correcta:");
                    this.numAutosVendidos = sc.nextInt();
                    sc.nextLine(); 
                }
            }
        }
        
        
        //Recibe el total de ventas, se asigna el parametro de ventas a la variable   
        public void totalVentasEmpleado(float tVentas) {
            this.tVentas = tVentas;
        }

        // Calcular comisiones y utilidades y devuelve la suma de ambos 
        public float calcularComision() {
            return totalComision; 
        }
        // calcula el 5% de las ventas totales
        public float calcularUtilidad() {
            return tVentas * utilidad;
        }
        // Suma todo lo que se calculo para entregar el sueldo final al pagar al empleado
        public float calcularSueldoFinal() {
            return sueldo + calcularComision() + calcularUtilidad();
        }

        //Devuelve el nombre del empleado 
        public String getNombre() {
            return nombre;
        }
        // Devuelve la cantidad de autos vendidos por el empleado 
        public int getAutosVendidos() {
            return numAutosVendidos;
        }
        // El total de las ventas realizadas
        public float getTotalVentas() {
            return tVentas;
        }
    }

    /**
     * Metodo para gestionar cuantos empleados va a ingresar, por ejemplo si ingresa el usuario ingresa
     * 4 el bloque de codigo se ejecutara 4 veces.
     */
    public void ejecutar() {
        System.out.println("¿Cuantos empleados desea ingresar?");
        int cantidadEmpleados = sc.nextInt();
        // Se limpia el salto de linea 
        sc.nextLine(); 

        // En esta variable se almacena la ingformacion de los enpleados para presentar como un informe general
        String informeGeneral = ""; 

        // Se ejecuta por la cantidad de empleados que ingrese el usuario
        for (int i = 1; i <= cantidadEmpleados; i++) {
            System.out.println("Ingrese el nombre del empleado " + i + ":");
            String nombre = sc.nextLine();

            // Crea los empleados
            Empleado empleado = new Empleado(nombre);

            // Pide cuantos autos el mepleado vendio 
            System.out.println("Ingrese el numero de autos vendidos:");
            int autosVendidos = sc.nextInt();
            // llama al metodo de autos vendidos y guarda el numero de autos que se vendio y hace que se cumplan las condiciones del metodo
            empleado.autosVendidosEmpleado(autosVendidos);

            // Se pide ingresar el precio de cada uno de los autos vendidos, tabien llama al metodo y verifica si se cumplen las condiciones
            for (int j = 1; j <= empleado.getAutosVendidos(); j++) {
                System.out.println("Ingrese el precio del auto " + j + ":");
                float precioAuto = sc.nextFloat();
                empleado.registrarAuto(precioAuto); 
            }
            sc.nextLine(); 

            // Agrega los datos de cada empleado al informe general de ssalida
            informeGeneral += generarInformeEmpleado(empleado) + "\n";
        }

        // Se muestra el informe general
        System.out.println("INFORME GENERAL DE TODOS LOS EMPLEADOS");
        System.out.println(informeGeneral);
    }

    /**
     * Este metodo crea un informe individial de cada empleado para despues agregarlo al informe general
     */
    private String generarInformeEmpleado(Empleado empleado) {
        String informe = " - Empleado: " + empleado.getNombre() + "\n";
        informe += " - Autos vendidos: " + empleado.getAutosVendidos() + "\n";
        informe += " - Total ventas: $" + String.format("%,.2f", empleado.getTotalVentas()) + "\n";
        informe += " - Comision: $" + String.format("%,.2f", empleado.calcularComision()) + "\n";
        informe += " - Utilidad: $" + String.format("%,.2f", empleado.calcularUtilidad()) + "\n";
        informe += " - Sueldo total: $" + String.format("%,.2f", empleado.calcularSueldoFinal()) + "\n";
        return informe;
    }
    //Metodo principal para ejecutar el programa donde informe llama al metodo de ejecutar para ejecutar el programa
    public static void main(String[] args) {
        Informe informe = new Informe();
        informe.ejecutar();
    }
}
