/**
 * A Controller réteg számára elérhető metódusok a gombatest vonatkozásában.
 * Állapotmódosító műveleteket tartalmaznak, lekérdezést nem valósítanak meg.
 */
public interface MushroomBodyControl {

    /**
     * Beállítja a gombatest megmaradt spórakilövéseinek számát.
     *
     * @param remainingEjects A gombatest megmaradt spórakilövéseinek száma.
     */
    void setRemainingEjects(int remainingEjects);

    /**
     * Hozzáad egy új spórát a gombatest spóráinak listájához.
     *
     * @param spore A gombatest spóráinak listájához hozzáadandó spóra.
     */
    void addSpore(Spore spore);

    /**
     * A gombatest kilövi az összes spóráját a megadott céltektonra, ha az elérhető.
     *
     * @param target A céltekton, amelyre a gombatest valamennyi spórája kilövésre kerül.
     */
    void ejectSpores(Tecton target);

    /**
     * Beállítja a gombatest elhelyezkedése szerinti tektont.
     *
     * @param location A gombatest elhelyezkedése szerinti tektonként beállítandó tekton.
     */
    void setLocation(Tecton location);
}
