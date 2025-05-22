/**
 * A növekedés vezérlőnek implementációja.
 */
public class GrowthControllerImpl implements GrowthController {
    public static GrowthController instance;

    /**
     * A gombafonalakat példányosító objektum.
     */
    private MyceliumFactory myceliumFactory;

    /**
     * A gombatesteket példányosító objektum.
     */
    private MushroomBodyFactory mushroomBodyFactory;

    private final TurnController turnController;

    /**
     * A konstruktor a függőségeit saját maga példányosítsa.
     */
    public GrowthControllerImpl(TurnController turnController) {
        this.turnController = turnController;
        myceliumFactory = new DefaultMyceliumFactory();
        mushroomBodyFactory = new DefaultMushroomBodyFactory();

        instance = this;
    }

    /**
     * A megadott paraméterek alapján növesz egy tektont.
     *
     * @param name     A gombafonál neve.
     * @param location A gombafonál helye.
     */
    @Override
    public void growMycelium(String type, String name, Tecton location, Mycologist mycologist) {
        //String defaultType = "default";
        if (mycologist == null) {
            if (turnController.getCurrentPlayer() instanceof Mycologist mycologist1) {
                mycologist = mycologist1;
            }
            else return;
        }

        if (mycologist.getRemainingGrows() <= 0)
            return;

        Mycelium mycelium = myceliumFactory.create(type, name, location);
        mycologist.addMycelium(mycelium);
        mycologist.subscribe(mycelium);
        mycologist.useGrow();
    }

    /**
     * A megadott paraméterek alapján növeszt egy gombatestet.
     *
     * @param name     A gombatest neve.
     * @param location A gombatest helye.
     */
    @Override
    public void growMushroomBody(String name, Tecton location, Mycologist mycologist) {
        boolean valid = false;

        if (mycologist == null) {
            if (turnController.getCurrentPlayer() instanceof Mycologist mycologist1) {
                mycologist = mycologist1;
            }
            else return;
        }

        for (Mycelium mycelium : location.getMycelia()) {
            if (turnController.getCurrentPlayer().controlsMycelium(mycelium)) {
                valid = true;
            }
        }

        if (!valid)
            return;

        MushroomBody mushroomBody = mushroomBodyFactory.create(name, location);
        mycologist.addMushroomBody(mushroomBody);
        mycologist.subscribe(mushroomBody);
    }
}
