import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImdbInfo {
    public String imdbInfo(String title) throws Exception {
        System.out.println("Searching IMDB for " + title);
        String url = "https://imdb-api.com/API/SearchTitle/" + title;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return content.toString();
    }

    public static void main(String[] args) throws Exception {
        // Without mocking
        ImdbInfo client = new ImdbInfo();
        System.out.println(client.imdbInfo("Bambi"));
    }
}
