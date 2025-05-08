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
                Entomologist entomologist = new EntomologistImpl(name);
                ObjectRegistry.registerObject(name, entomologist);
                return entomologist;
            case "mycologist":
                Mycologist mycologist = new MycologistImpl(name);
                ObjectRegistry.registerObject(name, mycologist);
                return mycologist;
            default:
                throw new UnsupportedOperationException("Player type is invalid");
        }
    }
}
