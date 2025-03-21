import java.util.HashMap;
import java.util.Scanner;

/**
 * A program futtatásért és tesztelő - program összeköttetésért felelős osztály.
 */
public class Main {
    /**
     *
     */
    public static final HashMap<Object, String> objectNames = new HashMap<>();

    /**
     *
     */
    public static boolean printTrace = false;


    /**
     * @param object
     */
    public static void mockDeletion(Object object) {
        if (printTrace) {
            System.out.printf("%s \n", objectNames.get(object));
            System.out.println("\t~finalize()");
        }
    }

    /**
     * A program futtatása ezzel a függvénnyel kezdődik.
     * @param args Futtatáshoz átadott paraméterek, jelen programban ezzel nem kezdünk semmit.
     */
    public static void main(String... args) {
        System.out.println("Fungorium - Szekeleton");
        printTestOptions();

        try(Scanner selectScanner = new Scanner(System.in)) {
            while (selectScanner.hasNext()) {
                if (selectScanner.hasNextInt()) {
                    int input = selectScanner.nextInt();

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
                        default -> System.out.println("A megadott szám nem értelmezhető teszt sorszámaként!");
                    }
                }
                else {
                    System.out.println("A megadott bemenet nem értelmezhető számként!");
                    selectScanner.next();
                }

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
    }

    /**
     * 1. test case
     */
    private static void myceliumGrowthSuccessOnFertileTecton() {
        //Init
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
     * 2. test case
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 3. test case
     */
    private static void myceliumGrowthSuccessOnSemiFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 4. test case
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnSemiFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 5. test case
     */
    private static void myceliumGrowthSuccessOnAridTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 6. test case
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnAridTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 7. test case
     */
    private static void myceliumGrowthSuccessOnMultiLayeredTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 8. test case
     */
    private static void myceliumGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 9. test case
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
     * 10. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 11. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 12. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnFertileTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 13. test case
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
     * 14. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnAridTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 15. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnAridTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 16. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnAridTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 17. test case
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
     * 18. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSpaceOnMultiLayeredTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 19. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfSporesOnMultiLayeredTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 20. test case
     */
    private static void mushroomBodyGrowthFailureDueToLackOfMyceliaOnMultiLayeredTecton() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 21. test case
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
     * 22. test case
     */
    private static void stunSporeEaten() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 23. test case
     */
    private static void preventCutSporeEaten() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 24. test case
     */
    private static void speedSporeEaten() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * 25. test case
     */
    private static void slownessSporeEaten() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
