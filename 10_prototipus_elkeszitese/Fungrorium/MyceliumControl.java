public interface MyceliumControl {
    /**
     * A growing attribútum setterje.
     * @param growing A gombafonál növekedés alatt lesz-e.
     */
    public void setGrowing(boolean growing);

    /**
     * A location attribútum setterje.
     * @param location A tekton, ahová a gombafonál kerül.
     */
    public void setLocation(Tecton location);
}