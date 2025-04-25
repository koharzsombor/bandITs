public interface Insect extends InsectControl, InsectView {
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

    /**
     * A rovar elfut egy véletlenszerű tektonra, ahol van gombafonál.
     */
    public void runAway();

    /**
     * Ha STUN állapotban van a rovar, meghal (azan eltünik a pályáról, de mint objektum megmarad, pontszámoláshoz)
     */
    public void die();
}
