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

    public String chooseLuckyVictim() {
        String chosenVictim = names.get(random.chooseRandomNumberStartingFrom1(names.size())-1);

        while (chosenVictim.equalsIgnoreCase(nameThatShouldNotBeChosen)) {
            chosenVictim = 
        }
        return chosenVictim;
    }
}
