public class CommandFactoryImpl implements CommandFactory {
    /**
     * A megadott paraméterek alapján létrehoz egy parancsot.
     *
     * @param command A parancs elkészítéséhez szükséges bemenet.
     * @param actor   A parancsot végrehajtó játékos.
     * @return A parancs, futtatható formában.
     */
    @Override
    public Command createCommand(InputCommand command, Player actor) {
        switch (command.commandName().toUpperCase()) {
            case "ADD_PLAYER":
                return new AddPlayerCommand(command, null);
            case "CREATE_TECTON":
                return new CreateTectonCommand(command, null);
            case "CREATE_MYCELIUM":
                return new CreateMyceliumCommand(command, actor);
            case "CREATE_MUSHROOMBODY":
                return new CreateMushroomBodyCommand(command, actor);
            case "CREATE_INSECT":
                return new CreateInsectCommand(command, actor);
            default:
                throw new UnsupportedOperationException("Command is not implemented in factory");
        }
    }
}
