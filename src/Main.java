import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConversorMoneda conversor = new ConversorMoneda();
        conversor.cargarTasasCambio("1210e7c9f7c98a183ef5bd59"); // Usar  API Key
        mostrarMensajeBienvenida(); // Mostrar mensaje de bienvenida
        mostrarMenu(conversor); // Mostrar el menú después del mensaje
    }

    // Método para mostrar el mensaje de bienvenida
    private static void mostrarMensajeBienvenida() {
        System.out.println("Bienvenido al Conversor de Moneda.");
        System.out.println("Puedes convertir monedas entre varias divisas disponibles.");
    }

    // Método para mostrar el menú de opciones
    private static void mostrarMenu(ConversorMoneda conversor) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Convertir Dólar a Peso Argentino (USD a ARS)");
            System.out.println("2. Convertir Peso Argentino a Dólar (ARS a USD)");
            System.out.println("3. Convertir Dólar a Real Brasileño (USD a BRL)");
            System.out.println("4. Convertir Real Brasileño a Dólar (BRL a USD)");
            System.out.println("5. Convertir Peso Colombiano a Dólar (COP a USD)");
            System.out.println("6. Convertir Dólar a Peso Colombiano (USD a COP)");
            System.out.println("7. Convertir Real Brasileño a Peso Colombiano (BRL a COP)");
            System.out.println("8. Convertir Peso Colombiano a Real Brasileño (COP a BRL)");
            System.out.println("9. Salir");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> ejecutarConversion(conversor, "USD", "ARS");
                case 2 -> ejecutarConversion(conversor, "ARS", "USD");
                case 3 -> ejecutarConversion(conversor, "USD", "BRL");
                case 4 -> ejecutarConversion(conversor, "BRL", "USD");
                case 5 -> ejecutarConversion(conversor, "COP", "USD");
                case 6 -> ejecutarConversion(conversor, "USD", "COP");
                case 7 -> ejecutarConversion(conversor, "BRL", "COP");
                case 8 -> ejecutarConversion(conversor, "COP", "BRL");
                case 9 -> continuar = false;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // Método auxiliar para ejecutar una conversión
    private static void ejecutarConversion(ConversorMoneda conversor, String monedaOrigen, String monedaDestino) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad en " + monedaOrigen + ": ");
        double cantidad = scanner.nextDouble();
        conversor.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
    }
}
