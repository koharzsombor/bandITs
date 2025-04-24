public class SplitSpore implements Spore{

    @Override
    public void eatSpore(Insect insect){
        insect.setRemainingMoves(0);
        insect.split();
    }
}
