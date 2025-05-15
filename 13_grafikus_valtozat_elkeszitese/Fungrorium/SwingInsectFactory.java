public class SwingInsectFactory implements InsectAbstractFactory {
    /**
     * Statikus osztály, tehát hidden a constructor
     */
    private SwingInsectFactory() {}

    /**
     * Amikor létrejön egy Insect, létrejön egy SwingInsect is
     * @param insect a létrejött insect
     */
    public static void onCreateInsect(Insect insect) {
        SwingInsect swingInsect = new SwingInsect(insect);
        ViewRepository.bind(insect, swingInsect);
    }
}
