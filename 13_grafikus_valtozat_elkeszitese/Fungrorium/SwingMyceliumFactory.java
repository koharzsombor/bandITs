/**
 * A grafikusan megjeleníthető Mycelium-oknak egy factory-ja
 */
public class SwingMyceliumFactory implements MyceliumAbstractFactory{

    private static GameFieldView gameFieldView;

    public static void setGameFieldView(GameFieldView gameFieldView) {
        SwingMyceliumFactory.gameFieldView = gameFieldView;
    }

    /**
     * Az alapvető myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus Mycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */
    public static void onCreateMycelium(Mycelium m) {
        SwingMycelium sm = new SwingMycelium(m, gameFieldView);
        ViewRepository.bind(m,sm);
    }

    /**
     * A húsevő myceliumnak a létrejövésekor lefutó parancs.
     * Létrehoz egy grafikus CarnivorousMycelium-ot és összepárosítja a modellbeli megfelelőjével
     * @param m Amelyik Mycelium meghívta, átadja magát.
     */

    public static void onCreateCarnivorousMycelium(Mycelium m) {
        SwingCarnivorousMycelium scm = new SwingCarnivorousMycelium(m, gameFieldView);
        ViewRepository.bind(m,scm);
    }
}
