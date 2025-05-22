/**
 * A gombatestet irányító objektum implementációja.
 */
public class MushroomBodyControllerImpl implements MushroomBodyController {

    /**
     * A spórákat példányosító objektum.
     */
    private SporeFactory sporeFactory;

    private final TurnController turnController;

    /**
     * A függőségeit saját maga példányosítsa.
     */
    public MushroomBodyControllerImpl(TurnController turnController) {
        this.turnController = turnController;
        sporeFactory = new SporeFactoryImpl();
    }

    /**
     * Kilövi a gombatesten lévő spórákat.
     *
     * @param mushroomBody A spórákat kilövő gombatest.
     * @param target       A tekton, amire a spórákat lövi.
     */
    @Override
    public void eject(MushroomBody mushroomBody, Tecton target) {
        if (turnController.getCurrentPlayer().controlsMushroomBody(mushroomBody))
            mushroomBody.ejectSpores(target);
    }

    /**
     * A gombatestet inaktív állapotba rakja.
     *
     * @param mushroomBody A gombatest, ami inaktív lesz.
     */
    @Override
    public void deactivate(MushroomBody mushroomBody) {
        mushroomBody.setRemainingEjects(0);
    }

    /**
     * A maradék kilövéseinek a számát beállítja egy adott számra.
     *
     * @param mushroomBody A gombatest, aminek a maradék kilövéseit állítjuk.
     * @param amount       A maradék kilövések száma.
     */
    @Override
    public void setRemainingEjects(MushroomBody mushroomBody, int amount) {
        mushroomBody.setRemainingEjects(amount);
    }

    /**
     * Hozzáadunk egy spórát a gombatesthez.
     *
     * @param sporeType    Az új spóra típusa.
     * @param sporeName    Az új spóra neve.
     * @param mushroomBody A gombatest, amihez a spórát adjuk.
     */
    @Override
    public void addSpores(String sporeType, String sporeName, MushroomBody mushroomBody) {
        mushroomBody.addSpore(sporeFactory.create(sporeName, sporeType));
    }
}
