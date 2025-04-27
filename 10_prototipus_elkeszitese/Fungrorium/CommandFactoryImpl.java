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
            case "STATE" -> new StateCommand(command, null);
            case "ADD_PLAYER" -> new AddPlayerCommand(command, null);
            case "CREATE_TECTON" -> new CreateTectonCommand(command, null);
            case "CREATE_MYCELIUM" -> new CreateMyceliumCommand(command, actor);
            case "CREATE_MUSHROOMBODY" -> new CreateMushroomBodyCommand(command, actor);
            case "CREATE_INSECT" -> new CreateInsectCommand(command, actor);
            case "ENDTURN" -> new EndTurnCommand(command, null);
            case "START_GAME" -> new BeginGameCommand(command, null);
            case "SET_ENDGAMETIMER" -> new SetEndgameTimerCommand(command, null);
            case "END_GAME" -> new EndGameCommand(command, null);
            case "GROW_MYCELIUM" -> new GrowMyceliumCommand(command, actor);
            case "GROW_MUSHROOMBODY" -> new GrowMushroomBodyCommand(command, actor);
            case "SET_BREAKTIMER" -> new SetBreakTimerCommand(command, null);
            case "ADD_NEIGHBOUR" -> new AddNeighbourCommand(command, null);
            case "ADD_MYCELIUM_TO_TECTON" -> new AddMyceliumCommand(command, null);
            case "PUT_SPORE" -> new PutSporeCommand(command, null);
            case "EJECT_SPORES" -> new EjectSporesCommand(command, actor);
            case "DEACTIVATE" -> new DeactivateCommand(command, null);
            case "SET_REMAININGEJECTS" -> new SetRemainingEjectsCommand(command, null);
            case "ADD_SPORE" -> new AddSporeCommand(command, null);
            case "CUT" -> new CutCommand(command, actor);
            case "EAT" -> new EatCommand(command, actor);
            case "MOVE" -> new MoveCommand(command, actor);
            case "LIST_ALL" -> new ListAllCommand(command, null);
            default -> null;
        };
    }
}
