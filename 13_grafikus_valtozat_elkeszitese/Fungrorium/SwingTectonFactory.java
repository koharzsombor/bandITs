/**
 * Swing tipusu, tectonokat grafikusan megjelenito objektumokat gyart
 */
public class SwingTectonFactory implements  TectonAbstractFactory{

    private static GameFieldView gameFieldView;

    private SwingTectonFactory() {}

    public static void setGameFieldView(GameFieldView view) {
        gameFieldView = view;
    }

    public static void onCreateTecton(FertileTectonImpl fertileTecton) {
        SwingFertileTecton swingFertileTecton = new SwingFertileTecton(fertileTecton);
        ViewRepository.bind(fertileTecton, swingFertileTecton);

        if (gameFieldView != null) {
            // Random helyre rakja be a field-ben
            double x = Math.random() * (GameFieldView.WIDTH - GameFieldView.TECTON_SIZE);
            double y = Math.random() * (GameFieldView.HEIGHT - GameFieldView.TECTON_SIZE);

            gameFieldView.addTecton(swingFertileTecton, x, y);
        }
    }
}
