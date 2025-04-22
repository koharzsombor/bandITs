public interface SporeEater {
    /**
     * A rovar megesz egy spórát,
     */
    public void eatSpore();

    /**
     * A rovar lassú állapotba kerül.
     */
    public void beSlow();

    /**
     * A rovar gyors állapotba kerül.
     */
    public void beFast();

    /**
     * A rovar olyan állapotba kerül, amitől nem tudja elvágni a gombafonalakat.
     */
    public void preventCut();

    /**
     * A rovar bénult állapotba kerül.
     */
    public void beStunned();

    /**
     * A rovar kettészakad
     */
    public void split();
}
