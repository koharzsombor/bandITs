import java.util.ArrayList;

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
        setBreakTimer(1);
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

    /**
     * A kor/Turn kezdeten hivodo metodus
     * Itt tortenik a tektontores es annak kovetkezmenyei
     * (myceliumok elvagasa, Insectek elmenekulese, )
     */
    @Override
    public void onRoundBegin() {

        boolean originalPrintTrace = Main.printTrace;

        if (Main.printTrace) {
            System.out.printf("\t=onTurnBegin()=> %s%n", Main.objectNames.get(this));
        }

        setBreakTimer(getBreakTimer() - 1);

        if (getBreakTimer() <= 0) {

            if (Main.printTrace) {
                System.out.printf("%s\n", Main.objectNames.get(this));
            }


            while( !getMycelia().isEmpty()) {
                Mycelium mycelium = getMycelia().poll();
                assert mycelium != null;

                System.out.printf("\t=cut()=> %s\n", Main.objectNames.get(mycelium));

                Main.printTrace = false;
                mycelium.cut();
                Main.printTrace = originalPrintTrace;
            }

            // Egy temp lista melyben az Insectek lesznek eltarolva mivel sima
            // iteracio kozben ki fognak esni elemek a listabol
            // ez csak egy masolata az eredeti Occupants Listanak
            ArrayList<Insect> temp = new ArrayList<>(getOccupants());

            for (Insect o : temp) {
                if (Main.printTrace) {
                    System.out.printf("\t=runAway()=> %s\n", Main.objectNames.get(o));
                }
                Main.printTrace = false;
                o.runAway();
                Main.printTrace = originalPrintTrace;
            }

            FertileTecton newt = new FertileTecton();
            Main.objectNames.put(newt, "newt: FertileTecton");
            if (Main.printTrace) {
                System.out.printf("\t=Create()=> %s%n", Main.objectNames.get(newt));
            }
            newt.addNeighbour(this);
            if (Main.printTrace) {
                System.out.printf("\t=addNeighbour(%s)=> %s%n", Main.objectNames.get(this), Main.objectNames.get(newt));
            }
            this.addNeighbour(newt);

        }
    }
}
