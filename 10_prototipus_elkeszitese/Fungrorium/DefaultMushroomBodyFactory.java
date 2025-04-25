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
    public MushroomBody create(String name, Tecton location) {
        MushroomBody mushroomBody;

        if (location instanceof SemiFertileTectonImpl semiFertileTecton) {
            mushroomBody = new MushroomBodyImpl(semiFertileTecton);
        }
        else if (location instanceof FertileTectonImpl fertileTecton) {
            mushroomBody = new MushroomBodyImpl(fertileTecton);
        }
        else {
            throw new IllegalArgumentException("NOOOOO ;(");
        }

        return mushroomBody;
    }
}
