import javax.swing.*;

public class SwingTectonButton extends JMenu {
    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    //JPopupMenu tectonPopupMenu;

    SwingTectonButton(String name, TectonView tectonView, boolean isFertile, String text) {
        super(name);
        this.tectonView = tectonView;

        //tectonPopupMenu = new JPopupMenu();

        JLabel label = new JLabel("Tecton: " + ObjectRegistry.lookupName(tectonView));
        add(label);

        JLabel subLabel = new JLabel(text);
        add(subLabel);

        if (isFertile) {
            JMenuItem growMycelium = new JMenuItem("Grow Mycelium on this Tecton");
            growMycelium.addActionListener(new GrowMyceliumToFertileActionListener((FertileTectonImpl) tectonView));
            add(growMycelium);

            JMenuItem growMushroomBody = new JMenuItem("Grow MushroomBody on this Tecton");
            growMushroomBody.addActionListener(new GrowMushroomBodyToFertileActionListener((FertileTectonImpl) tectonView));
            add(growMushroomBody);
        }
        else{
            JMenuItem growMycelium = new JMenuItem("Grow Mycelium on this Tecton");
            growMycelium.addActionListener(new GrowMyceliumToSemiFertileActionListener((SemiFertileTectonImpl) tectonView));
            add(growMycelium);

            JMenuItem growMushroomBody = new JMenuItem("Grow MushroomBody on this Tecton");
            growMushroomBody.addActionListener(new GrowMushroomBodyToSemiFertileActionListener((SemiFertileTectonImpl) tectonView));
            add(growMushroomBody);
        }

        addSeparator();
        add(new JLabel("Neighbouring tectons:"));
        for(TectonView t: tectonView.getNeighboursViews()){
            add(new JLabel(ObjectRegistry.lookupName(t)));
        }

        //addActionListener(new TectonButtonListener(this));
    }
}
