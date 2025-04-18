import java.util.ArrayList;
import java.util.List;

/**
 * A gombász implementációja.
 */
public class MycologistImpl implements Mycologist {
    /**
     * A gombafonalak listája.
     */
    private final List<Mycelium> mycelia = new ArrayList<>();

    /**
     * A gombatestek listája.
     */
    private final List<MushroomBody> mushroomBodies = new ArrayList<>();

    /**
     * Hozzáad egy gombafonalat a gombász saját gombafonalaihoz.
     *
     * @param mycelium A játékos új gombafonala.
     */
    @Override
    public void addMycelium(Mycelium mycelium) {
        mycelia.add(mycelium);
    }

    /**
     * Elvesz egy gombafonalat a gombásztól.
     *
     * @param mycelium A játékostól elvett gombafonál.
     */
    @Override
    public void removeMycelium(Mycelium mycelium) {
        mycelia.remove(mycelium);
    }

    /**
     * Megmondja, hogy a játékoshoz tartozik-e egy gombafonál.
     *
     * @param mycelium A gombafonál ami az eldöntés tárgya.
     * @return A játékosé-e a gombafonál.
     */
    @Override
    public boolean ownsMycelium(Mycelium mycelium) {
        return mycelia.contains(mycelium);
    }

    /**
     * Hozzáad egy gombatestet a gombász saját gombafonalaihoz.
     *
     * @param mushroomBody A játékos új gombateste.
     */
    @Override
    public void addMushroomBody(MushroomBody mushroomBody) {
        mushroomBodies.add(mushroomBody);
    }

    /**
     * Elvesz egy gombatestet a gombásztól.
     *
     * @param mushroomBody A játékostól elvett gombatest.
     */
    @Override
    public void removeMushroomBody(MushroomBody mushroomBody) {
        mushroomBodies.remove(mushroomBody);
    }

    /**
     * Megmondja, hogy a játékoshoz tartozik-e egy gombatest.
     *
     * @param mushroomBody A gombatest ami az eldöntés tárgya.
     * @return A játékosé-e a gombatest.
     */
    @Override
    public boolean ownsMushroomBody(MushroomBody mushroomBody) {
        return mushroomBodies.contains(mushroomBody);
    }
}
