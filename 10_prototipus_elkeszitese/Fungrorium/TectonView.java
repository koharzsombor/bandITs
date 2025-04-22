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
    List<TectonView> getNeighbours();

    List<Mycelium> getMycelia();

    boolean hasMycelium();

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
}
