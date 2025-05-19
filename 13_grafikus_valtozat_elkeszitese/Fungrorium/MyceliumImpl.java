/**
 * Gombafonál – olyan gombarész, amelyre gombatest nőhet.
 * Elvágása esetén a gombafonál akkor is fennmarad, ha olyan tektonon halad át, amelyen található gombatest.
 * Új gombatestet olyan gombafonál növeszthet, amely összeköttetésben áll az eredeti gombatestével.
 */

import java.util.*;

public class MyceliumImpl implements Mycelium{

    /**
     * Azt ábrázolja, hogy a gombafonál éppen növekedés alatt van-e.
     */
    protected boolean growing = false;

    /**
     * A tekton, ahol a gombafonál elhelyezkedik.
     */
    protected Tecton location;

    /**
     * Az idő, ami alatt a gombafonál megnő.
     */
    protected int growTimer = 0;

    public int getGrowTimer() {
        return growTimer;
    }

    /**
     * Az idő, ami mulva elszakad a fonál.
     */
    protected int deathTimer= -1;

    /**
     * Létrehoz egy üres gombafonalat, ami nincs rajta egy tektonon.
     * Csak a játékmező létrehozására szabad használni.
     */
    public MyceliumImpl() {
        MyceliumAbstractFactory myceliumAbstractFactory = new SwingMyceliumFactory();
        myceliumAbstractFactory.onCreateMycelium(this);
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public MyceliumImpl(FertileTectonImpl location) {
        this.location = location;

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);
        myceliumGrowthEvaluator.visit(location);
        MyceliumAbstractFactory myceliumAbstractFactory = new SwingMyceliumFactory();
        myceliumAbstractFactory.onCreateMycelium(this);
        ViewRepository.updateObject(location);
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public MyceliumImpl(SemiFertileTectonImpl location) {
        this.location = location;

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);
        myceliumGrowthEvaluator.visit(location);
        MyceliumAbstractFactory myceliumAbstractFactory = new SwingMyceliumFactory();
        myceliumAbstractFactory.onCreateMycelium(this);
        ViewRepository.updateObject(location);
    }

    /**
     * A függvény kitörli a gombafonalat.
     */
    @Override
    public void delete() {
        location.getMycelia().remove(this);
        location=null;
    }

    /**
     * A growing attribútum getterje.
     * @return Éppen növekedés alatt van-e a gombafonál.
     */
    @Override
    public boolean isGrowing() {
        return growing;
    }

    /**
     * A growing attribútum setterje.
     * @param growing A gombafonál növekedés alatt lesz-e.
     */
    @Override
    public void setGrowing(boolean growing) {
        this.growing = growing;
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
     * A location attribútum getterje.
     * @return A tekton, ahol a gombafonál van.
     */
    @Override
    public Tecton getLocation() {
        return location;
    }

    /**
     * A location attribútum setterje.
     * @param location A tekton, ahová a gombafonál kerül.
     */
    @Override
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * A gombafonál elvágódik, ezzel szól a többi gombafonálnak, hogy nézzék meg, hogy hozzá vannak-e csatlakoztatva
     * egy gombatesthez.
     */
    @Override
    public void cutImmediate(){
        Tecton tmpLocation = location;
        this.delete();
        tmpLocation.checkNeighbourMyceliaSustain();
        if(tmpLocation.getMycelia().isEmpty()){
            ArrayList<Insect> temp = new ArrayList<>(tmpLocation.getOccupants());
            for (Insect insect : temp) {
                insect.runAway();
            }
        }
        ViewRepository.updateObject(tmpLocation);
    }

    /**
     * Beállítja a deathTimer-t 2-re. A rovar fonalvágáskor ezt használja
     */
    @Override
    public void cutWithDelay(){
        if(deathTimer!=-1){
            return;
        }
        deathTimer=2;
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
            }
        }
    }

    /**
     * To string, a kiiráshoz
     * @return a Myceliumimpl tulajdonságainak formázott stringje
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(ObjectRegistry.lookupName(this)); sb.append(": Mycelium\n");
        sb.append("\tgrowing boolean = "); sb.append(growing); sb.append("\n");
        sb.append("\tlocation Tecton = "); sb.append(ObjectRegistry.lookupName(location)); sb.append("\n");
        sb.append("\tgrowTimer int = "); sb.append(growTimer); sb.append("\n");
        sb.append("\tdeathTimer int = "); sb.append(deathTimer); sb.append("\n");
        return sb.toString();
    }
}

