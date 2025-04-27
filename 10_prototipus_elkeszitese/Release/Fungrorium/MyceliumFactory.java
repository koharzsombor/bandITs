/**
 * Gombafonalakat példányosító osztály.
 */
public interface MyceliumFactory {
    /**
     * Létrehoz egy gombafonalat a megadott paraméterek alapján.
     * @param type A gombafonál típusa.
     * @param name A gombafonál neve;
     * @param location A gombafonál helye.
     * @return Az új gombafonál.
     */
    Mycelium create(String type, String name, Tecton location);
}
