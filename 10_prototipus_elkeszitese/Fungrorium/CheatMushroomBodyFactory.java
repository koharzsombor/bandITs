/**
 * Gombatestet létrehozó objektum a növesztési szabályok betartása nélkül.
 */
public class CheatMushroomBodyFactory implements MushroomBodyFactory {
    /**
     * A megadott paraméterek alapján példányosít egy gombatestet.
     *
     * @param name     A gombatest neve.
     * @param location A gombatest helye.
     * @return A példányosított gombatest.
     */
    @Override
    public MushroomBody create(String name, Tecton location) {
        MushroomBody mushroomBody = new MushroomBodyImpl();
        location.setMushroomBody(mushroomBody);
        mushroomBody.setLocation(location);
        ObjectRegistry.registerObject(name, mushroomBody);
        return mushroomBody;
    }
}
