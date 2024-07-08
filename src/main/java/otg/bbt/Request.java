package otg.bbt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import otg.bbt.models.ServerResponse;

import javax.xml.transform.Result;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Request {

    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()

                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .GET()
                .header("Accept", "application/json")
                .build();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<ServerResponse> result = objectMapper.readValue(response.body(), new TypeReference<List<ServerResponse>>() {});
                for (ServerResponse data: result) {
                    System.out.println(data.toString());
                }
            } else {
                System.out.println("Error " + response.statusCode());
            }
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}


