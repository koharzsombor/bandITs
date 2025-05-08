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
            case "speedspore" -> {
                SpeedSpore spore = new SpeedSpore();
                ObjectRegistry.registerObject(name, spore);
                return spore;
            }
            case "stunspore" -> {
                StunSpore spore = new StunSpore();
                ObjectRegistry.registerObject(name, spore);
                return spore;
            }
            case "splitspore" -> {
                SplitSpore spore = new SplitSpore();
                ObjectRegistry.registerObject(name, spore);
                return spore;
            }
            case "preventcutspore" -> {
                PreventCutSpore spore = new PreventCutSpore();
                ObjectRegistry.registerObject(name, spore);
                return spore;
            }
            case "slownessspore" -> {
                SlownessSpore spore = new SlownessSpore();
                ObjectRegistry.registerObject(name, spore);
                return spore;
            }
            default -> throw new UnsupportedOperationException("Spore type not implemented");
        }
    }
}
