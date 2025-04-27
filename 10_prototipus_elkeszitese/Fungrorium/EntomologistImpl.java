import java.util.ArrayList;
import java.util.List;

/**
 * A rovarász implementációja.
 */
public class EntomologistImpl extends PlayerImpl implements Entomologist {

    /**
     * A rovarász rovarjainak a listája.
     */
    private final List<Insect> insects = new ArrayList<>();

    /**
     * A játékos létrehozásához szükséges megadni a nevét.
     *
     * @param name A játékos neve.
     */
    public EntomologistImpl(String name) {
        super(name);
    }

    /**
     * Hozzáad egy rovart a rovarász saját rovaraihoz.
     *
     * @param insect A rovarász új rovarja.
     */
    @Override
    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    /**
     * Elvesz egy rovart a rovarásztól.
     *
     * @param insect Az elvett rovart.
     */
    @Override
    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    /**
     * Elmondja, hogy a rovarászé-e a rovar.
     *
     * @param insect A rovar ami kérdés tárgya.
     * @return A rovar a rovarászé-e.
     */
    @Override
    public boolean ownsInsect(Insect insect) {
        return insects.contains(insect);
    }

    /**
     * Kiszámolja a játékos pontszámát.
     *
     * @return A játékos pontszáma.
     */
    @Override
    public int calculateScore() {
        return insects.stream().mapToInt(Insect::getSporesEaten).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Entomologist: \n" +
                "Name: " +
                name + "\n" +
                "Score: " +
                calculateScore() + "\n" +
                "Insects: {\n");

        for (Insect insect : insects) {
            sb.append(ObjectRegistry.lookupName(insect)).append("\n");
        }

        sb.append("}\n");

        return sb.toString();
    }
}
