import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 *
 */
public class SwingPlayer extends JPanel {
    /**
     * @param player
     */
    public SwingPlayer(Player player) {
        setBorder(new LineBorder(Color.black));
        JLabel playerLabel = new JLabel(player.toString());
        add(playerLabel);
    }
}
