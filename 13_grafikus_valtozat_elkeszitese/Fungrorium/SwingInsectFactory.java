public class SwingInsectFactory implements InsectAbstractFactory {
    /**
     * Amikor létrejön egy Insect, létrejön egy SwingInsect is
     * @param insect a létrejött insect
     */
    public void onCreateInsect(Insect insect) {
        SwingInsect swingInsect = new SwingInsect(insect);
        ViewRepository.bind(insect, swingInsect);
    }
}
