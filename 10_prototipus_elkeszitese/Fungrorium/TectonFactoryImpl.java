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
                return new FertileTectonImpl();
            }
            case "semifertiletecton" -> {
                return new SemiFertileTectonImpl();
            }
            case "multilayeredtecton" -> {
                return new MultiLayeredTectonImpl();
            }
            case "aridtecton"-> {
                return new AridTectonImpl();
            }
            case "sustainingtecton" -> {
                return new SustainingTectonImpl();
            }
            default -> throw new UnsupportedOperationException("Not implemented!");
        }
    }
}
