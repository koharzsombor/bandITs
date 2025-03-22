/**
 *  Az AridTecton egy olyan FertileTecton amelyen a rajta levo fonalak
 *  5 kor/Turn utan felszivodnak/megsemmisulnek
 *  Kepes interakciokba lepni a MushroomBodyGrowthEvaluator es MushroomBody objektumokkal,
 *  valamint reagal minden kor/Turn elejen
 */
public class AridTecton extends FertileTecton implements OnTurnBeginSubscriber {

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
     *  A kor/Turn kezdeten hivodo metodus
     *  Jelenleg nem tartalmaz implementaciot
     */
    @Override
    public void onTurnBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
