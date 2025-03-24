import java.util.HashMap;
import java.util.Scanner;

/**
 * A program futtatásért és tesztelő - program összeköttetésért felelős osztály.
 */
public class Main {
    public static Scanner selectScanner = new Scanner(System.in);

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

        try {
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
        finally {
            selectScanner.close();
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B FertileTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B FertileTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B FertileTectonon történő létrehozásának feltételei fennállnak,
     *        M gombafonál a B FertileTectonon található spórák számának megfelelő sebességgel B FertileTectonon véglegesen létrejön.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B FertileTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B FertileTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B FertileTectonon történő létrehozásának feltételei nem állnak fenn, az előzetesen létrejött M gombafonál törlődik.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B SemiFertileTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B SemiFertileTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B SemiFertileTectonon történő létrehozásának feltételei fennállnak,
     *        M gombafonál a B SemiFertileTectonon található spórák számának megfelelő sebességgel B SemiFertileTectonon véglegesen létrejön.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B SemiFertileTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B SemiFertileTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B SemiFertileTectonon történő létrehozásának feltételei nem állnak fenn, az előzetesen létrejött M gombafonál törlődik.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B AridTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B AridTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B AridTectonon történő létrehozásának feltételei fennállnak,
     *     M gombafonál a B AridTectonon található spórák számának megfelelő sebességgel B AridTectonon véglegesen létrejön.
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
     *
     *      1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B AridTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B AridTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B AridTectonon történő létrehozásának feltételei nem állnak fenn, az előzetesen létrejött M gombafonál törlődik.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B MultiLayeredTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B MultiLayeredTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B MultiLayeredTectonon történő létrehozásának feltételei fennállnak,
     *     M gombafonál a B MultiLayeredTectonon található spórák számának megfelelő sebességgel B MultiLayeredTectonon véglegesen létrejön.
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
     *
     *     1. Tester előzetesen létrehozza M gombafonalat.
     *     2. M gombafonál megvizsgáltatja B MultiLayeredTectonnal, hogy hány gombafonál lehet rajta (a tektonon).
     *     3. M gombafonál megvizsgáltatja B MultiLayeredTectonnal, hogy hány gombafonál van rajta (a tektonon).
     *     4. Mivel M gombafonál B MultiLayeredTectonon történő létrehozásának feltételei nem állnak fenn, az előzetesen létrejött M gombafonál törlődik.
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
     *
     *     1. Tester előzetesen létrehozza MB gombatestet.
     *     2. MB gombatest megvizsgáltatja T FertileTectonnal, hogy van-e rajta gombatest.
     *     3. MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta elég spóra.
     *     4. MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombafonál.
     *     5. A fentiek alapján MB gombatest T FertileTectonra történő növekedésének feltételei teljeskörűen fennállnak.
     *     6. T FertileTecton 3-mal csökkenti spóráinak számát.
     *     7. MB gombatest véglegesen létrejön.
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
     *
     *     1. Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombafonál.
     *     5. A T FertileTectonon már van gombatest, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombafonál.
     *     5. A T FertileTectonon nincs elég spóra, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T FertileTectonnal, hogy van-e rajta gombafonál.
     *     5. A T FertileTectonon nincs gombafonál, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. Tester előzetesen létrehozza MB gombatestet.
     *     2. MB gombatest megvizsgáltatja T AridTectonnal, hogy van-e rajta gombatest.
     *     3. MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta elég spóra.
     *     4. MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombafonál.
     *     5. A fentiek alapján MB gombatest T AridTectonra történő növekedésének feltételei teljeskörűen fennállnak.
     *     6. T AridTecton 3-mal csökkenti spóráinak számát.
     *     7. MB gombatest véglegesen létrejön.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombafonál.
     *     5. A T AridTectonon már van gombatest, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombafonál.
     *     5. A T AridTectonon nincs elég spóra, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T AridTectonnal, hogy van-e rajta gombafonál.
     *     5. A T AridTectonon nincs gombafonál, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. Tester előzetesen létrehozza MB gombatestet.
     *     2. MB gombatest megvizsgáltatja T MultiLayeredTectonnal, hogy van-e rajta gombatest.
     *     3. MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta elég spóra.
     *     4. MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombafonál.
     *     5. A fentiek alapján MB gombatest T MultiLayeredTectonnal történő növekedésének feltételei teljeskörűen fennállnak.
     *     6. T MultiLayeredTecton 3-mal csökkenti spóráinak számát.
     *     7. MB gombatest véglegesen létrejön.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombafonál.
     *     5. A T MultiLayeredTectonon már van gombatest, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombafonál.
     *     5. A T MultiLayeredTectonon nincs elég spóra, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombatest.
     *     3. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta elég spóra.
     *     4. Az MB gombatest megvizsgáltatja a T MultiLayeredTectonnal, hogy van-e rajta gombafonál.
     *     5. A T MultiLayeredTectonon nincs gombafonál, ezért nem nőhet rajta MB gombatest.
     *     6. Az MB gombatest megsemmisül.
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
     *
     *     1. A Tester előzetesen létrehozza az MB gombatestet.
     *     2. Az MB gombatest megvizsgáltatja a T SemiFertileTectonnal, hogy lehet-e rajta gombatest.
     *     3. A T SemiFertileTectonon nem nőhet gombatest.
     *     4. Az MB gombatest megsemmisül.
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
     *
     *     1. I rovar spóraevésre kap utasítást a Testertől.
     *     2. I rovar üzen T FertileTecton-nak, hogy megeszik egy rajta lévő spórát.
     *     3. T FertileTecton üzen a spórának, hogy megette a rovar.
     *     4. StunSpore hatására a rovar Stun állapotba kerül.
     *     5. StunSpore megsemmisül.
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
        System.out.println("Tester");
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 23. test case:
     * PreventCutSpore elfogyasztása
     *
     *     1. I rovar spóraevésre kap utasítást a Testertől.
     *     2. I rovar üzen T FertileTecton-nak, hogy megeszik egy rajta lévő spórát.
     *     3. T FertileTecton üzen a spórának, hogy megette a rovar.
     *     4. PreventCutSpore hatására a rovar CannotCut állapotba kerül.
     *     5. PreventCutSpore megsemmisül.
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
        System.out.println("Tester");
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 24. test case:
     * SpeedSpore elfogyasztása
     *
     *     1. I rovar spóraevésre kap utasítást a Testertől.
     *     2. I rovar üzen T FertileTecton-nak, hogy megeszik egy rajta lévő spórát.
     *     3. T FertileTecton üzen a spórának, hogy megette a rovar.
     *     4. SpeedSpore hatására a rovar Fast állapotba kerül.
     *     5. SpeedSpore megsemmisül.
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
        System.out.println("Tester");
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 25. test case:
     * SlownessSpore elfogyasztása
     *
     *     1. I rovar spóraevésre kap utasítást a Testertől.
     *     2. I rovar üzen T FertileTecton-nak, hogy megeszik egy rajta lévő spórát.
     *     3. T FertileTecton üzen a spórának, hogy megette a rovar.
     *     4. SlownessSpore hatására a rovar Slow állapotba kerül.
     *     5. SlownessSpore megsemmisül.
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
        System.out.println("Tester");
        I.eatSpore();

        objectNames.clear();
    }

    /**
     * 26. test case:
     * Tektontörés
     *
     *     1. Az A tekton jelzést kap, hogy új kör kezdődött.
     *     2. Elvágódik és egyúttal megszűnik létezni A tektonon lévő összes fonál.
     *     3. A gombafonalak elvágása során az egyes gombafonalak jelet küldenek A tekton szomszédos tektonjainak, hogy nézzék meg, hogy a rajtuk levő fonalak össze vannak-e kötve gombatesttel.
     *     4. Az A tekton jelet küld az összes rajta lévő rovarnak (jelen esetben csak I-nek), hogy meneküljenek el.
     *     5. Az A tekton létrehozza az új (letört) newt FertileTectont.
     *     6. Az A tekton és a newt FertileTecton szomszédosak lesznek.
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
     *
     *     1. I rovar mozgási szándékot jelez B tekton felé.
     *     2. B tekton megvizsgálja, hogy szomszédos-e I rovar tektonjával (A) és van-e rajta gombafonál.
     *     3. A fentiek alapján I rovar B tektonra való mozgásának feltételei teljeskörűen fennállnak.
     *     4. B tekton jelzi A tektonnak, hogy I elment róla.
     *     5. B tekton beállítja magának I-t, mint rajta lévő rovar.
     *     6. B visszajelez I rovarnak, hogy mozgása sikeres, ezáltal állítsa be saját helyét B-re és csökkentse a megmaradó lépéseinek számát eggyel.
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
     * 28. test case:
     * Rovar sikertelen mozgása nem szomszédos tektonra
     *
     *     1. I rovar mozgási szándékot jelez B tektonnak.
     *     2. B tekton megvizsgálja, hogy szomszédos-e I tektonjával (A) és van-e rajta gombafonál.
     *     3. B tekton nem szomszédos I rovar tektonjával így nem megy végbe a mozgás.
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
     * 29. test case:
     * Rovar sikertelen mozgása olyan tektonra, ahol nincs gombafonál
     *
     *     1. I rovar mozgási szándékot jelez B tekton felé.
     *     2. B tekton megvizsgálja, hogy szomszédos-e I tektonjával (A) és van-e rajta gombafonál.
     *     3. B tektonon nincs gombafonál így nem megy végbe a mozgás.
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
     * 30. test case:
     * Rovar általi gombafonál elvágás
     *
     *     1. A tekton jelet kap, hogy rajta fonalvágás történik.
     *     2. A tekton elvágja a rá legkorábban ránővő fonalat.
     *     3. A tekton jelet küld a szomszédos tektonjainak, hogy nézzék meg, hogy a rajtuk levő fonalak össze vannak-e kötve gombatesttel.
     *     4. Mivel a vágás után nincs több fonal A tektonon, jelet küld a rajta levő rovaroknak, hogy meneküljenek el.
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
     * 31. test case:
     * Rovar elmenekülése
     *
     *     1. I rovar jelet kap a tesztelőtől, hogy menekülnie kell.
     *     2. A rovar megnézi a Use-case-ben szereplő tektonokról, hogy tudna-e rájuk menekülni (van-e rajtuk gombafonál).
     *     3. I rovar (alap esetben véletlenszerűen,) jelen esetben a a felhasználótól kapott bemenet alapján, kiválaszt egy tektont.
     *     4. Ha van a kiválasztott tektonon van gombafonál akkor teljesül az átmenekülésnek a feltétele.
     *     5. I rovar szól a kiválasztott tektonnak, hogy most már rajta van.
     *     6. I rovar beállítja az új helyének a kiválasztott tektont.
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
     * 32. test case:
     * Gombatest sikeres spórakilövése szomszédos tektonra
     *
     *     1. MB gombatest utasítást kap, hogy lője ki a spóráit B FertileTectonra.
     *     2. MB gombatest utasítására A FertileTecton kiszámolja, hogy milyen messze van tőle B FertileTecton.
     *     3. MB gombatest a még megmaradt spórakilövéseinek száma és a tektontávolság alapján meghatározza, hogy el tud-e lőni B FertileTectonra.
     *     4. Mivel MB gombatest a vizsgálat eredményeként el tud lőni B FertileTectonra és még maradt spórakilövése,
     *        MB gombatest utasítására B FertileTecton az MB gombatest által kilőtt spórákat hozzáadja a rajta (B FertileTectonon) lévő spórák listájához.
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
     * 33. test case:
     * Gombatest sikeres spórakilövése szomszédos tekton szomszédjára
     *
     *     1. MB gombatest utasítást kap, hogy lője ki a spóráit C FertileTectonra.
     *     2. MB gombatest utasítására A FertileTecton kiszámolja, hogy milyen messze van tőle C FertileTecton.
     *     3. MB gombatest a még megmaradt spórakilövéseinek száma és a tektontávolság alapján meghatározza, hogy el tud-e lőni C FertileTectonra.
     *     4. Mivel MB gombatest a vizsgálat eredményeként el tud lőni C FertileTectonra és még maradt spórakilövése,
     *        MB gombatest utasítására C FertileTecton az MB gombatest által kilőtt spórákat hozzáadja a rajta (C FertileTectonon) lévő spórák listájához.
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
     * 34. test case:
     * Gombatest sikertelen spórakilövése szomszédos tekton szomszédjára
     *
     *     1. MB gombatest utasítást kap, hogy lője ki a spóráit C FertileTectonra.
     *     2. MB gombatest utasítására A FertileTecton kiszámolja, hogy milyen messze van tőle C FertileTecton.
     *     3. MB gombatest a még megmaradt spórakilövéseinek száma és a tektontávolság alapján meghatározza, hogy el tud-e lőni C FertileTectonra.
     *     4. Mivel nem ez MB gombatest utolsó kilövése, így szomszédos tekton szomszédjára, azaz C FertileTectonra nem lőhet spórát.
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
     * 35. test case:
     * Gombatest sikertelen spórakilövése szomszédos tekton szomszédjánál távolabb lévő tektonra
     *
     *     1. MB gombatest utasítást kap, hogy lője ki a spóráit D FertileTectonra.
     *     2. MB gombatest utasítására A FertileTecton kiszámolja, hogy milyen messze van tőle D FertileTecton.
     *     3. MB gombatest a még megmaradt spórakilövéseinek száma és a tektontávolság alapján meghatározza, hogy el tud-e lőni D FertileTectonra.
     *     4. Mivel D FertileTecton túl messze van, rá MB gombatest nem lőhet spórát.
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
