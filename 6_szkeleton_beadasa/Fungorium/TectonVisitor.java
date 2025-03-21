/**
 *
 */
public abstract class TectonVisitor {
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
    public abstract Mushroom getCreator();
}
