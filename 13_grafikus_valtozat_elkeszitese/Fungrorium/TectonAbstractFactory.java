/**
 * Factory interface a tectonok grafikus megjelenitesehez
 */
public interface TectonAbstractFactory {

    /**
     * FertileTecton tipusu tectonok letrejottekor, egy azt grafikusan megjelenito objektum letrehozasa
     * @param fertileTecton
     */

    public void onCreateTecton(FertileTectonImpl fertileTecton);
}
