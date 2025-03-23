import java.util.HashMap;
import java.util.Scanner;

/**
 * A program futtatásért és tesztelő - program összeköttetésért felelős osztály.
 */
public class Main {
    /**
     * Az objektumoknak a neveit tárolja, csak a modell tesztelése céljából létezik.
     */
    public static final HashMap<Object, String> objectNames = new HashMap<>();

    /**
     * Az objektumok közötti kommunikáció kiírásért felelős érték:
     * Ha igaz, akkor a kommunikációt ki kell írni a konzolra, ha hamis akkor nem szabad kiírni.
     */
    public static boolean printTrace = false;


    /**
     * Egy objektum kitörlését szimuláló funkció, mivel a java nem képes explicit objektumokat kitörölni, ezért így írjuk le.
     * @param object A "kitörlendő" objektum.
     */
    public static void mockDeletion(Object object) {
        if (printTrace) {
            System.out.printf("%s %n", objectNames.get(object));
            System.out.println("\t~finalize()");
        }
    }

    /**
     * A program futtatása ezzel a függvénnyel kezdődik.
     * Kezeli a felhasználói inputokat, és futtatja az inputnak megfelelő tesztet.
     * @param args Futtatáshoz átadott paraméterek, jelen programban ezzel nem kezdünk semmit.
     */
    public static void main(String... args) {
        System.out.println("Fungorium - Szekeleton");
        printTestOptions();

        try(Scanner selectScanner = new Scanner(System.in)) {
            while (selectScanner.hasNext()) {
                if (selectScanner.hasNextInt()) {
                    int input = selectScanner.nextInt();
                    selectScanner.nextLine();

                    switch (input) {
                        case 1 -> myceliumGrowthSuccessOnFertileTecton();
                        case 2 -> myceliumGrowthFailureDueToLackOfSpaceOnFertileTecton();
                        case 3 -> myceliumGrowthSuccessOnSemiFertileTecton();
                        case 4 -> myceliumGrowthFailureDueToLackOfSpaceOnSemiFertileTecton();
                        case 5 -> myceliumGrowthSuccessOnAridTecton();
                        case 6 -> myceliumGrowthFailureDueToLackOfSpaceOnAridTecton();
                        case 7 -> myceliumGrowthSuccessOnMultiLayeredTecton();
                        case 8 -> myceliumGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton();
                        case 9 -> mushroomBodyGrowthSuccessOnFertileTecton();
                        case 10 -> mushroomBodyGrowthFailureDueToLackOfSpaceOnFertileTecton();
                        case 11 -> mushroomBodyGrowthFailureDueToLackOfSporesOnFertileTecton();
                        case 12 -> mushroomBodyGrowthFailureDueToLackOfMyceliaOnFertileTecton();
                        case 13 -> mushroomBodyGrowthSuccessOnAridTecton();
                        case 14 -> mushroomBodyGrowthFailureDueToLackOfSpaceOnAridTecton();
                        case 15 -> mushroomBodyGrowthFailureDueToLackOfSporesOnAridTecton();
                        case 16 -> mushroomBodyGrowthFailureDueToLackOfMyceliaOnAridTecton();
                        case 17 -> mushroomBodyGrowthSuccessOnMultiLayeredTecton();
                        case 18 -> mushroomBodyGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton();
                        case 19 -> mushroomBodyGrowthFailureDueToLackOfSporesOnMultiLayeredTecton();
                        case 20 -> mushroomBodyGrowthFailureDueToLackOfMyceliaOnMultiLayeredTecton();
                        case 21 -> mushroomBodyGrowthFailureOnSemiFertileTecton();
                        case 22 -> stunSporeEaten();
                        case 23 -> preventCutSporeEaten();
                        case 24 -> speedSporeEaten();
                        case 25 -> slownessSporeEaten();
                        case 26 -> tectonBreak();
                        case 27 -> insectMoveSucces();
                        case 28 -> insectMoveNeighbourFail();
                        case 29 -> insectMoveMyceliumFail();
                        case 30 -> insectCutMycelium();
                        case 31 -> insectRunAway();
                        case 32 -> mushroomBodySuccesfullEjectSpore1Distance();
                        case 33 -> mushroomBodySuccesfullEjectSpore2Distance();
                        case 34 -> mushroomBodyFailedEjectSpore2Distance();
                        case 35 -> mushroomBodyFailedEjectSpore3Distance();
                        default -> System.out.println("A megadott szám nem értelmezhető teszt sorszámaként!");
                    }
                }
                else {
                    System.out.println("A megadott bemenet nem értelmezhető számként!");
                    selectScanner.next();
                }

                System.out.println("-----------------------------------");
                System.out.println("A folytatáshoz, nyomjon egy entert.");
                selectScanner.nextLine();

                printTestOptions();
            }
        }
    }

