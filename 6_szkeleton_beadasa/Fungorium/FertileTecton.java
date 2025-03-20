/**
 *
 */
public class FertileTecton extends Tecton {
    /**
     *
     */
    public FertileTecton() {
        setMyceliaCapacity(1);
    }

    /**
     * @param myceliumGrowthEvaluator
     * @param mycelium
     */
    @Override
    public void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium) {
        System.out.printf("%s\n", Main.objectNames.get(this));

        if (getMycelia().size() >= getMyceliaCapacity()) {
            System.out.printf("\t=delete()=> %s", Main.objectNames.get(mycelium));
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mycelium));
        mycelium.grow(getSpores().size());
    }

    /**
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {

    }

    /**
     *
     */
    @Override
    public void onRoundBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
