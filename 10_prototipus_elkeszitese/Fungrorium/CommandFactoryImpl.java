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
        switch (command.commandName()) {
            default -> throw new UnsupportedOperationException("Command is not implemented in factory");
        }
    }
}
