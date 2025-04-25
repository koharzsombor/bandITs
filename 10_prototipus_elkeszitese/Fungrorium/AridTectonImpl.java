import java.util.ArrayList;

public class AridTectonImpl extends FertileTectonImpl {

    private int abosrbCountdown;

    AridTectonImpl() {
        setMyceliaCapacity(1);
    }

    @Override
    public void accept(MyceliumGrowthEvaluator myceliumGrowthEvaluator, Mycelium mycelium) {
        if (getMycelia().size() >= getMyceliaCapacity()) {
            mycelium.delete();
            return;
        }

        getMycelia().offer(mycelium);

        int sporeCount = getSpores().size();
        mycelium.grow(sporeCount);
        abosrbCountdown = 5;
    }

    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (getSpores().size() < 3 || getMushroomBody() != null || getMycelia().isEmpty()) {
            mushroomBody.delete();
            return;
        }

        setMushroomBody(mushroomBody);
        mushroomBody.grow(getSpores().size());
    }

    public void onRoundBegin() {
        if (abosrbCountdown > 0) {
            abosrbCountdown--;
            if (abosrbCountdown <= 0) {
                Mycelium mycelium = this.getMycelia().poll();
                if (mycelium != null) {
                    mycelium.delete();
                }
            }
        }

        setBreakTimer(getBreakTimer() - 1);

        if (getBreakTimer() <= 0) {
            while(!getMycelia().isEmpty()) {
                Mycelium mycelium = getMycelia().poll();
                assert mycelium != null;
                mycelium.cutImmediate();
            }

            ArrayList<Insect> temp =new ArrayList<Insect>(getOccupants());
            for (Insect insect : temp) {
                insect.runAway();
            }

            FertileTectonImpl newFertileTecton = new FertileTectonImpl();
            newFertileTecton.addNeighbour(this);
            this.addNeighbour(newFertileTecton);
        }
    }

    public boolean sustaining() {
        return getMushroomBody() != null;
    }
}
