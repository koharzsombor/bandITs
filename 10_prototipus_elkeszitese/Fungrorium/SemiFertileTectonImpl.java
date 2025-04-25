import java.util.ArrayList;

public class SemiFertileTectonImpl extends TectonImpl {

    SemiFertileTectonImpl() {
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
    }

    @Override
    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        mushroomBody.delete();
    }

    public void onRoundBegin() {
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

    public boolean sustaining(){
        return false;
    }
}
