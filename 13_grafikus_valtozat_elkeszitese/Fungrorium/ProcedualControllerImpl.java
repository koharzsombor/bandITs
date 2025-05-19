import java.util.*;

/**
 * A térkép generátor implementációja.
 */
public class ProcedualControllerImpl implements ProcedualController {
    /**
     * Egy játékosra jutó tektonok száma.
     */
    private static final int TECTON_PER_PLAYER = 6;

    /**
     * A tektonok medián szomszéd száma elosztva kettővel.
     */
    private static final int MEDIAN_NEIGHBOUR_COUNT = 3;

    /**
     * A tektonok szomszéd számának intervallumának a nagysága elosztva kettővel..
     */
    private static final int NEIGHBOUR_COUNT_RANGE = 2;

    /**
     * Esély arra hogy gombafonál növéskor Carnivorous nő sima helyett
     */
    private static final int CHANCE_FOR_CARNIVOROUS_MYCELIUM = 15;

    /**
     * A térkép létrehozásáért felelős osztály.
     */
    private final MapCreationController mapCreationController;

    /**
     * A játékosok tára.
     */
    private final PlayerContainer playerContainer;

    /**
     * Tektonokat irányító objektum.
     */
    private final TectonController tectonController;

    /**
     * Random szám generátor.
     */
    private final Random random;

    /**
     * Létrehozza a kontrollert a megfelelő függőségekkel.
     *
     * @param mapCreationController A térkép létrehozásáért felelős osztály.
     * @param playerContainer A játékosok tára.
     * @param tectonController Tektonokat irányító objektum.
     */
    public ProcedualControllerImpl(MapCreationController mapCreationController, PlayerContainer playerContainer, TectonController tectonController) {
        this.mapCreationController = mapCreationController;
        this.playerContainer = playerContainer;
        this.tectonController = tectonController;

        random = new Random();
    }

    /**
     * A térképet proceduálisan létrehozza.
     */
    @Override
    public List<Tecton> generateMap() {
        List<Entomologist> entomologists = new ArrayList<>();
        List<Mycologist> mycologists = new ArrayList<>();

        for (Player player : playerContainer.getEntomologists()) {
            entomologists.add((Entomologist)player);
        }

        for (Player player : playerContainer.getMycologists()) {
            mycologists.add((Mycologist)player);
        }

        int playerCount = 0;
        for (Player player : playerContainer.getPlayers()) {
            playerCount++;
        }

        List<Tecton> tectons = new ArrayList<>();
        List<Tecton> startingLocations = new ArrayList<>();

        for (int i = 0; i < playerCount * TECTON_PER_PLAYER; i++) {
            String name = "Tecton-" + i;
            int type = random.nextInt(5);
            //int type = 0;
            String tectonType = "";
            switch (type) {
                case 0 -> tectonType = "fertiletecton";
                case 1 -> tectonType = "semifertiletecton";
                case 2 -> tectonType = "multilayeredtecton";
                case 3 -> tectonType = "aridtecton";
                case 4 -> tectonType = "sustainingtecton";
                default -> throw new IllegalArgumentException("It is not a type");
            }

            mapCreationController.createTecton(tectonType, name);

            if (!tectonType.equals("semifertiletecton"))
                startingLocations.add((Tecton)ObjectRegistry.getObject(name));

            tectons.add((Tecton)ObjectRegistry.getObject(name));
        }

        for (Tecton tecton : tectons) {
            for (int i = 0; i < MEDIAN_NEIGHBOUR_COUNT + random.nextInt(-NEIGHBOUR_COUNT_RANGE, NEIGHBOUR_COUNT_RANGE); i++) {
                Tecton neighbour = tectons.get(random.nextInt(tectons.size()));
                tectonController.addNeighbour(tecton, neighbour);
            }
        }

        List<Tecton> insectStartPosition = new ArrayList<>();

        int myceliumID = 0;
        int mushroomBodyID = 0;
        for (Mycologist mycologist : mycologists) {
            Tecton startingLocation = startingLocations.get(random.nextInt(startingLocations.size()));

            String myceliumType = random.nextInt(100) > (100-CHANCE_FOR_CARNIVOROUS_MYCELIUM) ? "carnivorousmycelium" : "mycelium";

            mapCreationController.createMycelium(startingLocation, myceliumType, "Mycelium-" + myceliumID, mycologist);
            Mycelium mycelium = (Mycelium)ObjectRegistry.getObject("Mycelium-" + myceliumID);

            insectStartPosition.add(startingLocation);
            myceliumID++;

            mapCreationController.createMushroomBody(startingLocation, "MushroomBody-" + mushroomBodyID, mycologist);
            mushroomBodyID++;
            startingLocations.remove(startingLocation);

            for (Tecton neighbour : startingLocation.getNeighbours()) {
                if (!startingLocations.contains(neighbour))
                    continue;

                myceliumType = random.nextInt(100) > (100-CHANCE_FOR_CARNIVOROUS_MYCELIUM) ? "carnivorousmycelium" : "mycelium";
                mapCreationController.createMycelium(neighbour, myceliumType, "Mycelium-" + myceliumID, mycologist);
                Mycelium mycelium2 = (Mycelium)ObjectRegistry.getObject("Mycelium-" + myceliumID);
                insectStartPosition.add(neighbour);
                myceliumID++;

                startingLocations.remove(neighbour);
            }
        }

        for (Entomologist entomologist : entomologists) {
            mapCreationController.createInsect(insectStartPosition.get(random.nextInt(insectStartPosition.size())),
                    "Insect-" + entomologist.getName(), entomologist);
        }

        return tectons;
    }
}
