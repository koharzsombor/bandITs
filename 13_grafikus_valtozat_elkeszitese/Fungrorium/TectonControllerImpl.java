/**
 * A tekton irányító osztály implementációja.
 */
public class TectonControllerImpl implements TectonController {
    /**
     * A spórákat példányosító objektum.
     */
    private SporeFactory sporeFactory;

    /**
     * A függőségeit saját maga példányosítsa.
     */
    public TectonControllerImpl() {
        sporeFactory = new SporeFactoryImpl();
    }

    /**
     * Eltöri a megadott tektont.
     *
     * @param tecton A tekton amit el kell törni.
     */
    @Override
    public void breakTecton(Tecton tecton) {
        throw new UnsupportedOperationException("ooof.");
    }

    /**
     * A tekton törési visszaszámlálóját beállítja.
     *
     * @param tecton A tekton, aminek a számlálóját beállítjuk.
     * @param time   A törés visszaszámláló új értéke.
     */
    @Override
    public void setBreakTimer(Tecton tecton, int time) {
        tecton.setBreakTimer(time);
    }

    /**
     * A megadott két tektont szomszédossá teszi.
     *
     * @param tecton1 Az egyik tekton.
     * @param tecton2 A másik tekton.
     */
    @Override
    public void addNeighbour(Tecton tecton1, Tecton tecton2) {
        if (!tecton1.getNeighbours().contains(tecton2)) {
            tecton1.addNeighbour(tecton2);
            tecton2.addNeighbour(tecton1);
        }
    }

    /**
     * Hozzáad a tektonhoz egy gombafonalat.
     *
     * @param mycelium A gombafonál.
     * @param tecton   A tekton amihez hozzáadjuk a gombafonalat.
     */
    @Override
    public void addMycelium(Mycelium mycelium, Tecton tecton) {
        tecton.addMycelium(mycelium);
        mycelium.setLocation(tecton);
    }

    /**
     * Készít egy spórát, majd azt rárakja a tektonra.
     *
     * @param sporeType A spóra típusa.
     * @param sporeName A spóra neve.
     * @param target    A tekton amire a spórát rakjuk.
     */
    @Override
    public void putSpore(String sporeType, String sporeName, Tecton target) {
        Spore spore = sporeFactory.create(sporeName, sporeType);
        target.addSpore(spore);
    }
}
