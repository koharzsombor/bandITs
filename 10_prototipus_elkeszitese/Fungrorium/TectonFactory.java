/**
 * Tektonokat példányosító objektum.
 */
public interface TectonFactory {
    /**
     * Példányosít egy tektont a megadott paraméterek alapján
     * @param type A tekton típusa.
     * @param name A tekton neve.
     * @return Az új tekton.
     */
    Tecton create(String type, String name);
}
