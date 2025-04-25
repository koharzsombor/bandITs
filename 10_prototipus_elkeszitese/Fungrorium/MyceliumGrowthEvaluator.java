public class MyceliumGrowthEvaluator implements TectonVisitor {
    /**
     * Ezt az objektumot létrehozó gombafonál.
     */
    private final Mycelium creator;

    /**
     * Visszaadja a létrehozó gombafonalat.
     * @return A létrehozó gombafonál.
     */
    public Mycelium getCreator() {
        return creator;
    }

    /**
     * A létrehozáshoz szükséges megadni, hogy melyik gombafonál hozta létre.
     * @param mushroom Az objektumot létrehozó gombafonál.
     */
    public MyceliumGrowthEvaluator(Mycelium mushroom) {
        creator = mushroom;
    }

    /**
     * Megkér egy "Fertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(FertileTecton tecton) {
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "Arid" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(AridTecton tecton) {
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "Sustaining" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(SustainingTecton tecton) {
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "MultiLayered" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(MultiLayeredTecton tecton) {
        tecton.accept(this, getCreator());
    }

    /**
     * Megkér egy "SemiFertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(SemiFertileTecton tecton) {
        tecton.accept(this, getCreator());
    }
}
