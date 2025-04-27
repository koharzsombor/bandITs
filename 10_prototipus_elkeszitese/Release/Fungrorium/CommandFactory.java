/**
 * Végrahajtható parancsokat példányosító objektum.
 */
public interface CommandFactory {
    /**
     * A megadott paraméterek alapján létrehoz egy parancsot.
     * @param command A parancs elkészítéséhez szükséges bemenet.
     * @param actor A parancsot végrehajtó játékos.
     * @return A parancs, futtatható formában.
     */
    Command createCommand(InputCommand command, Player actor);
}
