package crawler;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GetHTML {
    static List<String> list = new ArrayList<>();
    public static void get(String url, int n) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .timeout(Duration.of(10, ChronoUnit.SECONDS))
                            .GET()
                            .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        List<String> contentTypeValues =
                response.headers().allValues("Content-Type");
        boolean bool = false;
        for(int i = 0; i < contentTypeValues.size(); i++){
            if(contentTypeValues.get(i).equals("text/html")) {
                bool = true;
                break;
            }
        }
        if(bool) {
            //System.out.println(response.body());
            String title = findTitle(response.body());
            if(n == 0) {
                list = new ArrayList<>();
                WebCrawler.getTitleLable().setText(title);
                findLinks(response.body(), url);
            }
            addRows(url, title);
            list.add(url);
        }
    }
    public static String findTitle(String res){
        int firstIdx = res.indexOf("<title>");
        int lastIdx = res.indexOf("</title>");
        return res.substring(firstIdx + 7, lastIdx);
    }
    public static void findLinks(String res, String url) throws URISyntaxException, IOException, InterruptedException {
        //find links
        //The href links must be matched with a corresponding title
        //(by visiting their page and checking for <title>...</title>
        //not title= in the original page text
        System.out.println(list);
        String[] lines = res.split("\n");
        for(String line: lines){
            if(line.contains("href=")){
                int hrefIdx = line.indexOf("href=");
                int hrefIdxEnd = line.indexOf("\"", hrefIdx + 6);
                String href = line.substring(hrefIdx + 6, hrefIdxEnd);
                href = parsehref(href, url);
                //System.out.println(href);
                if(checkhref(href)) {
                    get(href, 1);
                }
            }
        }
    }
    public static void addRows(String href, String title){
        DefaultTableModel dtm = (DefaultTableModel) WebCrawler.getTable().getModel();
        dtm.addRow(new Object[]{href,title});
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
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(href)){
                b = false;
                break;
            }
        }
        return b;
    }
}
