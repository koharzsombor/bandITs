/**
 * ? parancs implementációja.
 */
public class HelpCommand extends CommandImpl {
    /**
     * A megadott bemenetből és végrehajtó játékosból csinál egy parancsot.
     *
     * @param inputCommand A bemenet amiből a parancs készül.
     * @param actor        A parancsot végrehajtó játékos.
     */
    public HelpCommand(InputCommand inputCommand, Player actor) {
        super(inputCommand, actor);
    }

    private static final String HELP_TEXT =
            "\n=== Segítség: Parancsok listája ===\n" +
                    "\n-- Általános játékmenet --\n" +
                    "START_GAME\n" +
                    "    Játék indítása\n" +
                    "END_GAME\n" +
                    "    Játék végének kezelése\n" +
                    "END_TURN\n" +
                    "    End turn küldése\n" +
                    "ADD_PLAYER player_type player_name\n" +
                    "    Játékos hozzáadása (név, típus: Gombász vagy Rovarász)\n" +
                    "SET_ENDGAMETIMER number\n" +
                    "    EndgameTimer beállítása (round-ok száma)\n" +

                    "\n-- Tekton parancsok --\n" +
                    "CREATE_TECTON Tecton_Type Tecton_Name\n" +
                    "    Új Tecton létrehozása (típussal és névvel)\n" +
                    "ADD_NEIGHBOUR tecton_name tecton_name\n" +
                    "    Két tekton szomszédságba helyezése\n" +
                    "SET_BREAKTIMER tecton number\n" +
                    "    Tecton BreakTimerjének beállítása\n" +

                    "\n-- Gombatest és Spóra parancsok --\n" +
                    "CREATE_MUSHROOMBODY MushroomBody_Name Tecton_Name\n" +
                    "    Gombatest létrehozása egy tektonon\n" +
                    "GROW_MUSHROOMBODY MushroomBody_Name Tecton_Name\n" +
                    "    Gombatest rákerül egy tektonra\n" +
                    "PUT_SPORE Spore_Type Spore_Name Tecton_Name\n" +
                    "    Spóra rákerül egy tektonra\n" +
                    "EJECT_SPORES MushroomBody_Name Tecton_Name\n" +
                    "    Gombatest összes spórája rákerül egy tektonra\n" +
                    "DEACTIVATE MushroomBody_Name\n" +
                    "    Gombatest inaktiválása\n" +
                    "ADD_SPORE Spore_Type Spore_Name MushroomBody_Name\n" +
                    "    Spóra hozzáadása gombatesthez\n" +
                    "SET_REMAININGEJECTS MushroomBody_Name RemainingEjects_Count\n" +
                    "    Gombatesthez hátralévő spórakilövés beállítása\n" +

                    "\n-- Gombafonál parancsok --\n" +
                    "CREATE_MYCELIUM Mycelium_Type Mycelium_Name\n" +
                    "    Gombafonál létrehozása\n" +
                    "ADD_MYCELIUM_TO_TECTON Mycelium_Name Tecton_Name\n" +
                    "    Gombafonál hozzáadása tektonhoz\n" +
                    "GROW_MYCELIUM Mycelium_Type Mycelium_Name Tecton_Name\n" +
                    "    Gombafonál ránő egy tektonra\n" +

                    "\n-- Rovar parancsok --\n" +
                    "CREATE_INSECT tecton_name insect_name\n" +
                    "    Rovar létrehozása tektonon\n" +
                    "MOVE insect_name tecton_name\n" +
                    "    Rovar mozgatása tektonra\n" +
                    "EAT insect_name\n" +
                    "    Rovar megeszi az első spórát a tektonján\n" +
                    "CUT insect_name\n" +
                    "    Rovar elvágja az első gombafonalt a tektonján\n";

    /**
     * A parancs végrehajtása.
     *
     * @param handler A parancsot végrehajtó kezelő.
     */
    @Override
    public void execute(CommandHandler handler) {
        try {
            System.out.println(HELP_TEXT);
        } catch (Exception e) {
            System.out.println("Hiba a help parancs futtatásakor!");
        }
    }
}
