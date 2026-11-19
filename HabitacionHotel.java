public class HabitacionHotel {
    private int numero;
    private double precioNoche;
    private int capacidad;
    private boolean ocupada;

    public HabitacionHotel() {
        this.numero = 101;
        this.precioNoche = 120000;
        this.capacidad = 2;
        this.ocupada = false;
    }

    public HabitacionHotel(int numero, double precioNoche, int capacidad, boolean ocupada) {
        this.numero = numero;
        this.precioNoche = precioNoche;
        this.capacidad = capacidad;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean reservar(int personas) {
        if (ocupada) {
            System.out.println("La habitacion " + numero + " ya esta ocupada.");
            return false;
        }
        if (personas <= 0) {
            System.out.println("La cantidad de personas debe ser mayor que cero.");
            return false;
        }
        if (personas > capacidad) {
            System.out.println("La habitacion solo tiene capacidad para " + capacidad + " personas.");
            return false;
        }
        ocupada = true;
        System.out.println("Reserva realizada en la habitacion " + numero + " para " + personas + " personas.");
        return true;
    }

    public double calcularHospedaje(int noches) {
        if (noches <= 0) {
            System.out.println("La cantidad de noches debe ser mayor que cero.");
            return -1;
        }
        double total = precioNoche * noches;
        if (noches >= 5) {
            total = total - (total * 0.10);
        }
        return total;
    }

    public String toString() {
        String estado = ocupada ? "Ocupada" : "Disponible";
        return "Habitacion " + numero + " | Precio por noche: $" + precioNoche
                + " | Capacidad: " + capacidad + " | Estado: " + estado;
    }
}
