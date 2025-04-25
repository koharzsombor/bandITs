public class TectonFactoryImpl implements TectonFactory {

    /**
     * Példányosít egy tektont a megadott paraméterek alapján
     *
     * @param type A tekton típusa.
     * @param name A tekton neve.
     * @return Az új tekton.
     */
    @Override
    public Tecton create(String type, String name) {
        switch (type.toLowerCase()) {
            default:
                throw new UnsupportedOperationException("Not implemented!");
        }
    }
}
