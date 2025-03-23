/**
 * Egy gombafonált ábrázoló osztály.
 */
public class Mycelium extends Mushroom {
    /**
     * Létrehoz egy üres gombafonalat, ami nincs rajta egy tektonon.
     * Csak a játékmező létrehozására szabad használni.
     */
    public Mycelium() {}

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     * @param name A konzolra kiírt objektum név. Csak a szkeleton programban szükséges.
     */
    public Mycelium(FertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(myceliumGrowthEvaluator, "MGE: MyceliumGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(myceliumGrowthEvaluator));
        }

        myceliumGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(myceliumGrowthEvaluator);
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     * @param name A konzolra kiírt objektum név. Csak a szkeleton programban szükséges.
     */
    public Mycelium(SemiFertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(myceliumGrowthEvaluator, "MGE: MyceliumGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(myceliumGrowthEvaluator));
        }

        myceliumGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(myceliumGrowthEvaluator);
    }

    /**
     * Azt ábrázolja, hogy a gombafonál éppen növekedés alatt van-e.
     */
    private boolean growing = false;

    /**
     * A tekton, ahol a gombafonál elhelyezkedik.
     */
    private Tecton location;

    /**
     * Az idő, ami alatt a gombafonál megnő.
     */
    private int growTimer = 0;

    /**
     * A függvény kitörli a gombafonalat.
     */
    @Override
    public void delete() {
        Main.mockDeletion(this);
    }

    /**
     * A függvény hívására a gombafonál megkezdi a növekedést.
     * @param sporeCount A tektonon lévő spórák száma, ez alapján dönti el, hogy mennyi idő lesz növekedni.
     */
    @Override
    public void grow(int sporeCount) {
        growing = true;
        growTimer = sporeCount > 0 ? 1 : 2;
    }

    /**
     * Amikor kör kezdődik, a növekedési idő csökken, és ha az időzítő lejárt, akkor befejezi a növekedést.
     */
    @Override
    public void onTurnBegin() {
        if (growing) {
            growTimer--;
            growing = growTimer <= 0;
        }
    }

    /**
     * A growing attribútum getterje.
     * @return Éppen növekedés alatt van-e a gombafonál.
     */
    public boolean isGrowing() {
        return growing;
    }

    /**
     * A growing attribútum setterje.
     * @param growing A gombafonál növekedés alatt lesz-e.
     */
    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    /**
     * A gombafonál elvágódik, ezzel szól a többi gombafonálnak, hogy nézzék meg, hogy hozzá vannak-e csatlakoztatva
     * egy gombatesthez.
     */
    public void cut() {
        delete();
        location.checkNeighbourMyceliaSustain();
    }

    /**
     * A location attribútum getterje.
     * @return A tekton, ahol a gombafonál van.
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * A location attribútum setterje.
     * @param location A tekton, ahová a gombafonál kerül.
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }
}
