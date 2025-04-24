import java.util.List;

/**
 * A visszaolvasható kiírást megvalósító osztály.
 */
public interface TraceablePrinter extends CommandHandler {
    /**
     * Kitörli a nyomtatónak a nyomtatási történetét.
     */
    void clearHistroy();

    /**
     * Kiolvassa a nyomtatási történetet.
     * @return A legutóbb kiírt szövegek listája.
     */
    List<String> readHistroy();

    /**
     * Kiír egy szöveget az alapértelmezett kimenetre.
     * @param output A kiírt szöveg.
     */
    void print(String output);

    /**
     * Kiír egy sort az alapnézett kimenetre.
     * @param output A kiírt sor.
     */
    void printLine(String output);
}
