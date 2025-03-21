/**
 *
 */
public class MultiLayeredTecton extends FertileTecton {
    /**
     *
     */
    public MultiLayeredTecton() {
        setMyceliaCapacity(3);
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

        if (getSpores().size() < 3 || getMushroomBody() != null || getMycelia().isEmpty()) {
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
}
