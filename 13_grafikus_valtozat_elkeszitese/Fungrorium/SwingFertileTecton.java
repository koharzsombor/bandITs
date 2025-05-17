import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Swing-es megjelenitese egy FertileTectonnak
 */
public class SwingFertileTecton extends  JButton implements Updatable{

    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    // Ez fog megjelenni ha rakattintunk a tectonra
    private JPopupMenu tectonPopupMenu;

    private int size = 80;

    private Color fillColor = Color.GREEN;

    /**
     * Konstruktor, PopupMenu + gombok + listenerek
     * @param fertileTecton
     */
    SwingFertileTecton(FertileTectonImpl fertileTecton) {
        this.tectonView =  fertileTecton;

        setContentAreaFilled(false); // Ne hasznalja az alap button default backgroundot
        setBorderPainted(false); // Ne szinezze a peremet
        setFocusPainted(false); // Ez nem tudom mi de csunyan nez ki

        tectonPopupMenu = new JPopupMenu();
        tectonPopupMenu.add("FertileTecton: " + ObjectRegistry.lookupName(fertileTecton));

        JButton growMycelium = new JButton("Grow Mycelium on this Tecton");
        growMycelium.addActionListener(new GrowMyceliumActionListener(fertileTecton));
        tectonPopupMenu.add(growMycelium);

        JButton growMushroomBody = new JButton("Grow MushroomBody on this Tecton");
        growMushroomBody.addActionListener(new GrowMushroomBodyActionListener(fertileTecton));
        tectonPopupMenu.add(growMushroomBody);

        addMouseListener(new TectonMouseListener(this));

        update();
    }

    /**
     * Frissiti a tooltipen levo adatokat
     */
    @Override
    public void update() {
        setToolTipText("To be implemented");
    }

    public void showPopupMenu(MouseEvent e) {
        tectonPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Maga a kocka
        g2.setColor(fillColor);
        g2.fillRect(0, 0, size, size);

        // Kocka keretje
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, size, size);

        g2.dispose();
    }

}
