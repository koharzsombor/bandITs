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
     * A gombatest körét megelőzően ez alapján generálódnak a spórák egyedi nevei.
     * Az elnevezési konvenció a következő:
     * [gombatest neve]-[a spóra típusára utaló elnevezés, azaz StunSpore esetén stuns; PreventCutSpore esetén prevents;
     * SlownessSpore esetén slows; SpeedSpore esetén speeds; SplitSpore esetén splits]
     * [1-től kezdődő, folytatólagos számozás – minden spóratípus esetén külön-külön].
     */
    private final Map<String, Integer> sporeTypeCounters = new HashMap<>();

    /**
     * A spórakilövés során aktuálisan elérhető tektonokat tartalmazza.
     */
    private final Set<Tecton> reachableTectons = new HashSet<>();

    /**
     * Konstruktor, amely beállítja a tektont (céltekton), amelyen a gombatest elhelyezésre kerül.
     * Ez a konstruktor használandó FertileTecton, továbbá a FertileTecton valamennyi leszármazottja,
     * azaz AridTecton, MultiLayeredTecton és SustainingTecton esetén.
     *
     * @param location A céltekton, amelyen a gombatest elhelyezésre kerül.
     */
    public MushroomBodyImpl(FertileTectonImpl location) {
        this.location = location;
        MushroomBodyGrowthEvaluator evaluator = new MushroomBodyGrowthEvaluator(this);
        evaluator.visit(location);
    }

    /**
     * Konstruktor, amely beállítja a tektont (céltekton), amelyen a gombatest elhelyezésre kerül.
     * Ez a konstruktor használandó SemiFertileTecton esetén.
     *
     * @param location A céltekton, amelyen a gombatest elhelyezésre kerül.
     */
    public MushroomBodyImpl(SemiFertileTectonImpl location) {
        this.location = location;
        MushroomBodyGrowthEvaluator evaluator = new MushroomBodyGrowthEvaluator(this);
        evaluator.visit(location);
        MushroomBodyAbstractFactory swingMBFactory = new SwingMushroomBodyFactory();
        swingMBFactory.onCreateMushroomBody(this);
    }

    /**
     * Paraméter nélküli konstruktor.
     */
    public MushroomBodyImpl() {
        MushroomBodyAbstractFactory swingMBFactory = new SwingMushroomBodyFactory();
        swingMBFactory.onCreateMushroomBody(this);
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
     * egy új spóra termelődik. A spóra típusa - elvileg - véletlenszerűen kerül kiválasztásra.
     * A metódus elvégzi a keletkezett spóra regisztrációját a {@code sporeTypeCounters} térképnél részletezett
     * elnevezési konvenció alapján.
     */
    @Override
    public void onTurnBegin() {
        if (remainingEjects <= 0)
            return;

        int random = 4;
        //int random = new Random().nextInt(5) + 1;
        Spore newSpore = switch (random) {
            case 1 -> new SplitSpore();
            case 2 -> new StunSpore();
            case 3 -> new PreventCutSpore();
            case 4 -> new SpeedSpore();
            case 5 -> new SlownessSpore();
            default -> throw new IllegalStateException("Error occurred when generating a new Spore" +
                    "for the MushroomBody named " + ObjectRegistry.lookupName(this) +
                    " before its turn!");
        };
        addSpore(newSpore);

        String sporeType = getSporeTypeName(newSpore);
        int count = sporeTypeCounters.getOrDefault(sporeType, 0) + 1;
        sporeTypeCounters.put(sporeType, count);

        String mushroomBodyName = ObjectRegistry.lookupName(this);
        String sporeName = mushroomBodyName + "-" + sporeType + count;

        ObjectRegistry.registerObject(sporeName, newSpore);
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

    /**
     * Segédfüggvény a gombatest által termelt spórák elnevezéséhez.
     * A visszatérési érték a spóra típusának megfelelő rövid kifejezés,
     * amelyet a {@code sporeTypeCounters} térkép is használ névgeneráláshoz (az elnevezési konvenciót lásd ott).
     *
     * @param spore A spóra, amelynek a típusához tartozó rövid elnevezésre szükség van.
     * @return A spórafajtához tartozó rövid típusnév.
     */
    private String getSporeTypeName(Spore spore) {
        if (spore instanceof StunSpore)
        {
            return "stuns";
        }
        if (spore instanceof PreventCutSpore)
        {
            return "prevents";
        }
        if (spore instanceof SlownessSpore)
        {
            return "slows";
        }
        if (spore instanceof SpeedSpore)
        {
            return "speeds";
        }
        if (spore instanceof SplitSpore)
        {
            return "splits";
        }
        return "unknown";
    }

    /**
     * Frissíti a reachableTectonst az aktuális remainingEjects és location alapján.
     * A gombatest utolsó kilövésénél (fejlett állapot; {@code remainingEjects == 1}) céltekton lehet
     * a gombatest közvetlen szomszédja vagy a szomszéd szomszédja is.
     * A gombatest utolsó kilövését megelőző kilövések során (fejletlen állapot; {@code remainingEjects > 1})
     *  céltekton csak a gombatest közvetlen szomszédja lehet.
     */
    public void updateReachableTectons() {
        reachableTectons.clear();

        if (remainingEjects == 0) {
            return;
        }

        if (remainingEjects == 1) {
            for (Tecton neighbour : location.getNeighbours()) {
                reachableTectons.add(neighbour);
                reachableTectons.addAll(neighbour.getNeighbours());
            }
            reachableTectons.remove(getLocation());
        } else {
            reachableTectons.addAll(location.getNeighbours());
        }
    }

    /**
     * Visszaadja azokat a tektonokat, amelyek célpontjai lehetnek a spórakilövésnek.
     *
     * @return A spórakilövéssel elérhető elérhető céltektonok  halmaza.
     */
    public Set<Tecton> getReachableTectons() {
        this.updateReachableTectons();
        return Collections.unmodifiableSet(reachableTectons);
    }

    /**
     * Metódus a gombatest tulajdonságainak kiírásához.
     *
     * @return A gombatest tulajdonságainak formázott stringje.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(ObjectRegistry.lookupName(this)).append(": MushroomBody\n");
        output.append("\tremainingEjects int = ").append(getRemainingEjects()).append("\n");
        output.append("\tlocation Tecton = ").append(ObjectRegistry.lookupName(getLocation())).append("\n");
        output.append("\tmushroomSpores List<Spore> = {\n");
        for (Spore spore : mushroomSpores) {
            output.append("\t\t").append(ObjectRegistry.lookupName(spore)).append("\n");
        }
        output.append("\t}\n");
        return output.toString();
    }
}