    /**
     * Az opciók kiírására szolgáló függvény.
     * Szerepelnie kell benne minden test case nevének és sorszámának.
     */
    private static void printTestOptions() {
        System.out.println("\nVálassza ki, melyik teszt esetet szeretné lefuttatni, a sorszáma megadásával:");
        System.out.println("1.\tElőfeltételnek megfelelő gombafonál növesztés „Fertile” típusú tektonra");
        System.out.println("2.\tGombafonál növesztés „Fertile” típusú tektonra, amin már van gombafonál");
        System.out.println("3.\tElőfeltételnek megfelelő gombafonál növesztés „SemiFertile” típusú tektonra");
        System.out.println("4.\tGombafonál növesztés „SemiFertile” típusú tektonra, amin már van gombafonál");
        System.out.println("5.\tElőfeltételnek megfelelő gombafonál növesztés „Arid” típusú tektonra");
        System.out.println("6.\tGombafonál növesztés „Arid” típusú tektonra, amin már van gombafonál");
        System.out.println("7.\tElőfeltételnek megfelelő gombafonál növesztés „MultiLayered” típusú tektonra");
        System.out.println("8.\tGombafonál növesztés „MultiLayered” típusú tektonra, amin 3 gombafonál van");
        System.out.println("9.\tElőfeltételnek megfelelő gombatest növesztés „Fertile” típusú tektonra");
        System.out.println("10.\tGombatest növesztés „Fertile” típusú tektonra, amin már van gombatest");
        System.out.println("11.\tGombatest növesztés „Fertile” típusú tektonra, amin nincs elég spóra");
        System.out.println("12.\tGombatest növesztés „Fertile” típusú tektonra, amin nincs gombafonál");
        System.out.println("13.\tElőfeltételnek megfelelő gombatest növesztés „Arid” típusú tektonra");
        System.out.println("14.\tGombatest növesztés „Arid” típusú tektonra, amin már van gombatest");
        System.out.println("15.\tGombatest növesztés „Arid” típusú tektonra, amin nincs elég spóra");
        System.out.println("16.\tGombatest növesztés „Arid” típusú tektonra, amin nincs gombafonál");
        System.out.println("17.\tElőfeltételnek megfelelő gombatest növesztés „MultiLayered” típusú tektonra");
        System.out.println("18.\tGombatest növesztés „MultiLayered” típusú tektonra, amin már van gombatest");
        System.out.println("19.\tGombatest növesztés „MultiLayered” típusú tektonra, amin nincs elég spóra");
        System.out.println("20.\tGombatest növesztés „MultiLayered” típusú tektonra, amin nincs gombafonál");
        System.out.println("21.\tGombatest növesztés „SemiFertile” típusú tektonra");
        System.out.println("22.\t„Stun” típusú Spóra elfogyasztása");
        System.out.println("23.\t„PreventCut” típusú Spóra elfogyasztása");
        System.out.println("24.\t„Speed” típusú Spóra elfogyasztása");
        System.out.println("25.\t„Slowness” típusú Spóra elfogyasztása");
        System.out.println("26.\tTecton törés");
        System.out.println("27.\tInsect mozgása előfeltételnek megfelelő tektonra");
        System.out.println("28.\tInsect mozgása szomsédsági feltételen megbukó tektonra");
        System.out.println("29.\tInsect mozgása Mycelium feltételen megbukó tektonra");
        System.out.println("30.\tRovar általi gombafonál elvágás");
        System.out.println("31.\tRovar elmenekülése");
        System.out.println("32.\tGombatest sikeres spórakilövése szomszédos tectonra");
        System.out.println("33.\tGombatest sikeres spórakilövése szomszédjával szomszédos tectonra");
        System.out.println("34.\tGombatest sikertelen spórakilövése szomszédjávol szomszédos tectonra");
        System.out.println("35.\tGombatest sikertelen spórakilövése túl messze levő tectonra");
    }

