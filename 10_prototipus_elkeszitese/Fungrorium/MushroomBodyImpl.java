import java.util.*;

/**
 * A gombatestekért felelős osztály. A gombatest a spórák termeléséért és kilövéséért felelős.
 * 3 spórakilövés után inaktívvá válik, amely abban nyilvánul meg, hogy a remaingEjects változó értéke 0 lesz.
 * A gombatest az utolsó kilövése előtt csak valamely szomszédjára lőhet spórát.
 * Fejlettnek az utolsó kilövése során minősül. Ilyenkor a szomszédja szomszédjára is tud lőni.
 */
public class MushroomBodyImpl implements MushroomBody {

    /**
     * A megmaradt spórakilövések számát tároló változó.
     * Alapértelmezett értéke 3, amely szám minden kilövéssel csökken - egészen 0-ig.
     * A 0 érték a gombatest inaktivitását jelenti. Ebben az állapotban
     * a gombatest semmilyen aktivitást nem képes kifejteni.
     */
    private int remainingEjects = 3;

    /**
     * A gombatest elhelyezkedése szerinti tektont tároló változó.
     */
    private Tecton location;

    /**
     * A gombatest spóráit tartalmazó lista. A lista alapértelmezetten üres.
     * A gombatest minden kör elején automatikusan egy új spórát növeszt.
     */
    private List<Spore> mushroomSpores = new ArrayList<>();

    /**
     * Konstruktor, amely beállítja a tektont (céltekton), amelyen a gombatest elhelyezésre kerül.
     * Ez a konstruktor használandó FertileTecton, továbbá a FertileTecton valamennyi leszármazottja,
     * azaz AridTecton, MultiLayeredTecton és SustainingTecton esetén.
     *
     * @param location A céltekton, amelyen a gombatest elhelyezésre kerül.
     */
    public MushroomBodyImpl(FertileTecton location) {
        this.location = location;
        MushroomBodyGrowthEvaluator evaluator = new MushroomBodyGrowthEvaluator(this);
        evaluator.visit(location, this);
    }

    /**
     * Konstruktor, amely beállítja a tektont (céltekton), amelyen a gombatest elhelyezésre kerül.
     * Ez a konstruktor használandó SemiFertileTecton esetén.
     *
     * @param location A céltekton, amelyen a gombatest elhelyezésre kerül.
     */
    public MushroomBodyImpl(SemiFertileTecton location) {
        this.location = location;
        MushroomBodyGrowthEvaluator evaluator = new MushroomBodyGrowthEvaluator(this);
        evaluator.visit(location, this);
    }

    /**
     * Paraméter nélküli konstruktor.
     */
    public MushroomBodyImpl() {
    }

    /**
     * A növekedési feltételek hiánya esetén kerül meghívásra az előzetesen létrehozott gombatest törlése céljából.
     */
    @Override
    public void delete() {
        location = null;
        mushroomSpores.clear();
    }

    /**
     * A gombatest növekedési folyamatát lezáró metódus, amelyet a Mushroom interfész miatt szükséges
     * a gombatestnél ilyen formában megvalósítani. A paramétert a céltektontól kapja.
     * A céltekton abban az esetben hívja meg ezt a metódust (és nem a {@link #delete()}-et),
     * ha a gombatest növesztési feltételeire vonatkozó vizsgálat pozitív eredménnyel zárult.
     * A metódus a gombatest esetében nem, csak a gombafonálnál bír jelentőséggel.
     *
     * @param sporeCount A céltektonon lévő spórák száma.
     */
    @Override
    public void grow(int sporeCount) {
    }

    /**
     * A gombatest minden új körének kezdetekor – beleértve a játék első körét is – a gombatestben
     * egy új spóra termelődik. A spóra típusa véletlenszerűen kerül kiválasztásra.
     */
    @Override
    public void onTurnBegin() {
        int random = new Random().nextInt(5) + 1;
        Spore newSpore = switch (random) {
            case 1 -> new SpitSpore();
            case 2 -> new StunSpore();
            case 3 -> new PreventCutSpore();
            case 4 -> new SpeedSpore();
            case 5 -> new SlownessSpore();
        };
        addSpore(newSpore);
    }

    /**
     * Visszaadja a gombatest megmaradt spórakilövéseinek számát.
     *
     * @return A gombatest megmaradt spórakilövéseinek száma.
     */
    public int getRemainingEjects() {
        return remainingEjects;
    }

    /**
     * Beállítja a gombatest megmaradt spórakilövéseinek számát.
     *
     * @param remainingEjects A gombatest megmaradt spórakilövéseinek száma.
     */
    public void setRemainingEjects(int remainingEjects) {
        this.remainingEjects = remainingEjects;
    }

    /**
     * Visszaadja a gombatest spóráit tartalmazó listát.
     *
     * @return A gombatest spóráit tartalmazó lista.
     */
    public List<Spore> getSpores() {
        return mushroomSpores;
    }

    /**
     * Hozzáad egy új spórát a gombatest spóráinak listájához.
     *
     * @param spore A gombatest spóráinak listájához hozzáadandó spóra.
     */
    public void addSpore(Spore spore) {
        mushroomSpores.add(spore);
    }

    /**
     * A gombatest kilövi az összes spóráját a megadott céltektonra, ha az elérhető.
     * Ha a gombatestnek nincs hátralévő kilövése (inaktív; {@code remainingEjects == 0})
     * vagy a céltekton a gombatest számára nem elérhető, a metódus bármilyen művelet végzése nélkül visszatér.
     * A gombatest utolsó kilövésénél (fejlett állapot; {@code remainingEjects == 1}) céltekton lehet
     * a gombatest közvetlen szomszédja vagy a szomszéd szomszédja is.
     * A gombatest utolsó kilövését megelőző kilövések során (fejletlen állapot; {@code remainingEjects > 1})
     * céltekton csak a gombatest közvetlen szomszédja lehet.
     * A spóralövés következtében a gombatest összes spórája átkerül a céltektonra, a spóralistája kiürítésre kerül
     * és a hátralévő spórakilővéseinek száma 1-gyel csökkentésre kerül.
     *
     * @param target A céltekton, amelyre a gombatest valamennyi spórája kilövésre kerül.
     */
    public void ejectSpores(Tecton target) {
        if (remainingEjects == 0) {
            return;
        }

        if (remainingEjects == 1) {
            Set<Tecton> reachable = new HashSet<>();

            for (Tecton primary : location.getNeighbours()) {
                reachable.add(primary);

                for (Tecton secondary : primary.getNeighbours()) {
                    reachable.add(secondary);
                }
            }

            if (reachable.contains(target)) {
                if (!mushroomSpores.isEmpty()) {
                    for (Spore spore : mushroomSpores) {
                        target.addSpore(spore);
                    }
                    mushroomSpores.clear();
                    remainingEjects--;
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            if (location.getNeighbours().contains(target)) {
                if (!mushroomSpores.isEmpty()) {
                    for (Spore spore : mushroomSpores) {
                        target.addSpore(spore);
                    }
                    mushroomSpores.clear();
                    remainingEjects--;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Visszaadja a gombatest elhelyezkedése szerinti tektont.
     *
     * @return A gombatest elhelyezkedése szerinti tekton.
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * Beállítja a gombatest elhelyezkedése szerinti tektont.
     *
     * @param location A gombatest elhelyezkedése szerinti tektonként beállítandó tekton.
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }
}
