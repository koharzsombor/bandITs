/**
 * Olyan spóra, amelynek elfogyasztása felgyorsítja a rovart,
 * így egy kör alatt 3 lépést tehet meg. Ez a hatás 3 körön át tart.
 */
public class SpeedSpore extends Spore {
    /**
     * A rovar gyors állapotba kerül a spóra megevése következtében.
     * @param insect A rovar ami a spórát megeszi.
     */
    @Override
    public void eatSpore(Insect insect) {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=beFast()=> %s", Main.objectNames.get(insect));
        }

        insect.beFast();
    }
}
