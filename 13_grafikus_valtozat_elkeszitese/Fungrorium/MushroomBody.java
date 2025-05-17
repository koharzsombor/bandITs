/**
 * A Model réteg által használt metódusok a gombatest vonatkozásában.
 * Örökli a Controller és View rétegek műveleteit és tartalmazza a viselkedésre vonatkozó logikát.
 */
public interface MushroomBody extends MushroomBodyControl, MushroomBodyView, Mushroom {
    /**
     * Frissíti a spórakilövés szempontjából aktuálisan elérhető potenciális céltektonok listáját.
     */
    void updateReachableTectons();
}
