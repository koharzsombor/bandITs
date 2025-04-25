/**
 * Rovarokat példányosító objektum interfésze.
 */
public interface InsectFactory {
    /**
     * Létrehoz egy rovart a megadott paraméterek alapján.
     * @param name A rovar neve.
     * @return Az új rovar.
     */
    Insect create(String name, Tecton tecton);
}
