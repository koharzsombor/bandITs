/**
 * Olyan spóra, amelynek elfogyasztása megakadályozza a rovart abban, hogy fonalat vágjon el.
 * Ez a hatás 3 körön át tart
 */
public class PreventCutSpore extends Spore {

    /**
     * Egy rovar megeszi a spórát, ennek hatására nem tud gombafonalat elvágni.
     * @param insect A spórát megevő rovar.
     */
    @Override
    public void eatSpore(Insect insect) {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=bePreventCut()=> %s", Main.objectNames.get(insect));
        }

        insect.preventCut();
    }
}
