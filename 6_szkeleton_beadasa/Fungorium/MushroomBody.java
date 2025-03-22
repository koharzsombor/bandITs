/**
 *
 */
public class MushroomBody extends Mushroom {
    public MushroomBody() {

    }
    /**
     *
     */
    private int remainingEjects;

    /**
     *
     */
    private Tecton location;


    /**
     * @param location
     * @param name
     */
    public MushroomBody(FertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator = new MushroomBodyGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(mushroomBodyGrowthEvaluator, "MBGE: MushroomBodyGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(mushroomBodyGrowthEvaluator));
        }

        mushroomBodyGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(mushroomBodyGrowthEvaluator);

    }

    public MushroomBody(SemiFertileTecton location, String name) {
        Main.objectNames.put(this, name);

        this.location = location;

        if (Main.printTrace)
            System.out.printf("%s %n", Main.objectNames.get(this));

        MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator = new MushroomBodyGrowthEvaluator(this);

        if (Main.printTrace) {
            Main.objectNames.put(mushroomBodyGrowthEvaluator, "MBGE: MushroomBodyGrowthEvaluator");
            System.out.printf("\t=Create(%s)=> %s %n", Main.objectNames.get(this), Main.objectNames.get(mushroomBodyGrowthEvaluator));
        }

        mushroomBodyGrowthEvaluator.visit(location);

        if (Main.printTrace)
            Main.mockDeletion(mushroomBodyGrowthEvaluator);

    }



    @Override
    public void delete() {
        if (Main.printTrace) {
            System.out.printf("%n");
        }
        Main.mockDeletion(this);
    }

    /**
     * @param sporeCount
     */
    @Override
    public void grow(int sporeCount) {
        // Currently nothing
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
    public int getRemainingEjects() {
        return remainingEjects;
    }

    /**
     * @param remainingEjects
     */
    public void setRemainingEjects(int remainingEjects) {
        this.remainingEjects = remainingEjects;
    }

    /**
     * @param target
     */
    public void ejectSpores(Tecton target) {
        throw new UnsupportedOperationException("Not implemented");
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
