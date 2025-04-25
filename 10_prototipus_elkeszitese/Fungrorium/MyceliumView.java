public interface MyceliumView {
    /**
     * A growing attribútum getterje.
     * @return Éppen növekedés alatt van-e a gombafonál.
     */
    public boolean isGrowing();

    /**
     * A location attribútum getterje.
     * @return A tekton, ahol a gombafonál van.
     */
    public Tecton getLocation();
}