/**
 * A rovart példányosító osztály implementációja.
 */
public class InsectFactoryImpl implements InsectFactory {

    /**
     * Létrehoz egy rovart a megadott paraméterek alapján.
     *
     * @param name A rovar neve.
     * @return Az új rovar.
     */
    @Override
    public Insect create(String name, Tecton tecton) {
        Insect insect = new InsectImpl(tecton);
        ObjectRegistry.registerObject(name, insect);
        return insect;
    }
}
