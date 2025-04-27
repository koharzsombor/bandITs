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

    private static final String HELP_TEXT = """
                    === Segítség: Parancsok listája ===
                                
                    -- Általános játékmenet --
                    STATE object
                        Egy objektum állapotának lekérdezése, a parancsokban megadott neve alapján.
                        Hasznos, nem parancs alapján megadott objketumok nevei: TURN, GAME_END
                    LIST_ALL
                        Lekérdezi az összes hivatkozható objektum nevet.
                    RUN file
                         Az adott file tartalmát kiadja mint parancs.
                    START_GAME
                        Játék indítása
                    END_GAME
                        Játék végének kezelése
                    END_TURN
                        End turn küldése
                    ADD_PLAYER player_type player_name
                        Játékos hozzáadása (név, típus: Mycologist vagy Entomologist)
                    SET_ENDGAMETIMER number
                        EndgameTimer beállítása (round-ok száma)         
                    -- Tekton parancsok --
                    CREATE_TECTON Tecton_Type Tecton_Name
                        Új Tecton létrehozása (típussal és névvel)
                        Típusok: FertileTecton, MultiLayeredTecton, SustainingTecton, AridTecton, SemiFertileTecton
                    ADD_NEIGHBOUR tecton_name tecton_name
                        Két tekton szomszédságba helyezése
                    SET_BREAKTIMER tecton number
                        Tecton BreakTimerjének beállítása
                                
                    -- Gombatest és Spóra parancsok --
                    CREATE_MUSHROOMBODY MushroomBody_Name Tecton_Name
                        Gombatest létrehozása egy tektonon
                    GROW_MUSHROOMBODY MushroomBody_Name Tecton_Name
                        Gombatest rákerül egy tektonra
                    PUT_SPORE Spore_Type Spore_Name Tecton_Name
                        Spóra rákerül egy tektonra
                        Típusok: SpeedSpore, StunSpore, PreventCutSpore, SplitSpore, SlownessSpore
                    EJECT_SPORES MushroomBody_Name Tecton_Name
                        Gombatest összes spórája rákerül egy tektonra
                    DEACTIVATE MushroomBody_Name
                        Gombatest inaktiválása
                    ADD_SPORE Spore_Type Spore_Name MushroomBody_Name
                        Spóra hozzáadása gombatesthez
                        Típusok: SpeedSpore, StunSpore, PreventCutSpore, SplitSpore, SlownessSpore
                    SET_REMAININGEJECTS MushroomBody_Name RemainingEjects_Count
                        Gombatesthez hátralévő spórakilövés beállítása
                                
                    -- Gombafonál parancsok --
                    CREATE_MYCELIUM Mycelium_Type Mycelium_Name
                        Gombafonál létrehozása
                        Típusok: Mycelium, CarnivorousMycelium
                    ADD_MYCELIUM_TO_TECTON Mycelium_Name Tecton_Name
                        Gombafonál hozzáadása tektonhoz
                    GROW_MYCELIUM Mycelium_Type Mycelium_Name Tecton_Name
                        Gombafonál ránő egy tektonra
                        Típusok: Mycelium, CarnivorousMycelium
                                
                    -- Rovar parancsok --
                    CREATE_INSECT tecton_name insect_name
                        Rovar létrehozása tektonon
                    MOVE insect_name tecton_name
                        Rovar mozgatása tektonra
                    EAT insect_name
                        Rovar megeszi az első spórát a tektonján
                    CUT insect_name
                        Rovar elvágja az első gombafonalt a tektonján""";

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
