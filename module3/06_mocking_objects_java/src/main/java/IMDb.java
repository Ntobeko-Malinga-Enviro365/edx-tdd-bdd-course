import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class IMDb {
    private static final Logger logger = Logger.getLogger(IMDb.class.getName());
    private final String apiKey;
    private final ConnectionFactory connectionFactory;

    public IMDb(String apiKey, ConnectionFactory connectionFactory) {
        this.apiKey = apiKey;
        this.connectionFactory = connectionFactory;
    }

    public JSONObject searchTitles(String title) {
        logger.info("Searching IMDb for Title: " + title);
        String url = "https://imdb-api.com/API/SearchTitle/" + apiKey + "/" + title;
        return sendRequest(url);
    }

    public JSONObject movieReviews(String imdbId) {
        logger.info("Searching IMDb for Reviews: " + imdbId);
        String url = "https://imdb-api.com/API/Reviews/" + apiKey + "/" + imdbId;
        return sendRequest(url);
    }

    public JSONObject movieRatings(String imdbId) {
        logger.info("Searching IMDb for Ratings: " + imdbId);
        String url = "https://imdb-api.com/API/Ratings/" + apiKey + "/" + imdbId;
        return sendRequest(url);
    }

    private JSONObject sendRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = connectionFactory.create(url);
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                return new JSONObject(content.toString());
            } else {
                logger.warning("Received non-OK response code: " + status);
            }
        } catch (Exception e) {
            logger.severe("Request failed: " + e.getMessage());
        }
        return new JSONObject(); // Return empty JSON object on failure
    }
}
