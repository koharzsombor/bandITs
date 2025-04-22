/**
 * Egy bemeneti parancs, részekre szétszedve.
 * @param commandName A parancs neve.
 * @param params A parancs paraméterei.
 */
public record InputCommand(String commandName, String[] params) { }
