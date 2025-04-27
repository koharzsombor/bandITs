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
                case "carnivorousmycelium" -> {
                    mycelium = new CarnivorousMycelium(semiFertileTecton);
                    ObjectRegistry.registerObject(name, mycelium);
                }
                case "mycelium" ->{
                    mycelium = new MyceliumImpl(semiFertileTecton);
                    ObjectRegistry.registerObject(name, mycelium);
                }
                default -> throw new IllegalArgumentException("Mycelium type not supported");
            }
        }
        else if (location instanceof FertileTectonImpl fertileTecton) {
            switch (type.toLowerCase()) {
                case "carnivorousmycelium" ->{
                    mycelium = new CarnivorousMycelium(fertileTecton);
                    ObjectRegistry.registerObject(name, mycelium);
                }
                case "mycelium" ->{
                    mycelium = new MyceliumImpl(fertileTecton);
                    ObjectRegistry.registerObject(name, mycelium);
                }
                default -> throw new IllegalArgumentException("Mycelium type not supported");
            }
        }
        else {
            throw new IllegalArgumentException("NOOOOO ;(");
        }

        return mycelium;
    }
}
