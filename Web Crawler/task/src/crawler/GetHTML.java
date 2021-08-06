package crawler;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetHTML {
    static Map<String, String> map = new HashMap<>();
    static List<String> list = new LinkedList<>();
    static List<String> subList = new LinkedList<>();
    public GetHTML() throws Exception {
        String url = WebCrawler.getTextField().getText();
        map = new HashMap<>();
        list.add(url);
        get(url, 0);
    }

    public static void get(String url, int n) throws Exception {
        HttpResponse<String> response = getResponse(url);

        if(checkIfHTML(response)) {
            String title = findTitle(response.body());
            map.put(url, title); //put to the Text Page.
            generateSubLinkList(response.body(), url);
            //process subLinkList iff reached the end of the loop TODO
            for(int i = 0; i < list.size(); i++){
                get(list.get(i), n);
            }
        }

        //stop here when max depth is reached
        addPageCount();
    }



    private static String parsehref(String href, String url){
        if(!href.contains("//")){
            int lastIdx = url.lastIndexOf("/");
            return url.substring(0,lastIdx) + "/" + href;
        }else if (href.contains("//") && !href.contains("https://")){
            return "https:" + href;
        }else{
            return href;
        }
    }

    private static boolean checkhref(String href){
        boolean b = true;
        for(Map.Entry<String ,String> entry : map.entrySet()){
            if(entry.getKey().equals(href)){
                b = false;
                break;
            }
        }
        return b;
    }

    public static Map<String, String> getMap() {
        return map;
    }

    private static boolean checkIfHTML(HttpResponse<String> response){
        List<String> contentTypeValues =
                response.headers().allValues("Content-Type");
        boolean bool = false;
        for(int i = 0; i < contentTypeValues.size(); i++){
            if(contentTypeValues.get(i).equals("text/html")) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    private static void addPageCount(){
        MainPanel.getPages().setText(
                Integer.toString(
                        Integer.parseInt(MainPanel.getPages().getText()) + 1
                ));
    }

    private static int getPageCount(){
        return Integer.parseInt(MainPanel.getPages().getText());
    }

    private static String findTitle(String res){
        int firstIdx = res.indexOf("<title>");
        int lastIdx = res.indexOf("</title>");
        return res.substring(firstIdx + 7, lastIdx);
    }

    private static void generateSubLinkList(String res, String url){
        list = new LinkedList<>();

        String[] lines = res.split("\n");

        for(String line: lines){
            if(line.contains("href=")){
                int hrefIdx = line.indexOf("href=");
                int hrefIdxEnd = line.indexOf("\"", hrefIdx + 6);
                String href = line.substring(hrefIdx + 6, hrefIdxEnd);
                href = parsehref(href, url);

                if(checkhref(href)) { // if href is alr repeated
                    list.add(href);
                }
            }
        }
    }

    private static HttpResponse<String> getResponse(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
