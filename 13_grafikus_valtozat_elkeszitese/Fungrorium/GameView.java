import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A játékot megjelenítő nézet.
 */
public class GameView extends JPanel {
    /**
     * Az ablak azon kártyája, amin ez a nézet helyezkedik el.
     */
    public static final String CARD_NAME = "game";

    /**
     * Létrehozza a nézetet a megfelelő függőségekkel.
     *
     * @param gameFieldView A játéktér nézete.
     * @param turnView A kör álapotnak a nézete.
     */
    public GameView(GameFieldView gameFieldView, TurnView turnView) {
        setBorder(new EmptyBorder(5, 5, 5, 5)); // 5px padding
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gameFieldView, turnView);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(1);

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
    }
}
