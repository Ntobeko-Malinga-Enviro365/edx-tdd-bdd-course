import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WheelTest {
    CustomRandom mockRandom;
    Wheel wheel;

    @BeforeEach
    void setUp() {
        mockRandom = mock(CustomRandom.class);
    }

    @Test
    void testCanReturnAName() {
        wheel = new Wheel(mockRandom, List.of("Ntobeko"));

        when(mockRandom.chooseRandomNumberStartingFrom1(1)).thenReturn(1);

        String luckyVictim = wheel.chooseLuckyVictim();

        assertNotNull(luckyVictim);

    }

    @Test
    void testCanChooseFromMultiplePeople() {
        wheel = new Wheel(mockRandom, List.of("User 1", "User 2"));

        when(mockRandom.chooseRandomNumberStartingFrom1(2)).thenReturn(2);

        String luckyVictim = wheel.chooseLuckyVictim();

        assertEquals("User 2", luckyVictim);
    }

    @Test
    void testAvoidingPickingNtobeko() {
        wheel = new Wheel(mockRandom, List.of("User 1", "Ntobeko", "User 3"));

        when(mockRandom.chooseRandomNumberStartingFrom1(3))
                .thenReturn(2)
                .thenReturn(1);

        String luckyVictim = wheel.chooseLuckyVictim();

        assertEquals("User 1", luckyVictim);
    }
}