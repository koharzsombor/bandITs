/**
 * Gombafonalat példányosító osztály a növesztés szabályait figyelembe véve.
 */
public class DefaultMushroomBodyFactory implements MushroomBodyFactory {
    /**
     * A megadott paraméterek alapján példányosít egy gombatestet.
     *
     * @param name     A gombatest neve.
     * @param location A gombatest helye.
     * @return A példányosított gombatest.
     */
    @Override
    public MushroomBody createMushroomBody(String name, Tecton location) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
