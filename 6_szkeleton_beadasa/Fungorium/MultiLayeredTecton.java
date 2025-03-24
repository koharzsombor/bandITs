/**
 * A MultiLayeredTecton olyan FertileTecton amelyen tobb mint egy
 * mycelium/gombafonal novesztheto, ez elter a FertileTectontol, ahol csak 1 szabad
 * A megengedett gombatestek szama ugyanugy 1 kell maradjon
 */
public class MultiLayeredTecton extends FertileTecton {
    /**
     * Ez a metodus beallitja, hogy pontosan hany mycelium nolhet az adott
     * tektonon, jelen esetben 3
     */
    public MultiLayeredTecton() {
        setMyceliaCapacity(3);
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
}
