/**
 * A gombafonalak növesztését vezérlő osztály.
 */
public interface GrowthController extends CommandHandler {
    /**
     * A megadott paraméterek alapján növesz egy tektont.
     * @param name A gombafonál neve.
     * @param location A gombafonál helye.
     */
    void growMycelium(String type, String name, Tecton location, Mycologist mycologist);

    /**
     * A megadott paraméterek alapján növeszt egy gombatestet.
     * @param name A gombatest neve.
     * @param location A gombatest helye.
     */
    void growMushroomBody(String name, Tecton location, Mycologist mycologist);
}
