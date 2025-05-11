import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A menu megjelenítésére szolgáló nézet
 */
public class MenuView extends JPanel {
    /**
     * Az alkalmazás keretben lévő kártya neve, amire hivatkozva át tudunk váltani erre a panelre.
     */
    public static final String CARD_NAME = "menu";

    /**
     * A játék kezdeti menüjét megjelenítő nézet.
     *
     * @param playerView A játékosok hozzáadásáért felelős nézet.
     * @param gameStartView A játék elindításáért felelős nézet.
     */
    public MenuView(PlayerView playerView, GameStartView gameStartView) {
        setBorder(new EmptyBorder(5, 5, 5, 5)); //5 px padding

        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, playerView, gameStartView);
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(3);
        add(splitPane, BorderLayout.CENTER);
    }
}
