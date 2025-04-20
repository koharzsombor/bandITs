/**
 * Rovarászt ábrázoló osztály interfésze.
 */
public interface Entomologist {
    /**
     * Hozzáad egy rovart a rovarász saját rovaraihoz.
     * @param insect A rovarász új rovarja.
     */
    void addInsect(Insect insect);

    /**
     * Elvesz egy rovart a rovarásztól.
     * @param insect Az elvett rovart.
     */
    void removeInsect(Insect insect);

    /**
     * Elmondja, hogy a rovarászé-e a rovar.
     * @param insect A rovar ami kérdés tárgya.
     * @return A rovar a rovarászé-e.
     */
    boolean ownsInsect(Insect insect);
}
