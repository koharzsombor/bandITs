/**
 * Spóra – a gombatest termeli. A spóra hatását az elfogyasztása következményeként a rovar internalizálja.
 * Egy spóra lehet PreventCutSpore, SlownessSpore, SpeedSpore vagy StunSpore,
 */
public abstract class Spore {
    /**
     * A függvény leírja, hogy mi történik egy rovarral, aki a spórát megeszi.
     * @param insect A rovar aki a spórát megette.
     */
    public abstract void eatSpore(Insect insect);
}
