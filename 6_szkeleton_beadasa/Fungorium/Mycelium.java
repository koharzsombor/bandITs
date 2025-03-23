/**
 *
 */
public class Mycelium extends Mushroom {
    public Mycelium() {

    }

    /**
     * @param location
     */
    public Mycelium(FertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(myceliumGrowthEvaluator, "MGE: MyceliumGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(myceliumGrowthEvaluator));
        }

        myceliumGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(myceliumGrowthEvaluator);
    }

    public Mycelium(SemiFertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MyceliumGrowthEvaluator myceliumGrowthEvaluator = new MyceliumGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(myceliumGrowthEvaluator, "MGE: MyceliumGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(myceliumGrowthEvaluator));
        }

        myceliumGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(myceliumGrowthEvaluator);
    }

    /**
     *
     */
    private boolean growing = false;

    /**
     *
     */
    private Tecton location;

    /**
     *
     */
    private int growTimer = 0;

    /**
     *
     */
    @Override
    public void delete() {
        Main.mockDeletion(this);
    }

    /**
     * @param sporeCount
     */
    @Override
    public void grow(int sporeCount) {
        growing = true;
        growTimer = sporeCount > 0 ? 1 : 2;
    }

    /**
     *
     */
    @Override
    public void onTurnBegin() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return
     */
    public boolean isGrowing() {
        return growing;
    }

    /**
     * @param growing
     */
    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    /**
     *
     */
    public void cut() {
        delete();
        location.checkNeighbourMyceliaSustain();
    }

    /**
     * @return
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }
}
