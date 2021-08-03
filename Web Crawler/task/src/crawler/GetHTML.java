package crawler;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GetHTML {
    public static String get(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .timeout(Duration.of(10, ChronoUnit.SECONDS))
                            .GET()
                            .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        findTitle(response.body());
        return response.body();
    }
    public static void findTitle(String res){
        int firstIdx = res.indexOf("<title>");
        int lastIdx = res.indexOf("</title>");
        String title = res.substring(firstIdx + 7, lastIdx);
        WebCrawler.getTitleLable().setText(title);
    }
}
