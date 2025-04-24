public class SpeedSpore implements Spore{

    @Override
    public void eatSpore(Insect insect){
        insect.setRemainingMoves(0);
        insect.beFast();
    }
}
