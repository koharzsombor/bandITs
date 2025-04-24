import java.util.Collections;
import java.util.List;

/**
 * A visszaolvasható kiírás implementációja.
 */
public class TrablePrinterImpl implements TraceablePrinter {
    /**
     * A legutóbb kiírt szövegek listája.
     */
    private List<String> printHistory;

    /**
     * Kitörli a nyomtatónak a nyomtatási történetét.
     */
    @Override
    public void clearHistroy() {
        printHistory.clear();
    }

    /**
     * Kiolvassa a nyomtatási történetet.
     *
     * @return A legutóbb kiírt szövegek listája.
     */
    @Override
    public List<String> readHistroy() {
        return Collections.unmodifiableList(printHistory);
    }

    /**
     * Kiír egy szöveget az alapértelmezett kimenetre.
     *
     * @param output A kiírt szöveg.
     */
    @Override
    public void print(String output) {
        printHistory.add(output);
        System.out.print(output);
    }

    /**
     * Kiír egy sort az alapnézett kimenetre.
     *
     * @param output A kiírt sor.
     */
    @Override
    public void printLine(String output) {
        printHistory.add(output);
        System.out.println(output);
    }
}
