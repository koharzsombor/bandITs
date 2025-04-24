import java.util.List;
import java.util.Queue;

/**
 * A viewban használt tekton interfész.
 */
public interface TectonView {
    /**
     * Visszadja, hogy hoány kör múlva törik el a tekton.
     * @return A tekton törésig hátralévő körök száma.
     */
    int getBreakTimer();

    /**
     * A tekton szomszédjainak a listáját adja vissza.
     * @return A tekton szomszédjainak listája.
     */
    List<TectonView> getNeighboursViews();

    /**
     * Visszadja a gombafonál kapacítását.
     * @return A tekton gombafonál kapacítása.
     */
    int getMyceliaCapacity();

    /**
     * A tektonon lévő spórákat visszadja.
     * @return A tektonon lévő spórákat.
     */
    Queue<Spore> getSpores();

    /**
     * Visszaadja a tektonon lévő gombafonalat.
     * @return A tektonon lévő gombatest.
     */
    MushroomBody getMushroomBody();

    /**
     * A tektonon lévő fonalakat visszaadja.
     * @return A tektonon lévő fonalakat.
     */
    Queue<Mycelium> getMycelia();

    /**
     * True-t ad vissza ha van legalább 1 fonál (legyen az bármilyen típusu) a tektonon
     * False-ot ad vissza ha nincs egy fonál sem a tektonon
     * @return
     */
    boolean hasMycelium();

    List<Insect> getOccupants();
}
