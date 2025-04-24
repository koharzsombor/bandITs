public class SporeFactoryImpl implements SporeFactory {
    /**
     * Egy új spórát létrehoz a megadott paraméterekkel.
     *
     * @param name A spóra neve.
     * @param type A spóra típusa.
     * @return Az új spóra.
     */
    @Override
    public Spore create(String name, String type) {
        switch (type.toLowerCase()) {
            default -> throw new UnsupportedOperationException("Not implemented");
        }
    }
}
