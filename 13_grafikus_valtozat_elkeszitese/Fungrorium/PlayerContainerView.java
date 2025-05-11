import javax.swing.*;

/**
 *
 */
public class PlayerContainerView extends JList<SwingPlayer> implements Updatable {
    /**
     *
     */
    private final PlayerContainer container;

    /**
     *
     */
    private final DefaultListModel<SwingPlayer> model;

    public PlayerContainerView(PlayerContainer container) {
        this.container = container;
        model = new DefaultListModel<>();
        setModel(model);
        setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            if (isSelected) {
                value.setBackground(list.getSelectionBackground());
                value.setForeground(list.getSelectionForeground());
            } else {
                value.setBackground(list.getBackground());
                value.setForeground(list.getForeground());
            }
            return value;
        });

        ViewRepository.bind(container, this);
    }

    /**
     * Frissíti a nézetetet, a hozzá tartozó modell alapján.
     */
    @Override
    public void update() {
        model.clear();
        for (Player player : container.getPlayers()) {
            model.addElement(new SwingPlayer(player));
        }
    }
}
