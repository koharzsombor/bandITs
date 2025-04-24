import java.util.ArrayList;

public class FertileTectonImpl implements FertileTecton {

    FertileTectonImpl() {
        setMyceliaCapacity(1);
        //setBreakTimer();
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

    public void accept(MushroomBodyGrowthEvaluator mushroomBodyGrowthEvaluator, MushroomBody mushroomBody) {
        if (getSpores().size() < 3 || getMushroomBody() != null || getMycelia().isEmpty()) {
            mushroomBody.delete();
            return;
        }

        setMushroomBody(mushroomBody);
        mushroomBody.grow(getSpores().size());
    }

    public void onRoundBegin() {
        setBreakTimer(getBreakTimer() - 1);

        if (getBreakTimer() <= 0) {
            while(!getMycelia().isEmpty()) {
                Mycelium mycelium = getMycelia().poll();
                assert mycelium != null;
                mycelium.cut();
            }

            ArrayList<Insect> temp =new ArrayList<Insect>(getOccupants());
            for (Insect insect : temp) {
                insect.runAway();
            }
        }
    }

    public boolean sustaining(){
        return getMushroomBody() != null;
    }
}
