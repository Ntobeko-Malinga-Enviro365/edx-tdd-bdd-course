import org.json.JSONObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IMDbTest {
    private static Map<String, JSONObject> imdbData = new HashMap<>();
    HttpURLConnection mockConnection;
    ConnectionFactory mockConnectionFactory;
    IMDb imDb;

    @BeforeAll
    static void beforeAll() {
        imdbData = Map.of(
                "GOOD_SEARCH", new JSONObject(
                        """
                                {
                                    "searchType": "Title",
                                    "expression": "Bambi",
                                    "results": [
                                      {
                                        "id": "tt1375666",
                                        "resultType": "Movie",
                                        "image": null,
                                        "title": "Babmi",
                                        "description": "Disney movie about a baby dear"
                                      }
                                    ],
                                    "errorMessage": null
                                }
                                """)
        );
    }

    @BeforeEach
    void setUp() {
        mockConnection = mock(HttpURLConnection.class);
        mockConnectionFactory = mock(ConnectionFactory.class);
        imDb = new IMDb("k_12345678", mockConnectionFactory);
    }

    @Test
    void testSearchByTitle() throws Exception {
        when(mockConnection.getResponseCode()).thenReturn(200);
        when(mockConnection.getInputStream()).thenReturn(
                new ByteArrayInputStream(imdbData.get("GOOD_SEARCH").toString().getBytes())
        );

        when(mockConnectionFactory.create(any())).thenReturn(mockConnection);

        JSONObject results = imDb.searchTitles("Bambi");
        assertTrue(results.has("results"));
        assertEquals("tt1375666", results.getJSONArray("results").getJSONObject(0).getString("id"));
    }

    @Test
    void testSearchWithNoResults() throws Exception {
        when(mockConnection.getResponseCode()).thenReturn(404);
        when(mockConnectionFactory.create(any())).thenReturn(mockConnection);

        JSONObject results = imDb.searchTitles("Bambi");

        assertTrue(results.isEmpty());
    }
}