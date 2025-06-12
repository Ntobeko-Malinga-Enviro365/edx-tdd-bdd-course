import java.util.List;

public class Wheel {
    private String nameThatShouldNotBeChosen;
    private List<String> names;
    private CustomRandom random;

    public Wheel(CustomRandom random, List<String> names) {
        this.random = random;
        this.names = names;
        this.nameThatShouldNotBeChosen = "Ntobeko";
    }

    private String getRandomNameFromListOfNames() {
        return names.get(random.chooseRandomNumberStartingFrom1(names.size())-1);
    }

    public String chooseLuckyVictim() {
        if (names.isEmpty() || (names.size() == 1 && names.getFirst().equalsIgnoreCase(nameThatShouldNotBeChosen))) {
            throw new IllegalArgumentException("Cannot choose from empty list of names");
        } else {
            String chosenVictim = getRandomNameFromListOfNames();

            while (chosenVictim.equalsIgnoreCase(nameThatShouldNotBeChosen)) {
                chosenVictim = getRandomNameFromListOfNames();
            }
            return chosenVictim;
        }
    }
}
