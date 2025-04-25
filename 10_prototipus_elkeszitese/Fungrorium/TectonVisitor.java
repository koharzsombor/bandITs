public interface TectonVisitor {

    /**
     * Visszaadja a létrehozó gombafonalat.
     * @return A létrehozó gombafonál.
     */
    Mushroom getCreator();

    /**
     * Megkér egy "Fertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */

    void visit(FertileTectonImpl tecton);

    /**
     * Megkér egy "Arid" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    void visit(AridTectonImpl tecton);

    /**
     * Megkér egy "Sustaining" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    void visit(SustainingTectonImpl tecton);

    /**
     * Megkér egy "MultiLayered" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    void visit(MultiLayeredTectonImpl tecton);

    /**
     * Megkér egy "SemiFertile" típusú tektont, hogy "fogadja be" az objektumot létrehozó gombafonalat.
     * @param tecton A felkért tekton.
     */
    void visit(SemiFertileTectonImpl tecton);
}
