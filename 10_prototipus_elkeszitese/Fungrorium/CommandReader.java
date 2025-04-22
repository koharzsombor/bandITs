/**
 * A felhasználó által megadott parancsokat olvassa be, majd továbbítja az értelmezőnek.
 */
public interface CommandReader extends CommandHandler {
    /**
     * Értelmezi a következő parancsot a pufferből, ha a puffer üres, akkor a játékostól kér új
     * parancsot.
     */
    void readNextCommand();

    /**
     * Egy parancsot a pufferbe helyez.
     * @param input A pufferbe helyezendő parancs.
     */
    void bufferCommand(String input);

    /**
     * Egy fájlban lévő összes sort külön parancsként a pufferbe helyez.
     * @param path A fájl elérési útja.
     */
    void bufferFile(String path);
}
