import java.util.ArrayList;

/**
 *
 */
public class MushroomBody extends Mushroom {
    /**
     * Default konstruktor
     */
    public MushroomBody() {

    }
    /**
     * A megmaradt kilovesek szamat tarolja
     */
    private int remainingEjects;

    /**
     * Azt a tektont tarolja melyen van az adott gombatest
     */
    private Tecton location;

    /**
     * Ez a lista tárolja a MushroomBody által termelt spórákat
     */
    private ArrayList<Spore> MushroomSpores = new ArrayList<Spore>();

    /**
     * Konstruktor FertileTecton, AridTecton es MultiLayeredTecton eseten, itt eleg a Fertile Tectont
     * hasznalni mivel a masik ketto ennek leszarmazottja
     * @param location - A tekton ahol a gombatest letre szeretne jonni
     * @param name - a neve az adott gombatestnek, ez a kiirashoz kell
     */
    public MushroomBody(FertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator = new MushroomBodyGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(mushroomBodyGrowthEvaluator, "MBGE: MushroomBodyGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(mushroomBodyGrowthEvaluator));
        }

        mushroomBodyGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(mushroomBodyGrowthEvaluator);

    }

    /**
     * Konstruktor SemiFertileTecton eseten
     * @param location - A tekton ahol a gombatest letre szeretne jonni
     * @param name - a neve az adott gombatestnek, ez a kiirashoz kell
     */
    public MushroomBody(SemiFertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator = new MushroomBodyGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(mushroomBodyGrowthEvaluator, "MBGE: MushroomBodyGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(mushroomBodyGrowthEvaluator));
        }

        mushroomBodyGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(mushroomBodyGrowthEvaluator);

    }


    /**
     *  A test torlesere szolgalo metodus
     *  Szol a Main osztalynak is a mockDeletion fuggvenyenek
     */
    @Override
    public void delete() {
        if (Main.printTrace) {
            System.out.printf("%n");
        }
        Main.mockDeletion(this);
    }

    /**
     * A Jelenleg nem tartalmaz implementaciot
     * @param sporeCount - Az absztrakt Mushroom osztalybol valo orokles miatt szukseges
     *                   itt nem, csak a Mycelium novesztesekor fontos, a novesztes sebessege miatt
     *                   A gombatest novesztese azonnali
     */
    @Override
    public void grow(int sporeCount) {
        // Currently nothing
    }

    /**
     * A jatek koroz kezdetekor torteno akciok ide kerulnek, jelenleg nem implementalva
     */
    @Override
    public void onTurnBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Visszaadja a megmaradt kilovesek szamat
     * @return - a megmaradt kilovesek szama
     */
    public int getRemainingEjects() {
        return remainingEjects;
    }

    /**
     * Beallitja a megmaradt kilovesek szamat
     * @param remainingEjects - Az uj kilovesek szama
     */
    public void setRemainingEjects(int remainingEjects) {
        this.remainingEjects = remainingEjects;
    }

    /**
     * @return
     */
    public ArrayList<Spore> getSpores() {
        return MushroomSpores;
    }

    /**
     * Hozzáadja a newSpore-t a spórák listájához
     * @param newSpore
     */
    public void addSpore(Spore newSpore) {
        MushroomSpores.add(newSpore);
    }

    /**
     * Sporakat lo ki egy adott tektonra
     * @param target - A celpont tekton, ahova a sporakat szeretnenk kiloni
     */
    public void ejectSpores(Tecton target) {
        if(!MushroomSpores.isEmpty() && getRemainingEjects() > 0) {
            if (Main.printTrace) {
                System.out.println(Main.objectNames.get(this));
                System.out.printf("\t=distance(%s)=> %s %n \t<=distance: int =%s %n ", Main.objectNames.get(target), Main.objectNames.get(location), Main.objectNames.get(location));
            }
            int distance = location.distance(target);
            if ((distance == 1 && getRemainingEjects()>=1) || (distance == 2 && getRemainingEjects() == 1)) {
                if (Main.printTrace) {
                    System.out.printf("\t=transferSpores(spores)=> %s %n", Main.objectNames.get(target));
                }
                target.transferSpores(MushroomSpores);
                MushroomSpores.clear();
                setRemainingEjects(getRemainingEjects()-1);
            }
        }
    }

    /**
     * Visszaadja a helyszint, ahol a gombatest talalhato
     * @return - a tekton amin az adott gombatest van
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * Beallitja a test helyszinet vagyis hogy melyik tektonon van
     * @param location - A tekton ahova szeretnenk elhelyezni a gombatestet
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }
}
