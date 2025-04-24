/**
 * A játékost példányosító osztály implementációja.
 */
public class PlayerFactoryImpl implements PlayerFactory {
    /**
     * Példányosít egy játékost a megadott paraméterek alapján.
     *
     * @param type A játékos típusa.
     * @param name A játékos neve.
     * @return Az új játékos.
     */
    @Override
    public Player create(String type, String name) {
        switch (type.toLowerCase()) {
            case "entomologist":
                return new EntomologistImpl(name);
            case "mycologist":
                return new MycologistImpl(name);
            default:
                throw new UnsupportedOperationException("Player type is invalid");
        }
    }
}
