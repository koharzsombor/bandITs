import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A menu megjelenítésére szolgáló nézet
 */
public class MenuView extends JPanel {
    /**
     *
     */
    private AppFrame frame;

    /**
     *
     */
    private PlayerView playerView;

    public MenuView(AppFrame frame, PlayerView playerView) {
        this.frame = frame;
        this.playerView = playerView;

        setBorder(new EmptyBorder(5, 5, 5, 5)); //5 px padding

        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        add(playerView, BorderLayout.WEST);
    }
}
