/**
 * A tektonokat irányító objektum.
 */
public interface TectonController extends CommandHandler {
    /**
     * Eltöri a megadott tektont.
     * @param tecton A tekton amit el kell törni.
     */
    void breakTecton(Tecton tecton);

    /**
     * A tekton törési visszaszámlálóját beállítja.
     * @param tecton A tekton, aminek a számlálóját beállítjuk.
     * @param time A törés visszaszámláló új értéke.
     */
    void setBreakTimer(Tecton tecton, int time);

    /**
     * A megadott két tektont szomszédossá teszi.
     * @param tecton1 Az egyik tekton.
     * @param tecton2 A másik tekton.
     */
    void addNeighbour(Tecton tecton1, Tecton tecton2);

    /**
     * Hozzáad a tektonhoz egy gombafonalat.
     * @param mycelium A gombafonál.
     * @param tecton A tekton amihez hozzáadjuk a gombafonalat.
     */
    void addMycelium(Mycelium mycelium, Tecton tecton);

    /**
     * Készít egy spórát, majd azt rárakja a tektonra.
     * @param sporeType A spóra típusa.
     * @param sporeName A spóra neve.
     * @param target A tekton amire a spórát rakjuk.
     */
    void putSpore(String sporeType, String sporeName, Tecton target);
}
