/**
 * Egy segédosztály, ami a tekton típusok és a gombarészek közti kommunikációban segít.
 * A szükséges kommunikáció befejezésével az osztály megszűnik.
 */
public abstract class TectonVisitor {
    /**
     * "Fertile" típusú tektonnal való kommunikáció.
     * @param tecton A tekton amivel kommunikálni szeretnénk.
     */
    public abstract void visit(FertileTecton tecton);

    /**
     * "MultiLayered" típusú tektonnal való kommunikáció.
     * @param tecton A tekton amivel kommunikálni szeretnénk.
     */
    public abstract void visit(MultiLayeredTecton tecton);

    /**
     * "Arid" típusú tektonnal való kommunikáció.
     * @param tecton A tekton amivel kommunikálni szeretnénk.
     */
    public abstract void visit(AridTecton tecton);

    /**
     * "SemiFertile" típusú tektonnal való kommunikáció.
     * @param tecton A tekton amivel kommunikálni szeretnénk.
     */
    public abstract void visit(SemiFertileTecton tecton);

    /**
     * A kommunikációnak a gombarész tagjának getterje.
     * Ez minden implementációban más lesz, ezért absztrakt.
     * @return A gombarész, aki a kommunikációt végzi.
     */
    public abstract Mushroom getCreator();
}
