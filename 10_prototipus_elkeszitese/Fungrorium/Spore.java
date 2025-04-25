public interface Spore {
    /**
     * a rovar megette a spórát, ez meg fogja hívni a rovaron a megfelelő parancsot spórától függően, hogy hajtsa végre a hatását a rovaron
     * @param insect az a rovar amelyik megette a spórát
     */
    public void eatSpore(Insect insect);

    /**
     * To string, a kiiráshoz
     * @return spóra neve
     */
    public String toString();
}
