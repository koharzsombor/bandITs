/**
 * Osztály a MushroomBody-hoz tartozó grafikus nézet létrehozásához.
 * Amikor egy MushroomBody példány létrejön, automatikusan hozzárendelődik egy SwingMushroomBody objektum.
 */
public class SwingMushroomBodyFactory implements MushroomBodyAbstractFactory {

    /**
     * Akkor hívódik meg, amikor egy MushroomBody példány létrejön.
     * Ekkor automatikusan létrejon egy SwingMushroomBody példány is, amely a ViewRepository-ban regisztrálásra kerül.
     *
     * @param mb Az újonnan létrehozott MushroomBody objektum.
     */
    public void onCreateMushroomBody(MushroomBody mb) {
        SwingMushroomBody smb = new SwingMushroomBody(mb);
        ViewRepository.bind(mb, smb);
    }
}
