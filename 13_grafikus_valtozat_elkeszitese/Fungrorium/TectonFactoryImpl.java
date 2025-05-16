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
            case "fertiletecton" -> {
                FertileTectonImpl t = new FertileTectonImpl();
                ObjectRegistry.registerObject(name, t);

                SwingTectonFactory.onCreateTecton(t);

                return t;
            }
            case "semifertiletecton" -> {
                SemiFertileTectonImpl t = new SemiFertileTectonImpl();
                ObjectRegistry.registerObject(name, t);
                return t;
            }
            case "multilayeredtecton" -> {
                MultiLayeredTectonImpl t = new MultiLayeredTectonImpl();
                ObjectRegistry.registerObject(name, t);
                return t;
            }
            case "aridtecton"-> {
                AridTectonImpl t = new AridTectonImpl();
                ObjectRegistry.registerObject(name, t);
                return t;
            }
            case "sustainingtecton" -> {
                SustainingTectonImpl t = new SustainingTectonImpl();
                ObjectRegistry.registerObject(name, t);
                return t;
            }
            default -> throw new UnsupportedOperationException("Not implemented!");
        }
    }
}
