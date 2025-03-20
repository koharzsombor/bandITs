/**
 *
 */
public class MushroomBodyGrowthEvaluator extends TectonVisitor {

    /**
     * @param mushroom
     */
    MushroomBodyGrowthEvaluator(Mushroom mushroom) {
        //super(mushroom);
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(FertileTecton tecton) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(MultiLayeredTecton tecton) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(AridTecton tecton) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(SemiFertileTecton tecton) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public Mushroom getCreator() {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
