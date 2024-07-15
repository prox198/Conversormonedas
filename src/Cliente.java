import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Cliente {

    Conversion obtenerConversion (String valor1, String valor2, double cantidad ){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/5f14303141e2c0381e82125c/pair/"+valor1+valor2+cantidad);

        // cliente
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response= null;

            // recibe strings            recibe respuesta e intenta interpretar
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Conversion.class);
    }
}
