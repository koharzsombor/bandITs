import java.util.List;

/**
 * A modellben használ Tecton interfész.
 */
public interface Tecton extends TectonControl, TectonView, RoundBeginSubscriber {
    /**
     * Meghatározza a távolságot a jelen tekton és a megadott tekton között.
     * @param tecton A megadott tekton, amitől a távolságot számoljuk.
     * @return A két tekton közti távolság.
     */
    int distance(Tecton tecton);

    /**
     * Ellenörzi, hogy a szomszédos gombafonállal rendelkező tektonok a gombafonalakat valami
     * életben tartja-e. Ha nem tartja életben semmi akkor kitörli őket.
     *
     * @return
     */
    void checkNeighbourMyceliaSustain();


    /**
     * Hozzáad egyszerre több spórát a tektonhoz.
     * @param newSpores A spórák listája, amiket hozzáadunk az új tektonhoz.
     */
    void transferSpores(List<Spore> newSpores);

    /**
     * Eldönti, hogy az adott gombafonál nőhet-e ezen a tektonon
     * @param myceliumGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mycelium A gombafonál, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium);

    /**
     * Eldönti, hogy az adott gombatest nőhet-e ezen a tektonon.
     * @param mushroomBodyGrowthEvaluator A növekedés eldöntésében segítő objektum.
     * @param mushroomBody A gombatest, amiről eldönti az eljárás, hogy a tektonra nöhet-e.
     */
    void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody);

    /**
     * Eldönti, hogy a tekton képes-e önerőből fenntartani egy gombafonalat.
     * @return A tekton képes-e önerőből fenntartani egy gombafonalat.
     */
    boolean sustaining();

    /**
     * Megpróbál minden rajta lévő rovart eltávolítani.
     */
    void killOccupants();

    /**
     * A megadott rovar megeszi a tektonon lévő, legrégebben ott tartózkodó spórát.
     * @param insect A spórát elfogyasztó rovar.
     */
    void eatSpore(Insect insect);

    /**
     * A tektonon lévő gombafonál elrágása.
     */
    void cutMycelium();

    /**
     * Ha tud a rovar a jelen tectonra mozogni, akkor megcsinálja ezt a műveletet.
     * @param insect A rovar, akit mozgat a függvény.
     */
    void moveInsect(Insect insect);

    /**
     * Visszaadja a szomszédos tektonokat.
     * @return A szomszédos tektonok.
     */
    List<Tecton> getNeighbours();

    void myceliaCheckSustain();
}
