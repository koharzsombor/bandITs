/**
 * A növekedés vezérlőnek implementációja.
 */
public class GrowthControllerImpl implements GrowthController {
    /**
     * A gombafonalakat példányosító objektum.
     */
    private MyceliumFactory myceliumFactory;

    /**
     * A gombatesteket példányosító objektum.
     */
    private MushroomBodyFactory mushroomBodyFactory;

    /**
     * A konstruktor a függőségeit saját maga példányosítsa.
     */
    public GrowthControllerImpl() {
        myceliumFactory = new DefaultMyceliumFactory();
        mushroomBodyFactory = new DefaultMushroomBodyFactory();
    }

    /**
     * A megadott paraméterek alapján növesz egy tektont.
     *
     * @param name     A gombafonál neve.
     * @param location A gombafonál helye.
     */
    @Override
    public void growMycelium(String type, String name, Tecton location, Mycologist mycologist) {
        String defaultType = "default";
        Mycelium mycelium = myceliumFactory.create(defaultType, name, location);
        mycologist.addMycelium(mycelium);
    }

    /**
     * A megadott paraméterek alapján növeszt egy gombatestet.
     *
     * @param name     A gombatest neve.
     * @param location A gombatest helye.
     */
    @Override
    public void growMushroomBody(String name, Tecton location, Mycologist mycologist) {
        MushroomBody mushroomBody = mushroomBodyFactory.create(name, location);
        mycologist.addMushroomBody(mushroomBody);
    }
}
