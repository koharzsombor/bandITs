public class PreventCutSpore implements Spore{

    /**
     * a rovar megette a spórát, ez meg fogja hívni a rovaron a preventCut() parancsot, hogy ne tudjon fonalat vágni
     * @param insect az a rovar amelyik megette a spórát
     */
    @Override
    public void eatSpore(Insect insect){
        insect.setRemainingMoves(0);
        insect.preventCut();
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
