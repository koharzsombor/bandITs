/**
 *
 */
public class SemiFertileTecton extends Tecton {
    /**
     *
     */
    public SemiFertileTecton() {
        setMyceliaCapacity(3);
    }

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
        System.out.printf("%s %n", Main.objectNames.get(this));

        if (getMycelia().size() >= getMyceliaCapacity()) {
            System.out.printf("\t=delete()=> %s", Main.objectNames.get(mycelium));
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        System.out.println("\t=size()=> TectonSpores");
        System.out.println("\t<=sporeCount= TectonSpores");
        int sporeCount = getSpores().size();

        System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mycelium));
        mycelium.grow(sporeCount);
    }

    /**
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {

    }
}
