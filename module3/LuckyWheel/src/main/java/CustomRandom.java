import java.util.Random;

public class CustomRandom {
    private Random random;

    public CustomRandom() {
        random = new Random();
    }

    public int chooseRandomNumberStartingFrom1(int maxNumber) {
        return random.nextInt(maxNumber) + 1;
    }
}
