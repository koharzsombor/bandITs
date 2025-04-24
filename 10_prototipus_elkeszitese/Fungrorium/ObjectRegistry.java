import java.util.HashMap;
import java.util.Map;

/**
 * A parancssorból hivatkozható objektumok tára.
 */
public class ObjectRegistry {
    /**
     * A számontartott objektumok tárolója.
     */
    private static final Map<String, Object> registeredObjects = new HashMap<>();

    /**
     * Az osztály nem példányosíthatü, ezért privát konstruktora van.
     */
    private ObjectRegistry() {}

    /**
     * Beír egy megadott névvel a tárba a megadott objektumot.
     * @param name Az objektum neve.
     * @param registeredObject Az objektum ami belekerül a tárba.
     */
    public static void registerObject(String name, Object registeredObject) {
        if (registeredObjects.values().contains(registeredObject))
            throw new UnsupportedOperationException("Can't register an object more than once!");

        registeredObjects.put(name, registeredObject);
    }

    /**
     * Kitörli az összes bejegyzést.
     */
    public static void clearRegistry() {
        registeredObjects.clear();
    }

    /**
     * Kitöröl egy bejegyzést név alapján.
     * @param name A név ami alapján kitörölődik a bejegyzés.
     */
    public static void removeFromRegistry(String name) {
        registeredObjects.remove(name);
    }

    /**
     * Lekérdez egy objektumot a neve alapján.
     * @param name Az objektum neve.
     * @return A keresett objektum.
     */
    public static Object getObject(String name) {
        return registeredObjects.get(name);
    }

    /**
     * Visszafelé keres, azaz megkeresi mi az objektum neve.
     * @param object A keresett objektum.
     * @return A keresett objektum neve.
     */
    public static String lookupName(Object object) {
        return registeredObjects.entrySet().stream().filter(o -> o.getValue().equals(object)).map(Map.Entry::getKey).findFirst().orElse("Not in collection");
    }
}
