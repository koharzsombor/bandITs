/**
 * A SemiFertileTecton egy olyan tekton, melyen nem nolhet gombatest
 * gombafonal mint a legtobb tektonnal csask 1 nolhet
 */
public class SemiFertileTecton extends Tecton {
    /**
     * Ez a metodus beallitja hogy hany mycelium/fonal nolhet az adott tektonon
     */
    public SemiFertileTecton() {
        setMyceliaCapacity(1);
    }

    /**
     * A kor/Turn kezdeten hivodo metodus
     * Jelenleg nem tartalmaz implementaciot
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
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
        }

        if (getMycelia().size() >= getMyceliaCapacity()) {
            if (Main.printTrace) {
                System.out.printf("\t=delete()=> %s %n", Main.objectNames.get(mycelium));
            }
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        if (Main.printTrace) {
            System.out.printf("\t=size()=> TectonSpores");
            System.out.printf("\t<=sporeCount= TectonSpores");
        }

        int sporeCount = getSpores().size();

        if (Main.printTrace) {
            System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mycelium));
        }
        mycelium.grow(sporeCount);
    }

    /**
     * Elfogad egy MushroomBodyGrowthEvaluator es egy MushroomBody objektumot,
     * es mivel nem szabad gombatest noljon vegrehajtja az adott MushroomBody torleset
     * @param mushroomBodyGrowthEvaluator - Az evaluator, ennek segitsegevel tud a gombatest
     *                                    kommunikalni a tektonnal
     * @param mushroomBody - A gombatest amely szeretne az adott tektonra ranolni
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
