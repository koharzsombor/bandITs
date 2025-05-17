/**
 * Swing tipusu, tectonokat grafikusan megjelenito objektumokat gyart
 */
public class SwingTectonFactory implements  TectonAbstractFactory{

    private  GameFieldView gameFieldView;

    public SwingTectonFactory() {
        this.gameFieldView = (GameFieldView)ObjectRegistry.getObject("GAME_FIELD");
    }

    public void onCreateTecton(FertileTectonImpl tecton) {
        SwingFertileTecton swingFertileTecton = new SwingFertileTecton(tecton);
        ViewRepository.bind(tecton, swingFertileTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
    public  void onCreateTecton(SemiFertileTectonImpl tecton) {
        SwingSemiFertileTecton swingSemiFertileTecton = new SwingSemiFertileTecton(tecton);
        ViewRepository.bind(tecton, swingSemiFertileTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
    public  void onCreateTecton(AridTectonImpl tecton) {
        SwingAridTecton swingAridTecton = new SwingAridTecton(tecton);
        ViewRepository.bind(tecton, swingAridTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
    public  void onCreateTecton(MultiLayeredTectonImpl tecton) {
        SwingMultiLayeredTecton swingMultilayeredTecton = new SwingMultiLayeredTecton(tecton);
        ViewRepository.bind(tecton, swingMultilayeredTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
    public  void onCreateTecton(SustainingTectonImpl tecton) {
        SwingSustainingTecton swingSustainingTecton = new SwingSustainingTecton(tecton);
        ViewRepository.bind(tecton, swingSustainingTecton);

        if (gameFieldView != null) {
            gameFieldView.addTecton(tecton);
            gameFieldView.BuildGraph();
        }
    }
}
