import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoneda {

    private JsonObject tasasCambio;

    // Método para cargar las tasas de cambio desde la API
    public void cargarTasasCambio(String apiKey) {
        String baseCurrency = "USD"; // Moneda base
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        // Enviar solicitud y manejar la respuesta
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    try {
                        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

                        if (jsonObject.has("conversion_rates")) {
                            tasasCambio = jsonObject.getAsJsonObject("conversion_rates");
                            // Eliminamos el mensaje que imprimía las tasas
                        } else {
                            System.out.println("Error: 'conversion_rates' no encontrado en la respuesta.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al procesar la respuesta: " + e.getMessage());
                    }
                })
                .join(); // Esperar a que la solicitud se complete
    }

    // Método para realizar la conversión de moneda
    public void convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        if (tasasCambio == null) {
            System.out.println("Error: Las tasas de cambio no se han cargado.");
            return;
        }

        try {
            if (!tasasCambio.has(monedaOrigen) || !tasasCambio.has(monedaDestino)) {
                System.out.println("Error: Tasa de cambio no disponible para " + monedaOrigen + " o " + monedaDestino);
                return;
            }

            double tasaOrigen = tasasCambio.get(monedaOrigen).getAsDouble();
            double tasaDestino = tasasCambio.get(monedaDestino).getAsDouble();

            // Realizar la conversión
            double resultado = cantidad * (tasaDestino / tasaOrigen);
            System.out.println("La cantidad convertida es: " + resultado + " " + monedaDestino);
        } catch (NullPointerException e) {
            System.out.println("Error: No se pudo encontrar la tasa de cambio para " + monedaOrigen + " o " + monedaDestino);
        }
    }
}
