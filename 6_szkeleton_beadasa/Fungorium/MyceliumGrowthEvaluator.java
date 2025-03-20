/**
 *
 */
public class MyceliumGrowthEvaluator extends TectonVisitor {

    /**
     * @param mushroom
     */
    MyceliumGrowthEvaluator(Mycelium mushroom) {
        super(mushroom);
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(FertileTecton tecton) {
        System.out.printf("%s\n", Main.objectNames.get(this));

        System.out.printf("\t=accept(%s, %s)=> %s\n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        tecton.accept(this, (Mycelium)getCreator());
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
}
