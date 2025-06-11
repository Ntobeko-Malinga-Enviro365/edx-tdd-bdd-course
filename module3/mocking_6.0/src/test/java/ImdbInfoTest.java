import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImdbInfoTest {

    @DisplayName("Testing mocking method that makes a call to an api")
    @Test
    void testImdbInfo() throws Exception {
        ImdbInfo imdbInfo = mock(ImdbInfo.class);

        when(imdbInfo.imdbInfo(any())).thenReturn("Mocked call to api");

        String mockedApiCallResults = imdbInfo.imdbInfo("Something");

        assertEquals("Mocked call to api", mockedApiCallResults);
    }
}