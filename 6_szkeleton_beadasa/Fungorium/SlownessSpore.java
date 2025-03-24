/**
 * Olyan spóra, amelynek elfogyasztása lelassítja a rovart, így egy kör alatt csak 1 lépést tehet meg.
 * Ez a hatás 3 körön át tart.
 */
public class SlownessSpore extends Spore {
    /**
     * A rovar lassú állapotba kerül a spóra megevése következtében.
     * @param insect A rovar ami a spórát megeszi.
     */
    @Override
    public void eatSpore(Insect insect) {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=beSlow()=> %s", Main.objectNames.get(insect));
        }

        insect.beSlow();
    }
}
