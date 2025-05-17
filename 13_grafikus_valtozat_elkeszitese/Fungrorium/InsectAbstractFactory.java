/**
 * Interface a Swing Insect factoryhoz
 */
public interface InsectAbstractFactory {
    /**
     * Amikor létrejön egy Insect, létrejön egy SwingInsect is
     * @param insect a létrejött insect
     */
    void onCreateInsect(Insect insect);
}
