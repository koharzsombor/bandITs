/**
 * Swing tipusu, tectonokat grafikusan megjelenito objektumokat gyart
 */
public class SwingTectonFactory implements  TectonAbstractFactory{

    /**
     * GameFieldView, amire gyártódnak a SwingTectonok
     */
    private  GameFieldView gameFieldView;

    /**
     * Konstruktor
     */
    public SwingTectonFactory() {
        this.gameFieldView = (GameFieldView)ObjectRegistry.getObject("GAME_FIELD");
    }

    /**
     * Függvény ami hivódik,a mikor létrejön egy FertileTecton
     * @param tecton a tecton, ami létrejött, amihez készül a SwingTecton
     */
    public void onCreateTecton(FertileTectonImpl tecton) {
        SwingFertileTecton swingFertileTecton = new SwingFertileTecton(tecton);
        ViewRepository.bind(tecton, swingFertileTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }

    /**
     * Függvény ami hivódik,a mikor létrejön egy SemiFertileTecton
     * @param tecton a tecton, ami létrejött, amihez készül a SwingTecton
     */
    public  void onCreateTecton(SemiFertileTectonImpl tecton) {
        SwingSemiFertileTecton swingSemiFertileTecton = new SwingSemiFertileTecton(tecton);
        ViewRepository.bind(tecton, swingSemiFertileTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }

    /**
     * Függvény ami hivódik,a mikor létrejön egy AridTecton
     * @param tecton a tecton, ami létrejött, amihez készül a SwingTecton
     */
    public  void onCreateTecton(AridTectonImpl tecton) {
        SwingAridTecton swingAridTecton = new SwingAridTecton(tecton);
        ViewRepository.bind(tecton, swingAridTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }

    /**
     * Függvény ami hivódik,a mikor létrejön egy MultiLayeredTecton
     * @param tecton a tecton, ami létrejött, amihez készül a SwingTecton
     */
    public  void onCreateTecton(MultiLayeredTectonImpl tecton) {
        SwingMultiLayeredTecton swingMultilayeredTecton = new SwingMultiLayeredTecton(tecton);
        ViewRepository.bind(tecton, swingMultilayeredTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }

    /**
     * Függvény ami hivódik,a mikor létrejön egy SustainingTecton
     * @param tecton a tecton, ami létrejött, amihez készül a SwingTecton
     */
    public  void onCreateTecton(SustainingTectonImpl tecton) {
        SwingSustainingTecton swingSustainingTecton = new SwingSustainingTecton(tecton);
        ViewRepository.bind(tecton, swingSustainingTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
}
