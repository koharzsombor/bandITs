/**
 * A gombatest (MushroomBody) és a gombafonál (Mycelium) objektumaira vonatkozó általános interfész.
 */
public interface Mushroom {

    /**
     * A növekedési feltételek hiánya esetén kerül meghívásra az előzetesen létrehozott gombatest törlése céljából.
     */
    void delete();

    /**
     * A gombatest és a gombafonál növesztési folyamatát lezáró metódus. A paramétert a céltektontól kapja.
     * A céltekton abban az esetben hívja meg ezt a metódust (és nem a {@link #delete()}-et),
     * ha a növesztési feltételekre vonatkozó vizsgálat pozitív eredménnyel zárult.
     *
     * @param sporeCount A céltektonon lévő spórák száma.
     */
    void grow(int sporeCount);

    /**
     * Az objektum minden új körének kezdetekor kerül meghívásra a releváns játékműveletek elvégzése céljából.
     */
    void onTurnBegin();
}
