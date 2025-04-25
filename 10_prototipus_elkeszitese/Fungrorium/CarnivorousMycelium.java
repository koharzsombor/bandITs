/**
 * Az alapvető gombafonál funkciókon kívül speciális feltételek között a rovarak evését és fonál
 * növesztését megvalósító osztály.
 */
import java.util.*;

public class CarnivorousMycelium extends MyceliumImpl{
    public CarnivorousMycelium() {
        super();
    }

    public CarnivorousMycelium(FertileTectonImpl location) {
        super(location);
    }

    public CarnivorousMycelium(SemiFertileTectonImpl location) {
        super(location);
    }

    /**
     * Amikor kör kezdődik, a növekedési idő csökken, és ha az időzítő lejárt, akkor befejezi a növekedést.
     */
    @Override
    public void onTurnBegin() {
        if (growing) {
            growTimer--;
            growing = growTimer <= 0;
            return;
        }

        if(deathTimer==0){
            this.cutImmediate();
            return;
        }

        if(deathTimer>0){
            deathTimer--;
        }
        location.killOccupants();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(ObjectRegistry.lookupName(this)); sb.append(": CarnivorousMycelium\n");
        sb.append("\tgrowing boolean = "); sb.append(growing); sb.append("\n");
        sb.append("\tlocation Tecton = "); sb.append(location); sb.append("\n");
        sb.append("\tgrowTimer int = "); sb.append(growTimer); sb.append("\n");
        sb.append("\tdeathTimer int = "); sb.append(deathTimer); sb.append("\n");
        return sb.toString();
    }
}