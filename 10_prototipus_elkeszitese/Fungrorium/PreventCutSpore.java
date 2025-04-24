public class PreventCutSpore implements Spore{

    @Override
    public void eatSpore(Insect insect){
        insect.setRemainingMoves(0);
        insect.preventCut();
    }
}
