import java.util.HashMap;
import java.util.Map;

/**
 * A nézeteket és a hozzájuk tartozó objektumoknak az adatbázisa.
 */
public class ViewRepository {
    /**
     * Az osztálynak kizárólag a statikus API-ját lehet használni, ezért a oéldányosítása tiltott.
     */
    private ViewRepository() {}

    /**
     * Egyes objektumokhoz tartozó nézetet tároló objektum.
     */
    private static final Map<Object, Updatable> objectRepository = new HashMap<>();

    /**
     * Frissíti az adott objektumhoz tartozó nézetet.
     * @param object Az objektum, melynek nézetét frissítsük.
     */
    public static void updateObject(Object object) {
        objectRepository.get(object).update();
    }

    /**
     * Hozzáköt egy objektumhoz egy nézethez.
     * @param model Az objektum ami alapján frissítünk.
     * @param view Az objektum amit frissítünk.
     */
    public static void bind(Object model, Updatable view) {
        objectRepository.put(model, view);
    }
}
