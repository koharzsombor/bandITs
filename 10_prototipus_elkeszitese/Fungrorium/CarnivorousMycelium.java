/**
 * Az alapvető gombafonál funkciókon kívül speciális feltételek között a rovarak evését és fonál
 * növesztését megvalósító osztály.
 */
import java.util.*;

public class CarnivorousMycelium extends MyceliumImpl{

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
        sb.append(); sb.append(": CarnivorousMycelium\n");
        sb.append("\t"); sb.append("growing boolean = "); sb.append(growing); sb.append("\n");
        sb.append("\t"); sb.append("location Tecton = "); sb.append(location); sb.append("\n");
        sb.append("\t"); sb.append("growTimer int = "); sb.append(growTimer);
        sb.append("\t"); sb.append("deathTimer int = "); sb.append(deathTimer);
        return sb.toString();
    }
}