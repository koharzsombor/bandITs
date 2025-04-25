import java.util.List;

public class TestBJ {
    //Teszt1: Rovar létrehozása és letevése
    private static final String test1_Path = "Fungrorium/TestInputs/BJTests/test1.txt";
    private static final String test1_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test1_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test1_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt2: Rovar mozgatása
    private static final String test2_Path = "Fungrorium/TestInputs/BJTests/test2.txt";
    private static final String test2_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n\n";
    private static final String test2_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm2\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test2_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test2_m2 = "m2: Mycelium\n" +
            "\tlocation = ft2\n\n";
    private static final String test2_i1 = "i1: Insect\n" +
            "\tlocation = ft2\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 1\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt3: Rovar sikertelen mozgatása nem-szomszédos tektonra
    private static final String test3_Path = "Fungrorium/TestInputs/BJTests/test3.txt";
    private static final String test3_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n\n";
    private static final String test3_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm2\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test3_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test3_m2 = "m2: Mycelium\n" +
            "\tlocation = ft2\n\n";
    private static final String test3_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt4: Rovar sikertelen mozgatása olyan tektonra, amelyen nincs gombafonál
    private static final String test4_Path = "Fungrorium/TestInputs/BJTests/test4.txt";
    private static final String test4_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test4_ft2 = "ft2: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft1\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t}\n\n";
    private static final String test4_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test4i_1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt5: Rovar általi spóraevés következtében kettészakadás
    private static final String test5_Path = "Fungrorium/TestInputs/BJTests/test5.txt";
    private static final String test5_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t\ti1-1\n" +
            "\t}\n\n";
    private static final String test5_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test5_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";
    private static final String test5_i1_1 = "i1-1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt6: Rovar általi spóraevés következtében Slow állapotba kerülés
    private static final String test6_Path = "Fungrorium/TestInputs/BJTests/test6.txt";
    private static final String test6_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test6_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test6_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 1\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = SLOW\n\n";

    //Teszt7: Rovar általi spóraevés következtében Fast állapotba kerülés
    private static final String test7_Path = "Fungrorium/TestInputs/BJTests/test7.txt";
    private static final String test7_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test7_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test7_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 3\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = FAST\n\n";

    //Teszt8: Rovar általi spóraevés következtében PreventCut állapotba kerülés
    private static final String test8_Path = "Fungrorium/TestInputs/BJTests/test8.txt";
    private static final String test8_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test8_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test8_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = CANNOT_CUT\n\n";

    //Teszt9: Rovar általi spóraevés következtében Stunned állapotba kerülés
    private static final String test9_Path = "Fungrorium/TestInputs/BJTests/test9.txt";
    private static final String test9_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test9_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test9_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 0\n" +
            "\tremainingMoves = 0\n" +
            "\tsporesEaten = 1\n" +
            "\teffectTimer = 0\n" +
            "\tstate = STUNNED\n\n";

    //Teszt10: Rovar általi sikertelen spóraevés
    private static final String test10_Path = "Fungrorium/TestInputs/BJTests/test10.txt";
    private static final String test10_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = null\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t\tm1\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test10_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n\n";
    private static final String test10_i1 = "i1: Insect\n" +
            "\tlocation = ft1\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    //Teszt11: Rovar általi gombafonál elvágás
    private static final String test11_Path = "Fungrorium/TestInputs/BJTests/test11.txt";
    private static final String test11_ft1 = "ft1: FertileTecton\n" +
            "\tbreakTimer int = 5\n" +
            "\tneighbours List<Tecton> = {\n" +
            "\t\tft2\n" +
            "\t}\n" +
            "\tmyceliumCapacity int = 1\n" +
            "\tspores Queue<Spore> = {\n" +
            "\t}\n" +
            "\tmushroomBody MushroomBody = mb1\n" +
            "\tmycelia Queue<Mycelium> = {\n" +
            "\t}\n" +
            "\toccupants List<Insect> = {\n" +
            "\t\ti1\n" +
            "\t}\n\n";
    private static final String test11_mb1 = "mb1: MushroomBody\n" +
            "\tlocation = ft2\n" +
            "\tremainingEjects = 3\n\n";
    private static final String test11_m1 = "m1: Mycelium\n" +
            "\tlocation = ft1\n" +
            "\tdeathTimer = 2\n\n";
    private static final String test11_i1 = "i1: Insect\n" +
            "\tlocation = ft2\n" +
            "\tmaxMoves = 2\n" +
            "\tremainingMoves = 2\n" +
            "\tsporesEaten = 0\n" +
            "\teffectTimer = 0\n" +
            "\tstate = NORMAL\n\n";

    private CommandRouter router;
    private CommandReader cr = new CommandReaderImpl(router);
    private TraceablePrinter tp = new TrablePrinterImpl();
    void akarmi() {
        cr.bufferCommand("CREATE_MYCELIUM Mycelium m4");
        cr.bufferCommand("STATE m4");
        cr.bufferFile("");

        List<String> output = tp.readHistroy();
        //Assertion...
    }
}
