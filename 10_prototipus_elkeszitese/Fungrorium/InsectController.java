/**
 * A rovarokat irányító osztály.
 */
public interface InsectController extends CommandHandler {
    /**
     * A rovar elrágja a gombafonalat azon a tektonon ahol van.s
     * @param insect A gombafonalat elvágó rovarász.
     */
    void cut(Insect insect);

    /**
     * A rovar megesz egy spórát a rajta lévő tektonon.
     * @param insect A spórát megevő rovar.
     */
    void eat(Insect insect);

    /**
     * A rovar elmozdul az adott tektonra.
     * @param insect A rovar aki mozog.
     * @param destination A rovar mozgásának célja.
     */
    void move(Insect insect, Tecton destination);
}
