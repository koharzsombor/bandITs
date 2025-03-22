/**
 *
 */
public class SpeedSpore extends Spore {
    /**
     * @param insect
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
