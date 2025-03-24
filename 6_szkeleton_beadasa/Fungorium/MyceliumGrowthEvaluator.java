/**
 * Egy segédosztály, ami annak az eldöntésében segít, hogy egy adott tektonra nőhet-e az obejktumot létrehozó gombafonál.
 */
public class MyceliumGrowthEvaluator extends TectonVisitor {
    /**
     * Ezt az objektumot létrehozó gombafonál.
     */
    private final Mycelium creator;

    /**
     * A létrehozáshoz szükséges megadni, hogy melyik gombafonál hozta létre.
     * @param mushroom Az objektumot létrehozó gombafonál.
     */
    MyceliumGrowthEvaluator(Mycelium mushroom) {
        creator = mushroom;
    }

    /**
     * Megkér egy "Fertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(FertileTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
      
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "MultiLayered" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(MultiLayeredTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "Arid" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(AridTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }

        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "SemiFertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(SemiFertileTecton tecton) {
        if (Main.printTrace) {
            System.out.printf("%s %n", Main.objectNames.get(this));
            System.out.printf("\t=accept(%s, %s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(getCreator()), Main.objectNames.get(tecton));
        }
        tecton.accept(this, getCreator());
    }

    /**
     * Visszaadja a létrehozó gombafonalat.
     * @return A létrehozó gombafonál.
     */
    @Override
    public Mycelium getCreator() {
        return creator;
    }
}