/**
 * Spórákat példányosító objektum.
 */
public interface SporeFactory {
    /**
     * Egy új spórát létrehoz a megadott paraméterekkel.
     * @param name A spóra neve.
     * @param type A spóra típusa.
     * @return Az új spóra.
     */
    Spore create(String name, String type);
}
