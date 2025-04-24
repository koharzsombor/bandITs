/**
 * Gombatesteket példányosító osztály.
 */
public interface MushroomBodyFactory {
    /**
     * A megadott paraméterek alapján példányosít egy gombatestet.
     * @param name A gombatest neve.
     * @param location A gombatest helye.
     * @return A példányosított gombatest.
     */
    MushroomBody create(String name, Tecton location);
}
