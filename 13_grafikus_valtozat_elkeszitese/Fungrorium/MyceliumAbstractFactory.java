/**
 * A grafikusan megjeleníthető Mycelium-oknak egy abstract factory-ja
 */

public interface MyceliumAbstractFactory {

    /**
     * Az alapvető myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus Mycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */
    public  void onCreateMycelium(Mycelium m);

    /**
     * A húsevő myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus CarnivorousMycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */
    public  void onCreateCarnivorousMycelium(Mycelium m);

}
