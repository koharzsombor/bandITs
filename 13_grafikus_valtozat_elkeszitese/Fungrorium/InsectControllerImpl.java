/**
 * A rovar irányító osztály implementációja
 */
public class InsectControllerImpl implements InsectController {

    private final TurnController turnController;

    public InsectControllerImpl(TurnController turnController) {
        this.turnController = turnController;
    }

    /**
     * A rovar elrágja a gombafonalat azon a tektonon ahol van.s
     *
     * @param insect A gombafonalat elvágó rovarász.
     */
    @Override
    public void cut(Insect insect) {
        if (turnController.getCurrentPlayer().controlsInsect(insect))
            insect.cutMycelium();
    }

    /**
     * A rovar megesz egy spórát a rajta lévő tektonon.
     *
     * @param insect A spórát megevő rovar.
     */
    @Override
    public void eat(Insect insect) {
        if (turnController.getCurrentPlayer().controlsInsect(insect))
            insect.eatSpore();
    }

    /**
     * A rovar elmozdul az adott tektonra.
     *
     * @param insect      A rovar aki mozog.
     * @param destination A rovar mozgásának célja.
     */
    @Override
    public void move(Insect insect, Tecton destination) {
        if (turnController.getCurrentPlayer().controlsInsect(insect))
            insect.move(destination);
    }
}
