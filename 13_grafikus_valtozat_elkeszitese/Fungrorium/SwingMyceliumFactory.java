/**
 * A grafikusan megjeleníthető Mycelium-oknak egy factory-ja
 */
public class SwingMyceliumFactory implements MyceliumAbstractFactory{

    /**
     * Az alapvető myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus Mycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */
    public  void onCreateMycelium(Mycelium m) {
        SwingMycelium sm = new SwingMycelium(m);
        ViewRepository.bind(m,sm);
    }

    /**
     * A húsevő myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus CarnivorousMycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */

    public  void onCreateCarnivorousMycelium(Mycelium m) {
        SwingCarnivorousMycelium scm = new SwingCarnivorousMycelium(m);
        ViewRepository.bind(m,scm);
    }
}
