public class SpeedSpore implements Spore{

    /**
     * a rovar megette a spórát, ez meg fogja hívni a rovaron a beFast() parancsot, hogy legyen gyors
     * @param insect az a rovar amelyik megette a spórát
     */
    @Override
    public void eatSpore(Insect insect){
        insect.setRemainingMoves(0);
        insect.beFast();
    }

    /**
     * To string, a kiiráshoz
     * @return spóra neve
     */
    @Override
    public String toString(){
        return ObjectRegistry.lookupName(this);
    }
}
