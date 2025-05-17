/**
 * Az alapvető gombafonál funkciókon kívül speciális feltételek között a rovarak evését és fonál
 * növesztését megvalósító osztály.
 */

public class CarnivorousMyceliumImpl extends MyceliumImpl{

    /**
     * Létrehoz egy üres gombafonalat, ami nincs rajta egy tektonon.
     * Csak a játékmező létrehozására szabad használni.
     */
    public CarnivorousMyceliumImpl() {
        super();
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public CarnivorousMyceliumImpl(FertileTectonImpl location) {
        this.location = location;

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);
        myceliumGrowthEvaluator.visit(location);
        MyceliumAbstractFactory myceliumAbstractFactory = new SwingMyceliumFactory();
        myceliumAbstractFactory.onCreateCarnivorousMycelium(this);
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public CarnivorousMyceliumImpl(SemiFertileTectonImpl location) {
        this.location = location;

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);
        myceliumGrowthEvaluator.visit(location);
        MyceliumAbstractFactory myceliumAbstractFactory = new SwingMyceliumFactory();
        myceliumAbstractFactory.onCreateCarnivorousMycelium(this);
    }

    /**
     * Beállítja a deathTimer-t 2-re. A rovar fonalvágáskor ezt használja
     */
    @Override
    public void cutWithDelay(){
        if(deathTimer!=-1){
            return;
        }
        deathTimer=3;
    }

    /**
     * Amikor kör kezdődik, a növekedési idő csökken, és ha az időzítő lejárt, akkor befejezi a növekedést.
     */
    @Override
    public void onTurnBegin() {
        if (growing) {
            growTimer--;
            growing = growTimer > 0;
            return;
        }

        if(deathTimer>0){
            deathTimer--;
            if(deathTimer<=0){
                this.cutImmediate();
                return;
            }
        }
        if (location != null)
            location.killOccupants();
    }

    /**
     * To string, a kiiráshoz
     * @return a CarnivorousMycelium tulajdonságainak formázott stringje
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(ObjectRegistry.lookupName(this)); sb.append(": CarnivorousMycelium\n");
        sb.append("\tgrowing boolean = "); sb.append(growing); sb.append("\n");
        sb.append("\tlocation Tecton = "); sb.append(ObjectRegistry.lookupName(location)); sb.append("\n");
        sb.append("\tgrowTimer int = "); sb.append(growTimer); sb.append("\n");
        sb.append("\tdeathTimer int = "); sb.append(deathTimer); sb.append("\n");
        return sb.toString();
    }
}