    /**
     * 1. test case:
     * Előfeltételnek megfelelő gombafonál növesztés „Fertile” típusú tektonra.
     */
    private static void myceliumGrowthSuccessOnFertileTecton() {
        //Init
        printTrace = false;

        FertileTecton B = new FertileTecton();
        objectNames.put(B, "B: FertileTecton");

        FertileTecton A = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody(A, "MB: MushroomBody");
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);
        //MB.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> m: Mycelium%n", objectNames.get(B));
        Mycelium m = new Mycelium(B, "m: Mycelium");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 2. test case:
     * Gombafonál növesztés „Fertile” típusú tektonra, amin már van gombafonál.
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnFertileTecton() {
        //Init
        printTrace = false;
        FertileTecton B = new FertileTecton();
        objectNames.put(B, "B: FertileTecton");

        FertileTecton A = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);

        MB.setLocation(A);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");

        B.addMycelium(M);
        M.setLocation(B);
        //TestCase
        printTrace = true;
        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> M2: Mycelium%n", objectNames.get(B));
        Mycelium m2 = new Mycelium(B, "M2: Mycelium");

        objectNames.clear();
    }

    /**
     * 3. test case:
     * Előfeltételnek megfelelő gombafonál növesztés „SemiFertile” típusú tektonra.
     */
    private static void myceliumGrowthSuccessOnSemiFertileTecton() {
        //Init
        printTrace = false;

        SemiFertileTecton B = new SemiFertileTecton();
        objectNames.put(B, "B: SemiFertileTecton");

        SemiFertileTecton A = new SemiFertileTecton();
        objectNames.put(A, "A: SemiFertileTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);
        MB.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> m: Mycelium%n", objectNames.get(B));
        Mycelium m = new Mycelium(B, "m: Mycelium");

        objectNames.clear();
    }

    /**
     * 4. test case:
     * Gombafonál növesztés „SemiFertile” típusú tektonra, amin már van gombafonál.
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnSemiFertileTecton() {
        //Init
        printTrace = false;
        SemiFertileTecton B = new SemiFertileTecton();
        objectNames.put(B, "B: SemiFertileTecton");

        SemiFertileTecton A = new SemiFertileTecton();
        objectNames.put(A, "A: SemiFertileTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);

        MB.setLocation(A);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");

        B.addMycelium(M);
        M.setLocation(B);
        //TestCase
        printTrace = true;
        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> M2: Mycelium%n", objectNames.get(B));
        Mycelium m2 = new Mycelium(B, "M2: Mycelium");

        objectNames.clear();
    }

    /**
     * 5. test case
     * Előfeltételnek megfelelő gombafonál növesztés „Arid” típusú tektonra.
     */
    private static void myceliumGrowthSuccessOnAridTecton() {
        //Init
        printTrace = false;

        AridTecton B = new AridTecton();
        objectNames.put(B, "B: AridTecton");

        AridTecton A = new AridTecton();
        objectNames.put(A, "A: AridTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);
        MB.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> m: Mycelium%n", objectNames.get(B));
        Mycelium m = new Mycelium(B, "m: Mycelium");

        objectNames.clear();
    }

    /**
     * 6. test case
     * Gombafonál növesztés „Arid” típusú tektonra, amin már van gombafonál.
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnAridTecton() {
        //Init
        printTrace = false;
        AridTecton B = new AridTecton();
        objectNames.put(B, "B: AridTecton");

        AridTecton A = new AridTecton();
        objectNames.put(A, "A: AridTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);

        MB.setLocation(A);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");

        B.addMycelium(M);
        M.setLocation(B);
        //TestCase
        printTrace = true;
        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> M2: Mycelium%n", objectNames.get(B));
        Mycelium m2 = new Mycelium(B, "M2: Mycelium");

        objectNames.clear();
    }

    /**
     * 7. test case
     * Előfeltételnek megfelelő gombafonál növesztés „MultiLayered” típusú tektonra.
     */
    private static void myceliumGrowthSuccessOnMultiLayeredTecton() {
        //Init
        printTrace = false;

        MultiLayeredTecton B = new MultiLayeredTecton();
        objectNames.put(B, "B: MultiLayeredTecton");

        MultiLayeredTecton A = new MultiLayeredTecton();
        objectNames.put(A, "A: MultiLayeredTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);
        MB.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> m: Mycelium%n", objectNames.get(B));
        Mycelium m = new Mycelium(B, "m: Mycelium");

        objectNames.clear();
    }

    /**
     * 8. test case:
     * Gombafonál növesztés „MultiLayered” típusú tektonra, amin 3 gombafonál van.
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton() {
        //Init
        printTrace = true;

        MultiLayeredTecton B = new MultiLayeredTecton();
        objectNames.put(B, "B: MultiLayeredTecton");

        FertileTecton A = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");

        B.addNeighbour(A);
        A.addNeighbour(B);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");

        A.setMushroomBody(MB);
        MB.setLocation(A);

        Mycelium M1 = new Mycelium();
        objectNames.put(M1, "M1: Mycelium");
        M1.setLocation(B);
        B.addMycelium(M1);

        Mycelium M2 = new Mycelium();
        objectNames.put(M2, "M2: Mycelium");
        M1.setLocation(B);
        B.addMycelium(M2);

        Mycelium M3 = new Mycelium();
        objectNames.put(M3, "M3: Mycelium");
        M1.setLocation(B);
        B.addMycelium(M3);

        A.setMushroomBody(MB);

        //Test case
        printTrace = true;
        Mycelium M4 = new Mycelium(B, "M4: Mycelium");
    }

    /**
     * 9. test case:
     * Előfeltételnek megfelelő gombatest növesztés „Fertile” típusú tektonra.
     */
    private static void mushroomBodyGrowthSuccessOnFertileTecton() {
        //Init
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);

        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 10. test case:
     * Gombatest növesztés „Fertile” típusú tektonra, amin már van gombatest.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnFertileTecton() {
        //Init
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        MushroomBody first = new MushroomBody(T, "first: MushroomBody");

        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 11. test case:
     * Gombatest növesztés „Fertile” típusú tektonra, amin nincs elég spóra.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnFertileTecton() {
        //Init
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        //SpeedSpore s3 = new SpeedSpore();
        //objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        //T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 12. test case:
     * Gombatest növesztés „Fertile” típusú tektonra, amin nincs gombafonál.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnFertileTecton() {
        //Init
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        //Mycelium M = new Mycelium(T, "m: Mycelium");
        //objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        //M.setLocation(T);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 13. test case:
     * Előfeltételnek megfelelő gombatest növesztés „Arid” típusú tektonra.
     */
    private static void mushroomBodyGrowthSuccessOnAridTecton() {
        //Init
        AridTecton T = new AridTecton();
        objectNames.put(T, "T: AridTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 14. test case:
     * Gombatest növesztés „Arid” típusú tektonra, amin már van gombatest.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnAridTecton() {
        //Init
        AridTecton T = new AridTecton();
        objectNames.put(T, "T: AridTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        MushroomBody first = new MushroomBody(T, "First: MuhsroomBody");

        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 15. test case:
     * Gombatest növesztés „Arid” típusú tektonra, amin nincs elég spóra.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnAridTecton() {
        //Init
        AridTecton T = new AridTecton();
        objectNames.put(T, "T: AridTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        //SpeedSpore s3 = new SpeedSpore();
        //objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");


        M.setLocation(T);
        T.addMycelium(M);

        //T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 16. test case:
     * Gombatest növesztés „Arid” típusú tektonra, amin nincs gombafonál.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnAridTecton() {
        //Init
        AridTecton T = new AridTecton();
        objectNames.put(T, "T: AridTecton");

        //Mycelium M = new Mycelium(T, "m: Mycelium");
        //objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        //M.setLocation(T);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 17. test case:
     * Előfeltételnek megfelelő gombatest növesztés „MultiLayered” típusú tektonra.
     */
    private static void mushroomBodyGrowthSuccessOnMultiLayeredTecton() {
        //Init
        MultiLayeredTecton T = new MultiLayeredTecton();
        objectNames.put(T, "T: MultiLayeredTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 18. test case:
     * Gombatest növesztés „MultiLayered” típusú tektonra, amin már van gombatest.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton() {
        //Init
        MultiLayeredTecton T = new MultiLayeredTecton();
        objectNames.put(T, "T: MultiLayeredTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        MushroomBody first = new MushroomBody(T, "first: MushroomBody");

        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 19. test case:
     * Gombatest növesztés „MultiLayered” típusú tektonra, amin nincs elég spóra.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnMultiLayeredTecton() {
        //Init
        MultiLayeredTecton T = new MultiLayeredTecton();
        objectNames.put(T, "T: MultiLayeredTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        //SpeedSpore s3 = new SpeedSpore();
        //objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        //T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 20. test case:
     * Gombatest növesztés „MultiLayered” típusú tektonra, amin nincs gombafonál.
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnMultiLayeredTecton() {
        //Init
        MultiLayeredTecton T = new MultiLayeredTecton();
        objectNames.put(T, "T: MultiLayeredTecton");

        //Mycelium M = new Mycelium(T, "m: Mycelium");
        //objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        //M.setLocation(T);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 21. test case:
     * Gombatest növesztés „SemiFertile” típusú tektonra.
     */
    private static void mushroomBodyGrowthFailureOnSemiFertileTecton() {
        //Init
        SemiFertileTecton T = new SemiFertileTecton();
        objectNames.put(T, "T: SemiFertileTecton");

        Mycelium M = new Mycelium(T, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        SpeedSpore s3 = new SpeedSpore();
        objectNames.put(s3, "s3: SpeedSpore");
        SpeedSpore s2 = new SpeedSpore();
        objectNames.put(s2, "s2: SpeedSpore");
        SpeedSpore s1 = new SpeedSpore();
        objectNames.put(s1, "s1: SpeedSpore");

        M.setLocation(T);
        T.addMycelium(M);

        T.addSpore(s3);
        T.addSpore(s2);
        T.addSpore(s1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=Create(%s)=> mb: MushroomBody%n", objectNames.get(T));
        MushroomBody mb = new MushroomBody(T, "mb: MushroomBody");

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 22. test case:
     * „Stun” típusú Spóra elfogyasztása.
     */
    private static void stunSporeEaten() {
        //Init
        printTrace = false;
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");

        StunSpore spore = new StunSpore();
        objectNames.put(spore, "spore: StunSpore");

        T.addOccupant(I);
        I.setLocation(T);

        T.addSpore(spore);

        //Test case
        printTrace = true;
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 23. test case:
     * „PreventCut” típusú Spóra elfogyasztása.
     */
    private static void preventCutSporeEaten() {
        //Init
        printTrace = false;
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");

        PreventCutSpore spore = new PreventCutSpore();
        objectNames.put(spore, "spore: PreventCutSpore");

        T.addOccupant(I);
        I.setLocation(T);

        T.addSpore(spore);

        //Test case
        printTrace = true;
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 24. test case:
     * „Speed” típusú Spóra elfogyasztása.
     */
    private static void speedSporeEaten() {
        //Init
        printTrace = false;
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");

        SpeedSpore spore = new SpeedSpore();
        objectNames.put(spore, "spore: SpeedSpore");

        T.addOccupant(I);
        I.setLocation(T);

        T.addSpore(spore);

        //Test case
        printTrace = true;
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 25. test case:
     * „Slowness” típusú Spóra elfogyasztása.
     */
    private static void slownessSporeEaten() {
        //Init
        printTrace = false;
        FertileTecton T = new FertileTecton();
        objectNames.put(T, "T: FertileTecton");

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");

        SlownessSpore spore = new SlownessSpore();
        objectNames.put(spore, "spore: SlownessSpore");

        T.addOccupant(I);
        I.setLocation(T);

        T.addSpore(spore);

        //Test case
        printTrace = true;
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 26. test case
     */
    private static void tectonBreak(){
        throw new UnsupportedOperationException("Not implemented yet");
    }


    /**
     * 27. test case
     */
    private static void insectMoveSucces(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");
        B.addMycelium(M);
        M.setLocation(B);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);
        I.setRemainingMoves(1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
    }

    /**
     * 28. test case
     */
    private static void insectMoveNeighbourFail(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");
        B.addMycelium(M);
        M.setLocation(B);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);
        I.setRemainingMoves(1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
    }

    /**
     * 29. test case
     */
    private static void insectMoveMyceliumFail(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);
        I.setRemainingMoves(1);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
    }

    /**
     * 30. test case
     */
    private static void insectCutMycelium() {
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");
        B.addMycelium(M);
        M.setLocation(B);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);
        I.setRemainingMoves(1);

        Mycelium M2 = new Mycelium();
        objectNames.put(M2, "M2: Mycelium");
        A.addMycelium(M2);
        M2.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=cutMycelium()=> %s %n", objectNames.get(A));
        A.cutMycelium();

        objectNames.clear();
    }

    /**
     * 31. test case
     */
    private static void insectRunAway(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");

        FertileTecton B = new FertileTecton();
        objectNames.put(B, "B: FertileTecton");

        FertileTecton C = new FertileTecton();
        objectNames.put(C, "C: FertileTecton");

        Mycelium MB = new Mycelium();
        objectNames.put(MB, "MB: Mycelium");

        Mycelium MC = new Mycelium();
        objectNames.put(MB, "MC: Mycelium");

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");

        B.addMycelium(MB);
        MB.setLocation(B);

        C.addMycelium(MC);
        MC.setLocation(C);

        A.addOccupant(I);
        I.setLocation(A);

        B.addNeighbour(A);
        A.addNeighbour(B);
        A.addNeighbour(C);
        C.addNeighbour(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=runAway()=> %s %n", objectNames.get(I));
        I.runAway();

        objectNames.clear();
    }

    /**
     * 32. test case
     */
    private static void mushroomBodySuccesfullEjectSpore1Distance(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        FertileTecton C = new FertileTecton();
        FertileTecton D = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        objectNames.put(C, "C: FertileTecton");
        objectNames.put(D, "D: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);
        B.addNeighbour(C);
        C.addNeighbour(B);
        C.addNeighbour(D);
        D.addNeighbour(C);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");
        A.setMushroomBody(MB);
        MB.setLocation(A);
        MB.setRemainingEjects(3);

        SpeedSpore S = new SpeedSpore();
        MB.addSpore(S);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=ejectSpores(%s)=> %s %n", objectNames.get(B), objectNames.get(MB));
        MB.ejectSpores(B);

        objectNames.clear();
    }

    /**
     * 33. test case
     */
    private static void mushroomBodySuccesfullEjectSpore2Distance(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        FertileTecton C = new FertileTecton();
        FertileTecton D = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        objectNames.put(C, "C: FertileTecton");
        objectNames.put(D, "D: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);
        B.addNeighbour(C);
        C.addNeighbour(B);
        C.addNeighbour(D);
        D.addNeighbour(C);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");
        A.setMushroomBody(MB);
        MB.setLocation(A);
        MB.setRemainingEjects(1);

        SpeedSpore S = new SpeedSpore();
        MB.addSpore(S);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=ejectSpores(%s)=> %s %n", objectNames.get(C), objectNames.get(MB));
        MB.ejectSpores(C);

        objectNames.clear();
    }

    /**
     * 34. test case
     */
    private static void mushroomBodyFailedEjectSpore2Distance(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        FertileTecton C = new FertileTecton();
        FertileTecton D = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        objectNames.put(C, "C: FertileTecton");
        objectNames.put(D, "D: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);
        B.addNeighbour(C);
        C.addNeighbour(B);
        C.addNeighbour(D);
        D.addNeighbour(C);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");
        A.setMushroomBody(MB);
        MB.setLocation(A);
        MB.setRemainingEjects(3);

        SpeedSpore S = new SpeedSpore();
        MB.addSpore(S);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=ejectSpores(%s)=> %s %n", objectNames.get(C), objectNames.get(MB));
        MB.ejectSpores(C);

        objectNames.clear();
    }

    /**
     * 35. test case
     */
    private static void mushroomBodyFailedEjectSpore3Distance(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        FertileTecton C = new FertileTecton();
        FertileTecton D = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        objectNames.put(C, "C: FertileTecton");
        objectNames.put(D, "D: FertileTecton");
        A.addNeighbour(B);
        B.addNeighbour(A);
        B.addNeighbour(C);
        C.addNeighbour(B);
        C.addNeighbour(D);
        D.addNeighbour(C);

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");
        A.setMushroomBody(MB);
        MB.setLocation(A);
        MB.setRemainingEjects(3);

        SpeedSpore S = new SpeedSpore();
        MB.addSpore(S);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=ejectSpores(%s)=> %s %n", objectNames.get(D), objectNames.get(MB));
        MB.ejectSpores(D);

        objectNames.clear();
    }
}
