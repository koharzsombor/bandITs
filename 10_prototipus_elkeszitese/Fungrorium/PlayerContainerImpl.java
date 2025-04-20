import java.util.ArrayList;
import java.util.List;

/**
 * A játékos tárának implementációja.
 */
public class PlayerContainerImpl implements PlayerContainer {
    /**
     * A játékosok listája.
     */
    private final List<Player> players = new ArrayList<>();

    /**
     * A gombászok listája.
     */
    private final List<Player> mycologists = new ArrayList<>();

    /**
     * A rovarászok listája.
     */
    private final List<Player> entomologists = new ArrayList<>();

    /**
     * Az objektum ami értesíti a feliratkozóit, ha a játékosok köre körbeért.
     */
    private RoundObserver roundObserver;

    /**
     * A jelenlegi játékos indexe, a játékosok listájában.
     */
    private int currentIndex = 0;

    /**
     * Hozzáad egy játékost a tárolóhoz.
     *
     * @param player A játékos, akit hozzá szeretnénk adni.
     * @param type   A játékos típusa.
     */
    @Override
    public void addPlayer(Player player, String type) {
        players.add(player);

        if (type.toLowerCase().equals("mycologist")) {
            mycologists.add(player);
        }
        else if (type.toLowerCase().equals("entomologist")) {
            entomologists.add(player);
        }
        else {
            throw new IllegalArgumentException("Not a player type");
        }
    }

    /**
     * Kivesz egy játékost a tárolóból.
     *
     * @param player A játékost akit ki szeretnénk venni.
     */
    @Override
    public void removePlayer(Player player) {
        players.remove(player);

        if (mycologists.contains(player))
            mycologists.remove(player);
        else
            entomologists.remove(player);
    }

    /**
     * Visszaállítja a jelenlegi játékost az 1. állapotjára.
     */
    @Override
    public void resetCurrentPlayer() {
        currentIndex = 0;
    }

    /**
     * A jelenlegi játékos a következő lesz. És visszadja a következő játékost.
     *
     * @return A következő játékos.
     */
    @Override
    public Player getNextPlayer() {
        currentIndex++;
        if (currentIndex >= players.size()) {
            currentIndex = 0;
            roundObserver.notifySubscribers();
        }


        return players.get(currentIndex);
    }

    /**
     * A mostani játékost visszadja.
     *
     * @return A mostani játékos.
     */
    @Override
    public Player getCurrentPlayer() {
        if (players.isEmpty())
            return null;

        return players.get(currentIndex);
    }

    /**
     * Visszaadja az összes játékost.
     *
     * @return Az összes játékos.
     */
    @Override
    public Iterable<Player> getPlayers() {
        return players;
    }

    /**
     * Visszadja az összes gombászt.
     *
     * @return Az összes gombász.
     */
    @Override
    public Iterable<Player> getMycologists() {
        return mycologists;
    }

    /**
     * Visszadja az összes rovarászt.
     *
     * @return Az összes rovarász.
     */
    @Override
    public Iterable<Player> getEntomologists() {
        return entomologists;
    }
}
