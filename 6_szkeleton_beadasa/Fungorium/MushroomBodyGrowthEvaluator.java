/**
 * A MushroomBodyGrowthEvaluator osztaly a MushroomBody osztaly novekedeset
 * es interakcioit kezeli kulonbozo Tecton-
 * Feladata a Tecton helyszinek meglatogatasa es a kapcsolodo novekedesi folyamatok vegrehajtasa
 */
public class MushroomBodyGrowthEvaluator extends TectonVisitor {
    /**
     * A MushroomBody objektum, amely ezt az evaluatort letrehozta
     */
    private final MushroomBody creator;

    /**
     * Konstruktor a MushroomBodyGrowthEvaluator letrehozasahoz
     * @param mushroom - A MushroomBody, amelyhez ez az evaluator tartoztatva van
     */
    MushroomBodyGrowthEvaluator(MushroomBody mushroom) {
        creator = mushroom;
    }

    /**
     * Meglatogat egy FertileTecton helyszint, es vegrehajtja a kapcsolodo muveleteket
     * @param tecton - Az eppen latogatott FertileTecton objektum
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
     * Meglatogat egy MultiLayeredTecton helyszint, es vegrehajtja a kapcsolodo muveleteket
     * @param tecton - Az eppen latogatott MultiLayeredTecton objektum
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
     * Meglatogat egy AridTecton helyszint, es vegrehajtja a kapcsolodo muveleteket
     * @param tecton - Az eppen latogatott AridTecton objektum
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
     * Meglatogat egy SemiFertileTecton helyszint, es vegrehajtja a kapcsolodo muveleteket
     * @param tecton - Az eppen latogatott SemiFertileTecton objektum
     */
    @Override
    public void visit(SemiFertileTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
        tecton.accept(this, getCreator());
    }

    /**
     * Visszaadja a MushroomBody objektumot, amely az evaluatort letrehozta
     * @return - Az evaluatorhoz tartozo MushroomBody objektum
     */
    @Override
    public MushroomBody getCreator() {
        return creator;
    }
}