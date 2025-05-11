import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A játékosok listájában egy játékost megjenítő objektum.
 */
public class SwingPlayer extends JPanel {
    /**
     * Létrehozza a megjelenítendő játékos alapján.
     *
     * @param player A játékos, ami alapján meg lehet jeleníteni.
     */
    public SwingPlayer(Player player) {
        setBorder(new LineBorder(Color.black));
        JLabel playerLabel = new JLabel(player.toString());
        add(playerLabel);
    }
}
