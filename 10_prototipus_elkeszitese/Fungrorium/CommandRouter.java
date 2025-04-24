/**
 * A parancsok megfelelő kezelő felé való továbbításáért felelős objektum.
 */
public interface CommandRouter {

    /**
     * Továbbküld egy megfelelő kezelőnek egy bemeneti parancsot.
     * @param command A parancs bemenete.
     */
    void routeCommand(InputCommand command);

    /**
     * Egy parancs névhez rendel egy kezelőt.
     * @param commandName A parancs név.
     * @param handler A parancs kezelője.
     */
    void addCommand(String commandName, CommandHandler handler);
}
