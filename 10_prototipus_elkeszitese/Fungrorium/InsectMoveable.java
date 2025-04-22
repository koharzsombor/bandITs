public interface InsectMoveable {
    /**
     * A rovar elmozdul a megadott céltektonra, ha van elég lépése a rovarnak.
     * @param target A cél tekton.
     */
    public void move(Tecton target);

    /**
     * A rovar elfut egy véletlenszerű tektonra, ahol van gombafonál.
     */
    public void runAway();
}
