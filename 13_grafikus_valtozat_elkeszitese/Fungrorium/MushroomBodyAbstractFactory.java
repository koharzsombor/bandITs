/**
 * Interfész a MushroomBody-hoz tartozó grafikus nézet létrehozásához.
 */
public interface MushroomBodyAbstractFactory {

    /**
     * Akkor hívódik meg, amikor egy MushroomBody példány létrejön. Ilyenkor létrejon egy SwingMushroomBody példány is.
     *
     * @param mb az újonnan létrehozott MushroomBody objektum
     */
    static void onCreateMushroomBody(MushroomBody mb) {}
}
