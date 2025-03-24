/**
 *  Az AridTecton egy olyan FertileTecton amelyen a rajta levo fonalak
 *  5 kor/Turn utan felszivodnak/megsemmisulnek
 *  Kepes interakciokba lepni a MushroomBodyGrowthEvaluator es MushroomBody objektumokkal,
 *  valamint reagal minden kor/Turn elejen
 */
public class AridTecton extends FertileTecton implements OnTurnBeginSubscriber {
    /**
     * Az mutatja, hogy hány kör múlva szívja fel a gombafonalat a tekton.
     */
    private int absorbCountdown = 0;

    /**
     * Eldönti, hogy a kapott gombafonál nőhet-e ezen a tektonon.
     * Ha a gombafonál sikeresen nőtt ide, a felszívódási számláló elkezdődik.
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
        absorbCountdown = 5;
    }

    /**
     * Elfogad egy MushroomBodyGrowthEvaluator es egy MushroomBody objektumot,
     * es vegrehajtja a novekedest vagy az adott MushroomBody torleset aszerint hogy
     * megvannak a megfelelo korulmenyek-e vagy sem a gombatest novekedesere
     * @param mushroomBodyGrowthEvaluator - Az evaluator, ennek segitsegevel tud a gombatest
     *                                    kommunikalni a tektonnal
     * @param mushroomBody - A gombatest amely szeretne az adott tektonra ranolni
     */
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
     * Minden kör elején csökkenti az időzítőt, majd ha eléri a nullát felszívja a tektont.
     */
    @Override
    public void onTurnBegin() {
        if (absorbCountdown > 0) {
            absorbCountdown--;

            if (absorbCountdown <= 0) {
                Mycelium mycelium = getMycelia().poll();

                if (mycelium != null)
                    mycelium.delete();
            }
        }
    }
}
