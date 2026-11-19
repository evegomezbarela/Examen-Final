import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<HabitacionHotel> habitaciones = new ArrayList<>();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println();
            System.out.println("===== GESTION HOTELERA =====");
            System.out.println("1. Crear objeto por defecto");
            System.out.println("2. Crear objeto con parametros");
            System.out.println("3. Modificar atributos del objeto");
            System.out.println("4. Visualizar objetos registrados");
            System.out.println("5. Ejecutar metodo de comportamiento 1");
            System.out.println("6. Ejecutar metodo de comportamiento 2");
            System.out.println("7. Salir");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    crearPorDefecto();
                    break;
                case 2:
                    crearConParametros();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    visualizar();
                    break;
                case 5:
                    reservar();
                    break;
                case 6:
                    calcularHospedaje();
                    break;
                case 7:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 7);
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    static boolean leerEstado(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim().toLowerCase();
            if (texto.equals("s")) {
                return true;
            }
            if (texto.equals("n")) {
                return false;
            }
            System.out.println("Responda con s o n.");
        }
    }

    static boolean numeroExiste(int numero) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    static HabitacionHotel pedirHabitacion() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
            return null;
        }
        visualizar();
        int posicion = leerEntero("Ingrese la posicion de la habitacion: ");
        if (posicion < 1 || posicion > habitaciones.size()) {
            System.out.println("La posicion " + posicion + " no existe.");
            return null;
        }
        return habitaciones.get(posicion - 1);
    }

    static void crearPorDefecto() {
        HabitacionHotel h = new HabitacionHotel();
        if (numeroExiste(h.getNumero())) {
            System.out.println("Ya existe una habitacion con el numero " + h.getNumero() + ".");
            return;
        }
        habitaciones.add(h);
        System.out.println("Habitacion por defecto creada.");
    }

    static void crearConParametros() {
        int numero = leerEntero("Numero de habitacion: ");
        if (numero <= 0) {
            System.out.println("El numero debe ser mayor que cero.");
            return;
        }
        if (numeroExiste(numero)) {
            System.out.println("Ya existe una habitacion con el numero " + numero + ".");
            return;
        }
        double precio = leerDecimal("Precio por noche: ");
        if (precio <= 0) {
            System.out.println("El precio debe ser mayor que cero.");
            return;
        }
        int capacidad = leerEntero("Capacidad de personas: ");
        if (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor que cero.");
            return;
        }
        boolean ocupada = leerEstado("Esta ocupada? (s/n): ");

        habitaciones.add(new HabitacionHotel(numero, precio, capacidad, ocupada));
        System.out.println("Habitacion creada correctamente.");
    }

    static void modificar() {
        HabitacionHotel h = pedirHabitacion();
        if (h == null) {
            return;
        }

        System.out.println("1. Numero");
        System.out.println("2. Precio por noche");
        System.out.println("3. Capacidad");
        System.out.println("4. Estado de ocupacion");
        int opcion = leerEntero("Que atributo desea modificar: ");

        switch (opcion) {
            case 1:
                int numero = leerEntero("Nuevo numero: ");
                if (numero <= 0) {
                    System.out.println("El numero debe ser mayor que cero.");
                } else if (numero != h.getNumero() && numeroExiste(numero)) {
                    System.out.println("Ya existe una habitacion con ese numero.");
                } else {
                    h.setNumero(numero);
                    System.out.println("Numero actualizado.");
                }
                break;
            case 2:
                double precio = leerDecimal("Nuevo precio por noche: ");
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor que cero.");
                } else {
                    h.setPrecioNoche(precio);
                    System.out.println("Precio actualizado.");
                }
                break;
            case 3:
                int capacidad = leerEntero("Nueva capacidad: ");
                if (capacidad <= 0) {
                    System.out.println("La capacidad debe ser mayor que cero.");
                } else {
                    h.setCapacidad(capacidad);
                    System.out.println("Capacidad actualizada.");
                }
                break;
            case 4:
                h.setOcupada(leerEstado("Esta ocupada? (s/n): "));
                System.out.println("Estado actualizado.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    static void visualizar() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        System.out.println("--- Habitaciones registradas ---");
        for (int i = 0; i < habitaciones.size(); i++) {
            System.out.println((i + 1) + ". " + habitaciones.get(i));
        }
    }

    static void reservar() {
        HabitacionHotel h = pedirHabitacion();
        if (h == null) {
            return;
        }
        int personas = leerEntero("Cantidad de personas: ");
        h.reservar(personas);
    }

    static void calcularHospedaje() {
        HabitacionHotel h = pedirHabitacion();
        if (h == null) {
            return;
        }
        int noches = leerEntero("Cantidad de noches: ");
        double total = h.calcularHospedaje(noches);
        if (total >= 0) {
            if (noches >= 5) {
                System.out.println("Se aplico un descuento del 10%.");
            }
            System.out.println("Costo total del hospedaje: $" + total);
        }
    }
}
