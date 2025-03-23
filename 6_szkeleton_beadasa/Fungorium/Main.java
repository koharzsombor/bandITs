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
        System.out.println("1.\tGombafonál sikeres növesztése szomszédos FertileTectonra (nem MultiLayeredTecton és nem AridTecton)");
        System.out.println("2.\tGombafonál sikertelen növesztése szomszédos FertileTectonra (nem MultiLayeredTecton és nem AridTecton), amelyen már van gombafonál");
        System.out.println("3.\tGombafonál sikeres növesztése szomszédos SemiFertileTectonra");
        System.out.println("4.\tGombafonál sikertelen növesztése szomszédos SemiFertileTectonra, amelyen már van gombafonál");
        System.out.println("5.\tGombafonál sikeres növesztése szomszédos AridTectonra");
        System.out.println("6.\tGombafonál sikertelen növesztése szomszédos AridTectonra, amelyen már van gombafonál");
        System.out.println("7.\tGombafonál sikeres növesztése szomszédos MultiLayeredTectonra");
        System.out.println("8.\tGombafonál sikertelen növesztése szomszédos MultiLayeredTectonra, amelyen már van 3 db gombafonál");
        System.out.println("9.\tGombatest sikeres növesztése FertileTectonra (nem MultiLayeredTecton és nem AridTecton)");
        System.out.println("10.\tGombatest sikertelen növesztése FertileTectonra, amin már van gombatest (nem MultiLayeredTecton és nem AridTecton)");
        System.out.println("11.\tGombatest sikertelen növesztése FertileTectonra, amin nincs elég spóra (nem MultiLayeredTecton és nem AridTecton)");
        System.out.println("12.\tGombatest sikertelen növesztése FertileTectonra, amin nincs gombafonál (nem MultiLayeredTecton és nem AridTecton)");
        System.out.println("13.\tGombatest sikeres növesztése AridTectonra");
        System.out.println("14.\tGombatest sikertelen növesztése AridTectonra, amin már van gombatest");
        System.out.println("15.\tGombatest sikertelen növesztése AridTectonra, amin nincs elég spóra");
        System.out.println("16.\tGombatest sikertelen növesztése AridTectonra, amin nincs gombafonál");
        System.out.println("17.\tGombatest sikeres növesztése MultiLayeredTectonra");
        System.out.println("18.\tGombatest sikertelen növesztése MultiLayeredTectonra, amin már van gombatest");
        System.out.println("19.\tGombatest sikertelen növesztése MultiLayeredTectonra, amin nincs elég spóra");
        System.out.println("20.\tGombatest sikertelen növesztése MultiLayeredTectonra, amin nincs gombafonál");
        System.out.println("21.\tGombatest sikertelen növesztése SemiFertileTectonra");
        System.out.println("22.\t„StunSpore elfogyasztása");
        System.out.println("23.\t„PreventCutSpore elfogyasztása");
        System.out.println("24.\t„SpeedSpore elfogyasztása");
        System.out.println("25.\t„SlownessSpore elfogyasztása");
        System.out.println("26.\tTektontörés");
        System.out.println("27.\tRovar sikeres mozgása");
        System.out.println("28.\tRovar sikertelen mozgása nem szomszédos tektonra");
        System.out.println("29.\tRovar sikertelen mozgása olyan tektonra, ahol nincs gombafonál");
        System.out.println("30.\tRovar általi gombafonál elvágás");
        System.out.println("31.\tRovar elmenekülése");
        System.out.println("32.\tGombatest sikeres spórakilövése szomszédos tektonra");
        System.out.println("33.\tGombatest sikeres spórakilövése szomszédos tekton szomszédjára");
        System.out.println("34.\tGombatest sikertelen spórakilövése szomszédos tekton szomszédjára");
        System.out.println("35.\tGombatest sikertelen spórakilövése szomszédos tekton szomszédjánál távolabb lévő tektonra");
    }

    /**
     * 1. test case:
     * Gombafonál sikeres növesztése szomszédos FertileTectonra (nem MultiLayeredTecton és nem AridTecton)
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
     * Gombafonál sikertelen növesztése szomszédos FertileTectonra (nem MultiLayeredTecton és nem AridTecton), amelyen már van gombafonál
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
     * Gombafonál sikeres növesztése szomszédos SemiFertileTectonra
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
     * Gombafonál sikertelen növesztése szomszédos SemiFertileTectonra, amelyen már van gombafonál
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
     * Gombafonál sikeres növesztése szomszédos AridTectonra
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
     *  Gombafonál sikertelen növesztése szomszédos AridTectonra, amelyen már van gombafonál
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
     * Gombafonál sikeres növesztése szomszédos MultiLayeredTectonra
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
     * Gombafonál sikertelen növesztése szomszédos MultiLayeredTectonra, amelyen már van 3 db gombafonál
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
     * Gombatest sikeres növesztése FertileTectonra (nem MultiLayeredTecton és nem AridTecton)
     */
    private static void mushroomBodyGrowthSuccessOnFertileTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése FertileTectonra, amin már van gombatest (nem MultiLayeredTecton és nem AridTecton)
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnFertileTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése FertileTectonra, amin nincs elég spóra (nem MultiLayeredTecton és nem AridTecton)
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnFertileTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése FertileTectonra, amin nincs gombafonál (nem MultiLayeredTecton és nem AridTecton)
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnFertileTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikeres növesztése AridTectonra
     */
    private static void mushroomBodyGrowthSuccessOnAridTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése AridTectonra, amin már van gombatest
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnAridTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése AridTectonra, amin nincs elég spóra
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnAridTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése AridTectonra, amin nincs gombafonál
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnAridTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikeres növesztése MultiLayeredTectonra
     */
    private static void mushroomBodyGrowthSuccessOnMultiLayeredTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése MultiLayeredTectonra, amin már van gombatest
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése MultiLayeredTectonra, amin nincs elég spóra
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnMultiLayeredTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése MultiLayeredTectonra, amin nincs gombafonál
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnMultiLayeredTecton() {
        //Init
        printTrace = false;

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
     * Gombatest sikertelen növesztése SemiFertileTectonra
     */
    private static void mushroomBodyGrowthFailureOnSemiFertileTecton() {
        //Init
        printTrace = false;

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
     * StunSpore elfogyasztása
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
     * PreventCutSpore elfogyasztása
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
     * SpeedSpore elfogyasztása
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
     * SlownessSpore elfogyasztása
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
     * 26. test case:
     * Tektontörés
     */
    private static void tectonBreak(){
        //Init
        printTrace = false;
        FertileTecton A = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");

        FertileTecton B = new FertileTecton();
        objectNames.put(B, "B: FertileTecton");

        A.addNeighbour(B);
        B.addNeighbour(A);

        Mycelium M = new Mycelium(A, "m: Mycelium");
        objectNames.put(M, "M: Mycelium");

        MushroomBody MB = new MushroomBody();
        objectNames.put(MB, "MB: MushroomBody");
        B.setMushroomBody(MB);
        MB.setLocation(B);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);

        //Test Case
        // Itt be van allitva a tores, hogy megtortenjen egy kor utan
        printTrace = true;
        System.out.println("Tester");
        A.onRoundBegin();



    }


    /**
     * 27. test case:
     * Rovar sikeres mozgása
     */
    private static void insectMoveSucces(){
        //Init
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

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 28. test case:
     * Rovar sikertelen mozgása nem szomszédos tektonra
     */
    private static void insectMoveNeighbourFail(){
        //Init
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

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 29. test case:
     * Rovar sikertelen mozgása olyan tektonra, ahol nincs gombafonál
     */
    private static void insectMoveMyceliumFail(){
        //Init
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

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=move(%s)=> %s %n", objectNames.get(B), objectNames.get(I));
        I.move(B);

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 30. test case:
     * Rovar általi gombafonál elvágás
     */
    private static void insectCutMycelium(){
        //Init
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
        printTrace = false;
    }

    /**
     * 31. test case:
     * Rovar elmenekülése
     */
    private static void insectRunAway(){
        //Init
        FertileTecton A = new FertileTecton();
        FertileTecton B = new FertileTecton();
        FertileTecton C = new FertileTecton();
        objectNames.put(A, "A: FertileTecton");
        objectNames.put(B, "B: FertileTecton");
        objectNames.put(C, "C: FertileTecton");
        A.addNeighbour(C);
        C.addNeighbour(B);
        C.addNeighbour(A);
        B.addNeighbour(C);

        Mycelium M = new Mycelium();
        objectNames.put(M, "M: Mycelium");
        B.addMycelium(M);
        M.setLocation(B);

        Insect I = new Insect();
        objectNames.put(I, "I: Insect");
        A.addOccupant(I);
        I.setLocation(A);

        //Test case
        printTrace = true;

        System.out.println("Tester");
        System.out.printf("\t=runAway()=> %s %n", objectNames.get(I));
        I.runAway();

        objectNames.clear();
        printTrace = false;
    }

    /**
     * 32. test case:
     * Gombatest sikeres spórakilövése szomszédos tektonra
     */
    private static void mushroomBodySuccesfullEjectSpore1Distance(){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * 33. test case:
     * Gombatest sikeres spórakilövése szomszédos tekton szomszédjára
     */
    private static void mushroomBodySuccesfullEjectSpore2Distance(){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * 34. test case:
     * Gombatest sikertelen spórakilövése szomszédos tekton szomszédjára
     */
    private static void mushroomBodyFailedEjectSpore2Distance(){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * 35. test case:
     * Gombatest sikertelen spórakilövése szomszédos tekton szomszédjánál távolabb lévő tektonra
     */
    private static void mushroomBodyFailedEjectSpore3Distance(){
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
