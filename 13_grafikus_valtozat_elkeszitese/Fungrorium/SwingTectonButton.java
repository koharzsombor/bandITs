import javax.swing.*;

public class SwingTectonButton extends JButton {
    // Hogy tudjon kommunikalni a FertileTectonImpl-vel
    private TectonView tectonView;

    JPopupMenu tectonPopupMenu;

    SwingTectonButton(String text, TectonView tectonView, boolean isFertile) {
        super(text);
        this.tectonView = tectonView;

        tectonPopupMenu = new JPopupMenu();

        JLabel label = new JLabel("Tecton: " + ObjectRegistry.lookupName(tectonView));
        tectonPopupMenu.add(label);

        if(isFertile) {
            JButton growMycelium = new JButton("Grow Mycelium on this Tecton");
            growMycelium.addActionListener(new GrowMyceliumToFertileActionListener((FertileTectonImpl) tectonView));
            tectonPopupMenu.add(growMycelium);

            JButton growMushroomBody = new JButton("Grow MushroomBody on this Tecton");
            growMushroomBody.addActionListener(new GrowMushroomBodyToFertileActionListener((FertileTectonImpl) tectonView));
            tectonPopupMenu.add(growMushroomBody);
        }
        else{
            JButton growMycelium = new JButton("Grow Mycelium on this Tecton");
            growMycelium.addActionListener(new GrowMyceliumToSemiFertileActionListener((SemiFertileTectonImpl) tectonView));
            tectonPopupMenu.add(growMycelium);

            JButton growMushroomBody = new JButton("Grow MushroomBody on this Tecton");
            growMushroomBody.addActionListener(new GrowMushroomBodyToSemiFertileActionListener((SemiFertileTectonImpl) tectonView));
            tectonPopupMenu.add(growMushroomBody);
        }

        tectonPopupMenu.addSeparator();
        tectonPopupMenu.add(new JLabel("Neighbouring tectons:"));
        for(TectonView t: tectonView.getNeighboursViews()){
            tectonPopupMenu.add(new JLabel(ObjectRegistry.lookupName(t)));
        }

        addActionListener(new TectonButtonListener(this));
    }

    public void showPopupMenu(){
        tectonPopupMenu.remove(0);
        JLabel label = new JLabel("Tecton: " + ObjectRegistry.lookupName(tectonView));
        tectonPopupMenu.add(label,0);
        tectonPopupMenu.show(this, 0, 0);
    }
}
