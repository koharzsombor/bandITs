public interface CommandHandler {
    /**
     * Kezeli a beérkező parancsokat.
     * @param command A beérkező parancs.
     */
    default void handleCommand(Command command) {
        command.execute(this);
    }
}
