import java.util.List;
import java.util.Set;

/**
 * A View réteg számára elérhető metódusok a gombatest vonatkozásában.
 * Olvasási műveleteket tartalmaznak, állapotot nem módosítanak.
 */
public interface MushroomBodyView {
    /**
     * Visszaadja a spórakilövés szempontjából elérhető potenciális céltektonok listáját
     * az utolsó {@code updateReachableTectons()} hívás alapján.
     */
    Set<Tecton> getReachableTectons();

    /**
     * Visszaadja a gombatest megmaradt spórakilövéseinek számát.
     */
    int getRemainingEjects();

    /**
     * Visszaadja a gombatest spóráit tartalmazó listát.
     */
    List<Spore> getSpores();

    /**
     * Visszaadja a gombatest elhelyezkedése szerinti tektont.
     */
    Tecton getLocation();
}
