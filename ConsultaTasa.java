import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaTasa {

    public Tasas obtenerTasa(String tasa) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/2eacb74a3d7704d63914279c/latest/" + tasa))
                .build();

        HttpResponse<String> response;
        {
            try {
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Gson gson = new Gson();
                return   gson.fromJson(response.body(), Tasas.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
