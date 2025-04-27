/**
 * Az alapvető gombafonál funkciókon kívül speciális feltételek között a rovarak evését és fonál
 * növesztését megvalósító osztály.
 */
import java.util.*;

public class CarnivorousMycelium extends MyceliumImpl{

    /**
     * Létrehoz egy üres gombafonalat, ami nincs rajta egy tektonon.
     * Csak a játékmező létrehozására szabad használni.
     */
    public CarnivorousMycelium() {
        super();
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public CarnivorousMycelium(FertileTectonImpl location) {
        super(location);
    }

    /**
     * Létrehoz egy gombafonalat az adott tektonra, ha a tekton befogadja, akkor létrejön a tektonon, ha nem megsemmisül.
     * Azért, hogy a visitor egyértelműen el tudja, dönteni, hogy milyen tekton-t kell meglátogatnia,
     * mindegyik tekton típusra kell egy külön konstruktor.
     * @param location A tekton, ahová a gombafonál nőni szeretne.
     */
    public CarnivorousMycelium(SemiFertileTectonImpl location) {
        super(location);
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