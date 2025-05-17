/**
 * Swing tipusu, tectonokat grafikusan megjelenito objektumokat gyart
 */
public class SwingTectonFactory implements  TectonAbstractFactory{

    private  GameFieldView gameFieldView;

    public SwingTectonFactory() {
        this.gameFieldView = (GameFieldView)ObjectRegistry.getObject("GAME_FIELD");
    }

    public  void onCreateTecton(FertileTectonImpl fertileTecton) {
        SwingFertileTecton swingFertileTecton = new SwingFertileTecton(fertileTecton);
        ViewRepository.bind(fertileTecton, swingFertileTecton);

        if (gameFieldView != null) {

            gameFieldView.addTecton(fertileTecton);
            gameFieldView.BuildGraph();
        }
    }
}
