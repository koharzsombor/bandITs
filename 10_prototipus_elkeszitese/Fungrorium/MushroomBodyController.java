/**
 * A gombatesteket irányító objektum.
 */
public interface MushroomBodyController extends CommandHandler {

    /**
     * Kilövi a gombatesten lévő spórákat.
     * @param mushroomBody A spórákat kilövő gombatest.
     * @param target A tekton, amire a spórákat lövi.
     */
    void eject(MushroomBody mushroomBody, Tecton target);

    /**
     * A gombatestet inaktív állapotba rakja.
     * @param mushroomBody A gombatest, ami inaktív lesz.
     */
    void deactivate(MushroomBody mushroomBody);

    /**
     * A maradék kilövéseinek a számát beállítja egy adott számra.
     * @param mushroomBody A gombatest, aminek a maradék kilövéseit állítjuk.
     * @param amount A maradék kilövések száma.
     */
    void setRemainingEjects(MushroomBody mushroomBody, int amount);

    /**
     * Hozzáadunk egy spórát a gombatesthez.
     * @param sporeType Az új spóra típusa.
     * @param sporeName Az új spóra neve.
     * @param mushroomBody A gombatest, amihez a spórát adjuk.
     */
    void addSpores(String sporeType, String sporeName, MushroomBody mushroomBody);
}
