/**
 *
 */
public class DefaultMyceliumFactory implements MyceliumFactory {
    /**
     * Létrehoz egy gombafonalat a megadott paraméterek alapján.
     *
     * @param type     A gombafonál típusa.
     * @param name     A gombafonál neve;
     * @param location A gombafonál helye.
     * @return Az új gombafonál.
     */
    @Override
    public Mycelium create(String type, String name, Tecton location) {
        Mycelium mycelium;

        if (location instanceof SemiFertileTectonImpl semiFertileTecton) {
            switch (type.toLowerCase()) {
                case "carnivorous" -> mycelium = new CarnivorousMycelium(semiFertileTecton);
                case "default" -> mycelium = new MyceliumImpl(semiFertileTecton);
                default -> throw new IllegalArgumentException("Mycelium type not supported");
            }
        }
        else if (location instanceof FertileTectonImpl fertileTecton) {
            switch (type.toLowerCase()) {
                case "carnivorous" -> mycelium = new CarnivorousMycelium(fertileTecton);
                case "default" -> mycelium = new MyceliumImpl(fertileTecton);
                default -> throw new IllegalArgumentException("Mycelium type not supported");
            }
        }
        else {
            throw new IllegalArgumentException("NOOOOO ;(");
        }

        return mycelium;
    }
}
