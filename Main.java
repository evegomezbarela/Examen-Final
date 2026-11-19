import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HabitacionHotel[] habitaciones = new HabitacionHotel[10];
        int cantidad = 0;
        int opcion = 0;

        while (opcion != 7) {
            System.out.println();
            System.out.println("===== GESTION HOTELERA =====");
            System.out.println("1. Crear objeto por defecto");
            System.out.println("2. Crear objeto con parametros");
            System.out.println("3. Modificar atributos del objeto");
            System.out.println("4. Visualizar objetos registrados");
            System.out.println("5. Ejecutar metodo de comportamiento 1");
            System.out.println("6. Ejecutar metodo de comportamiento 2");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    if (cantidad < habitaciones.length) {
                        habitaciones[cantidad] = new HabitacionHotel();
                        cantidad++;
                        System.out.println("Habitacion por defecto creada.");
                    } else {
                        System.out.println("No hay espacio para mas habitaciones.");
                    }
                    break;
                }
                case 2: {
                    if (cantidad < habitaciones.length) {
                        System.out.print("Numero de habitacion: ");
                        int numero = sc.nextInt();
                        System.out.print("Precio por noche: ");
                        double precio = sc.nextDouble();
                        System.out.print("Capacidad de personas: ");
                        int capacidad = sc.nextInt();
                        System.out.print("Esta ocupada? (1 = si, 2 = no): ");
                        int estado = sc.nextInt();

                        if (numero <= 0 || precio <= 0 || capacidad <= 0) {
                            System.out.println("El numero, el precio y la capacidad deben ser mayores que cero.");
                        } else {
                            if (estado == 1) {
                                habitaciones[cantidad] = new HabitacionHotel(numero, precio, capacidad, true);
                                cantidad++;
                                System.out.println("Habitacion creada.");
                            } else {
                                if (estado == 2) {
                                    habitaciones[cantidad] = new HabitacionHotel(numero, precio, capacidad, false);
                                    cantidad++;
                                    System.out.println("Habitacion creada.");
                                } else {
                                    System.out.println("Estado no valido.");
                                }
                            }
                        }
                    } else {
                        System.out.println("No hay espacio para mas habitaciones.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Posicion de la habitacion: ");
                    int posicion = sc.nextInt();

                    if (posicion >= 1 && posicion <= cantidad) {
                        HabitacionHotel h = habitaciones[posicion - 1];
                        System.out.println("1. Numero");
                        System.out.println("2. Precio por noche");
                        System.out.println("3. Capacidad");
                        System.out.println("4. Ocupada");
                        System.out.print("Que desea modificar: ");
                        int atributo = sc.nextInt();

                        switch (atributo) {
                            case 1: {
                                System.out.print("Nuevo numero: ");
                                int numero = sc.nextInt();
                                if (numero > 0) {
                                    h.setNumero(numero);
                                    System.out.println("Numero actualizado.");
                                } else {
                                    System.out.println("El numero debe ser mayor que cero.");
                                }
                                break;
                            }
                            case 2: {
                                System.out.print("Nuevo precio por noche: ");
                                double precio = sc.nextDouble();
                                if (precio > 0) {
                                    h.setPrecioNoche(precio);
                                    System.out.println("Precio actualizado.");
                                } else {
                                    System.out.println("El precio debe ser mayor que cero.");
                                }
                                break;
                            }
                            case 3: {
                                System.out.print("Nueva capacidad: ");
                                int capacidad = sc.nextInt();
                                if (capacidad > 0) {
                                    h.setCapacidad(capacidad);
                                    System.out.println("Capacidad actualizada.");
                                } else {
                                    System.out.println("La capacidad debe ser mayor que cero.");
                                }
                                break;
                            }
                            case 4: {
                                System.out.print("Esta ocupada? (1 = si, 2 = no): ");
                                int estado = sc.nextInt();
                                if (estado == 1) {
                                    h.setOcupada(true);
                                    System.out.println("Estado actualizado.");
                                } else {
                                    if (estado == 2) {
                                        h.setOcupada(false);
                                        System.out.println("Estado actualizado.");
                                    } else {
                                        System.out.println("Estado no valido.");
                                    }
                                }
                                break;
                            }
                            default: {
                                System.out.println("Opcion no valida.");
                            }
                        }
                    } else {
                        System.out.println("La posicion no existe.");
                    }
                    break;
                }
                case 4: {
                    if (cantidad == 0) {
                        System.out.println("No hay habitaciones registradas.");
                    } else {
                        for (int i = 0; i < cantidad; i++) {
                            System.out.println((i + 1) + ". " + habitaciones[i]);
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.print("Posicion de la habitacion: ");
                    int posicion = sc.nextInt();

                    if (posicion >= 1 && posicion <= cantidad) {
                        System.out.print("Cantidad de personas: ");
                        int personas = sc.nextInt();
                        habitaciones[posicion - 1].reservar(personas);
                    } else {
                        System.out.println("La posicion no existe.");
                    }
                    break;
                }
                case 6: {
                    System.out.print("Posicion de la habitacion: ");
                    int posicion = sc.nextInt();

                    if (posicion >= 1 && posicion <= cantidad) {
                        System.out.print("Cantidad de noches: ");
                        int noches = sc.nextInt();
                        double total = habitaciones[posicion - 1].calcularHospedaje(noches);
                        if (total >= 0) {
                            System.out.println("Costo total del hospedaje: $" + total);
                        }
                    } else {
                        System.out.println("La posicion no existe.");
                    }
                    break;
                }
                case 7: {
                    System.out.println("Fin del programa.");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida.");
                }
            }
        }
    }
}
