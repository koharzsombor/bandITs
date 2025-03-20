/**
 *
 */
public abstract class TectonVisitor {

    /**
     *
     */
    private Mushroom creator;

    /**
     * @param mushroom
     */
    TectonVisitor(Mushroom mushroom) {
        this.creator = mushroom;
    }

    /**
     * @param tecton
     */
    public abstract void visit(FertileTecton tecton);

    /**
     * @param tecton
     */
    public abstract void visit(MultiLayeredTecton tecton);

    /**
     * @param tecton
     */
    public abstract void visit(AridTecton tecton);

    /**
     * @param tecton
     */
    public abstract void visit(SemiFertileTecton tecton);

    /**
     * @return
     */
    public Mushroom getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Mushroom creator) {
        this.creator = creator;
    }
}
