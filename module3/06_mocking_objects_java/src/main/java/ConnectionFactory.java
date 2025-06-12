import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionFactory {

    public HttpURLConnection create(URL url) throws Exception {
        return (HttpURLConnection) url.openConnection();
    }
}
