/**
 *  A FertileTecton egy olyan tekton amelyen gombatest es gombafonal is novekedhet
 *  Legfeljebb 1 gombafonal es 1 gombatest novekedhet rajta
 */
public class FertileTecton extends Tecton {
    /**
     * Alapvető konstruktor. A gombafonál kapacítást 1-re állítja.
     */
    public FertileTecton() {
        setMyceliaCapacity(1);
    }

    /**
     * Eldönti, hogy a kapott gombafonál nőhet-e ezen a tektonon.
     * @param myceliumGrowthEvaluator A kommunikációt segítő segédobjektum.
     * @param mycelium A gombafonál, ami ide szeretne nőni.
     */
    @Override
    public void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
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
            System.out.printf("%s %n", Main.objectNames.get(this));
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
     * Jelenleg nem tartalmaz implementaciot
     */
    @Override
    public void onRoundBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
