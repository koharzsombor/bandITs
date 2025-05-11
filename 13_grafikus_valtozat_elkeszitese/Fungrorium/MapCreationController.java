/**
 * A játéktér létrehozásást irányító objektum.
 */
public interface MapCreationController extends CommandHandler {
    /**
     * A megadott névvel, a megadott tektonra létrehoz egy gomafonalat.
     * @param name A gombafonál neve.
     * @param type A gombafonál típusa.
     * @param tecton A tekton, amire a gombafonál kerülni fog.
     */
    void createMycelium(Tecton tecton, String type, String name, Mycologist owner);

    /**
     * A megadott névvel, a megadott tektonra létrehoz egy rovart.
     * @param name A gombatest neve.
     * @param tecton A tekton, amire létrejön a gombatest.
     */
    void createMushroomBody(Tecton tecton, String name, Mycologist owner);

    /**
     * A megadott névvel, a megadott tektonra létrehoz egy rovart.
     * @param name A rovar neve.
     * @param tecton A tekton, amire létrejön a rovar.
     */
    void createInsect(Tecton tecton, String name, Entomologist owner);

    /**
     * A megadott névvel és típússal létrehoz egy tektont.
     * @param type A tekton típusa.
     * @param name A tekton neve.
     */
    void createTecton(String type, String name);

    /**
     * Procedurálisan létrehozza a térképet.
     */
    void generateMap();
}
