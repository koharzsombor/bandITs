/**
 *
 */
public class SemiFertileTecton extends Tecton{
    /**
     *
     */
    @Override
    public void onRoundBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param MyceliumGrowthEvaluator
     * @param mycelium
     */
    @Override
    public void accept(MyceliumGrowthEvaluator MyceliumGrowthEvaluator, Mycelium mycelium) {

    }

    /**
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
        }
        if (Main.printTrace) {
            System.out.printf("\t=delete()=> %s", Main.objectNames.get(mushroomBody));
        }
        mushroomBody.delete();
    }
}
