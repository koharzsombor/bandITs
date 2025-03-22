/**
 *
 */
public class MyceliumGrowthEvaluator extends TectonVisitor {
    /**
     *
     */
    private final Mycelium creator;

    /**
     * @param mushroom
     */
    MyceliumGrowthEvaluator(Mycelium mushroom) {
        creator = mushroom;
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(FertileTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
      
        tecton.accept(this, getCreator());
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(MultiLayeredTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
        tecton.accept(this, getCreator());
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(AridTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }

        tecton.accept(this, getCreator());
    }

    /**
     * @param tecton
     */
    @Override
    public void visit(SemiFertileTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
        tecton.accept(this, getCreator());
    }

    @Override
    public Mycelium getCreator() {
        return creator;
    }
}