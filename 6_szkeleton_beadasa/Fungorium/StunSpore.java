/**
 * Olyan spóra, amelyet elfogyasztva a rovar megbénul és a következő 1 kör alatt nem tud semmilyen aktivitást kifejteni.
 */
public class StunSpore extends Spore {
    /**
     * A spórát megevő rovart lebénítja.
     * @param insect A spórát megevő rovar.
     */
    @Override
    public void eatSpore(Insect insect) {
        if (Main.printTrace) {
            System.out.println(Main.objectNames.get(this));
            System.out.printf("\t=beStunned()=> %s", Main.objectNames.get(insect));
        }

        insect.beStunned();
    }
}
