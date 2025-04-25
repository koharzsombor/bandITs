/**
 * A játéktér létrehozását irányító osztály implementációja.
 */
public class MapCreationControllerImpl implements MapCreationController {
    /**
     * A gombatesteket példányosító objektum.
     */
    private MushroomBodyFactory mushroomBodyFactory;

    /**
     * A gombafonalakat példányosító objektum.
     */
    private MyceliumFactory myceliumFactory;

    /**
     * A rovarokat példányosító objektum.
     */
    private InsectFactory insectFactory;

    /**
     * Tektonokat példányosító objektum.
     */
    private TectonFactory tectonFactory;

    /**
     * Éresíti a ráfeliratkozott objektumokat, hogyha a játékosok köre körbeért.
     */
    private RoundObserver roundObserver;

    /**
     * A konstruktor automatikusan létrehozza a szükséges objektumokat.
     */
    public MapCreationControllerImpl(RoundObserver roundObserver) {
        this.roundObserver = roundObserver;
        mushroomBodyFactory = new CheatMushroomBodyFactory();
        myceliumFactory = new CheatMyceliumFactory();
        insectFactory = new InsectFactoryImpl();
        tectonFactory = new TectonFactoryImpl();
    }

    /**
     * A megadott névvel, a megadott tektonra létrehoz egy gomafonalat.
     *
     * @param tecton A tekton ahova létrejön a gombafonál.
     * @param type A gombafonál típusa.
     * @param name A gombafonál neve.
     */
    @Override
    public void createMycelium(Tecton tecton, String type, String name, Mycologist owner) {
        Mycelium mycelium = myceliumFactory.create(type, name, tecton);
        if (owner != null) {
            owner.addMycelium(mycelium);
            owner.subscribe(mycelium);
        }
    }

    /**
     * A megadott névvel, a megadott tektonra létrehoz egy rovart.
     *
     * @param tecton A tekton amire létrejön a gombatest.
     * @param name A gombatest neve.
     */
    @Override
    public void createMushroomBody(Tecton tecton, String name, Mycologist owner) {
        MushroomBody mushroomBody = mushroomBodyFactory.create(name, tecton);
        if (owner != null) {
            owner.addMushroomBody(mushroomBody);
            owner.subscribe(mushroomBody);
        }
    }

    /**
     * A megadott névvel, a megadott tektonra létrehoz egy rovart.
     *
     * @param tecton A tekton ahova létrejön
     * @param name A rovar neve.
     */
    @Override
    public void createInsect(Tecton tecton, String name, Entomologist owner) {
        Insect newInsect = insectFactory.create(name);
        if (owner != null) {
            owner.addInsect(newInsect);
            owner.subscribe(newInsect);
        }
    }

    /**
     * A megadott névvel és típússal létrehoz egy tektont.
     *
     * @param type A tekton típusa.
     * @param name A tekton neve.
     */
    @Override
    public void createTecton(String type, String name) {
        Tecton tecton = tectonFactory.create(type, name);
        roundObserver.subscribe(tecton);
    }
}
