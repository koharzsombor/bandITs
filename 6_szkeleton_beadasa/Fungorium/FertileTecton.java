import java.util.ArrayList;

/**
 *  A FertileTecton egy olyan tekton amelyen gombatest es gombafonal is novekedhet
 *  Legfeljebb 1 gombafonal es 1 gombatest novekedhet rajta
 */
public class FertileTecton extends Tecton {
    /**
     *
     */
    public FertileTecton() {
        setMyceliaCapacity(1);
        setBreakTimer(1);
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
          System.out.println("\t=size()=> TectonSpores");
          System.out.println("\t<=sporeCount= TectonSpores");
        }
          
        int sporeCount = getSpores().size();
          
        if (Main.printTrace)
          System.out.printf("\t=grow(sporeCount)=> %s %n", Main.objectNames.get(mycelium));
          
        mycelium.grow(sporeCount);
    }

    /**
     * Elfogad egy MushroomBodyGrowthEvaluator es egy MushroomBody objektumot,
     * es vegrehajtja a novekedest vagy az adott MushroomBody torleset aszerint hogy
     * megvannak a megfelelo korulmenyek-e vagy sem a gombatest novekedesere
     * @param mushroomBodyGrowthEvaluator - Az evaluator, ennek segitsegevel tud a gombatest
     *                                    kommunikalni a tektonnal
     * @param mushroomBody - A gombatest amely szeretne az adott tektonra ranolni
     */
    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (Main.printTrace) {
            System.out.printf("%s\n", Main.objectNames.get(this));
        }

        // Logika a novekedes vagy torlesi folyamat alapjan
        // Pontosabban ha van legalabb 3 spora, nincs meg gombatest a tektonon es van legalabb egy gombafonal
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

    /**
     * A kor/Turn kezdeten hivodo metodus
     * Itt tortenik a tektontores es annak kovetkezmenyei
     * (myceliumok elvagasa, Insectek elmenekulese, )
     */
    @Override
    public void onRoundBegin() {

        boolean originalPrintTrace = Main.printTrace;

        if (Main.printTrace) {
            System.out.printf("\t=onTurnBegin()=> A: FertileTecton%n");
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
