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
        if (Main.printTrace) {
            System.out.printf("%s\n", Main.objectNames.get(this));
        }

        if (getMycelia().size() >= getMyceliaCapacity()) {
            if (Main.printTrace) {
                System.out.printf("\t=delete()=> %s", Main.objectNames.get(mycelium));
            }
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        if (Main.printTrace) {
            System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mycelium));
        }
        mycelium.grow(getSpores().size());
    }

    /**
     * @param mushroomBodyGrowthEvaluator
     * @param mushroomBody
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (Main.printTrace) {
            System.out.printf("%s\n", Main.objectNames.get(this));
        }

        if (getSpores().size() != 3 || getMushroomBody() == null || getMycelia().isEmpty()) {
            if (Main.printTrace) {
                System.out.printf("\t=delete()=> %s", Main.objectNames.get(mushroomBody));
            }
            mushroomBody.delete();
            return;
        }

        setMushroomBody(mushroomBody);

        if (Main.printTrace) {
            System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mushroomBody));
        }
        mushroomBody.grow(getSpores().size());
    }

    /**
     *
     */
    @Override
    public void onRoundBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
