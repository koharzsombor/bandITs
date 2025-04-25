public class MushroomBodyGrowthEvaluator implements TectonVisitor {
    /**
     * A MushroomBody objektum, amely ezt az evaluatort letrehozta
     */
    private final MushroomBody creator;

    /**
     * Konstruktor a MushroomBodyGrowthEvaluator letrehozasahoz
     * @param mushroom - A MushroomBody, amelyhez ez az evaluator tartoztatva van
     */
    MushroomBodyGrowthEvaluator(MushroomBody mushroom) {
        creator = mushroom;
    }

    /**
     * Visszaadja a MushroomBody objektumot, amely az evaluatort letrehozta
     * @return - Az evaluatorhoz tartozo MushroomBody objektum
     */
    @Override
    public MushroomBody getCreator() {
        return creator;
    }

    /**
     * Megkér egy "Fertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     *
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(FertileTectonImpl tecton) {
        tecton.accept(this, creator);
    }

    /**
     * Megkér egy "Arid" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     *
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(AridTectonImpl tecton) {
        tecton.accept(this, creator);
    }

    /**
     * Megkér egy "Sustaining" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     *
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(SustainingTectonImpl tecton) {
        tecton.accept(this, creator);
    }

    /**
     * Megkér egy "MultiLayered" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     *
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(MultiLayeredTectonImpl tecton) {
        tecton.accept(this, creator);
    }

    /**
     * Megkér egy "SemiFertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     *
     * @param tecton A felkért tekton.
     */
    @Override
    public void visit(SemiFertileTectonImpl tecton) {
        tecton.accept(this, creator);
    }
}
