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
        return switch (command.commandName().toUpperCase()) {
            case "?" -> new HelpCommand(command, null);
            case "RUN" -> new RunCommand(command, null);
            case "ADD_PLAYER" -> new AddPlayerCommand(command, null);
            case "CREATE_TECTON" -> new CreateTectonCommand(command, null);
            case "CREATE_MYCELIUM" -> new CreateMyceliumCommand(command, actor);
            case "CREATE_MUSHROOMBODY" -> new CreateMushroomBodyCommand(command, actor);
            case "CREATE_INSECT" -> new CreateInsectCommand(command, actor);
            case "END_TURN" -> new EndTurnCommand(command, null);
            case "START_GAME" -> new BeginGameCommand(command, null);
            case "SET_ENDGAMETIMER" -> new SetEndgameTimerCommand(command, null);
            case "END_GAME" -> new EndGameCommand(command, null);
            case "GROW_MYCELIUM" -> new GrowMyceliumCommand(command, actor);
            case "GROW_MUSHROOMBODY" -> new GrowMushroomBodyCommand(command, actor);
            default -> throw new UnsupportedOperationException("Command is not implemented in factory");
        };
    }
}
