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
            case "END_TURN":
                return new EndTurnCommand(command, null);
            case "START_GAME":
                return new BeginGameCommand(command, null);
            case "SET_ENDGAMETIMER":
                return new SetEndgameTimerCommand(command, null);
            case "END_GAME":
                return new EndGameCommand(command, null);
            case "GROW_MYCELIUM":
                return new GrowMyceliumCommand(command, actor);
            default:
                throw new UnsupportedOperationException("Command is not implemented in factory");
        }
    }
}
