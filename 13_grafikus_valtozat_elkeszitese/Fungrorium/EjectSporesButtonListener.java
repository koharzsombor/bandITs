import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Eseménykezelő osztály a spórákilövéshez.
 * Ez az osztály akkor aktiválódik, amikor a játékos a MushroomBodyhoz tartozó menüben
 * a spórakilövés gombot megnyomja. Ekkor meghívja a MushroomBodyController megfelelő metódusát.
 */
public class EjectSporesButtonListener implements ActionListener {

    /**
     * A MushroomBody példány, amelyhez ez a listener tartozik.
     */
    MushroomBody mushroomBody;

    /**
     * A MushroomBodyController, amely végrehajtja a kilövési műveletet.
     */
    MushroomBodyController mushroomBodyController;

    Tecton targetTecton;

    /**
     * Konstruktor, amely eltárolja a kapcsolódó MushroomBody példányt és lekéri
     * a MushroomBodyController példányt az ObjectRegistry-ből.
     *
     * @param mb A MushroomBody, amelyre a művelet vonatkozik.
     */
    public EjectSporesButtonListener(MushroomBody mb, Tecton tecton) {
        this.mushroomBody = mb;
        this.targetTecton = tecton;
        this.mushroomBodyController = (MushroomBodyController) ObjectRegistry.getObject("MushroomBodyController");
    }

    /**
     * Akkor hívódik meg, amikor a felhasználó rákattint a spórakilövés gombra.
     * Meghívja a MushroomBodyController eject metódusát, átadva a MushroomBody-t
     * és az annak elhelyezkedése szerinti tektont.
     *
     * @param e Az esemény, amely kiváltotta a műveletet.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mushroomBodyController.eject(mushroomBody, targetTecton);
    }
}
