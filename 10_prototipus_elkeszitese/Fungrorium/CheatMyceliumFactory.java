/**
 * Gombafonalat példámypsító osztály, a növeszetési feltételek betartása nélkül.
 */
public class CheatMyceliumFactory implements MyceliumFactory {
    /**
     * Létrehoz egy gombafonalat a megadott paraméterek alapján.
     *
     * @param type     A gombafonál típusa.
     * @param name     A gombafonál neve;
     * @param location A gombafonál helye.
     * @return Az új gombafonál.
     */
    @Override
    public Mycelium create(String type, String name, Tecton location) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
