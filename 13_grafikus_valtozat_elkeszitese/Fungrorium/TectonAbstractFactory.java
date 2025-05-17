/**
 * Factory interface a tectonok grafikus megjelenitesehez
 */
public interface TectonAbstractFactory {

    /**
     * FertileTecton tipusu tectonok letrejottekor, egy azt grafikusan megjelenito objektum letrehozasa
     * @param tecton
     */

    public void onCreateTecton(FertileTectonImpl tecton);
    public void onCreateTecton(SemiFertileTectonImpl tecton);
    public void onCreateTecton(AridTectonImpl tecton);
    public void onCreateTecton(MultiLayeredTectonImpl tecton);
    public void onCreateTecton(SustainingTectonImpl tecton);
}
