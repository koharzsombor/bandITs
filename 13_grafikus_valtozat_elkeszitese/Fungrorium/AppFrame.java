import javax.swing.*;
import java.awt.*;

/**
 * Az ablak, amiben a játék megjelenik.
 */
public class AppFrame extends JFrame {
    /**
     *
     */
    private CardLayout layout;

    /**
     * Az ablakot megnyitódik.
     * @param name
     * @param layout
     */
    public AppFrame(String name, CardLayout layout) {
        super(name);
        this.layout = layout;
        getContentPane().setLayout(this.layout);
    }

    /**
     * @param viewName
     */
    public void switchToView(String viewName) {
        layout.show(getContentPane(), viewName);
    }
}
