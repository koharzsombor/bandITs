/**
 * Osztály a MushroomBody-hoz tartozó grafikus nézet létrehozásához.
 * Amikor egy MushroomBody példány létrejön, automatikusan hozzárendelődik egy SwingMushroomBody objektum.
 */
public class SwingMushroomBodyFactory implements MushroomBodyAbstractFactory {

    /**
     * Privát konstruktor.
     */
    private SwingMushroomBodyFactory() {}

    /**
     * Akkor hívódik meg, amikor egy MushroomBody példány létrejön.
     * Ekkor automatikusan létrejon egy SwingMushroomBody példány is, amely a ViewRepository-ban regisztrálásra kerül.
     *
     * @param mb az újonnan létrehozott MushroomBody objektum
     */
    public static void onCreateMushroomBody(MushroomBody mb) {
        SwingMushroomBody smb = new SwingMushroomBody(mb);
        ViewRepository.bind(mb, smb);
    }
}
