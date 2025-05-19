import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.View;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A játéktér megjelenítését megjelenítő osztály.
 */
public class GameFieldView extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final int TECTON_SIZE = 50;

    private Map<SwingTecton, Node> tectonNodes = new HashMap<>();

    private List<Edge> edges = new ArrayList<>();

    private double repulsionForce = 1000.0;
    private double springLength = 300.0;
    private double springConstant = 0.03;
    private double damping = 0.85;

    private Timer layoutTimer;

    public List<TectonView> tectons = new ArrayList<>();

    public GameFieldView() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        setLayout(null);

        layoutTimer = new Timer(30, e -> updateLayout());
        layoutTimer.start();
    }

    public void BuildGraph() {
        for (SwingTecton component : new ArrayList<>(tectonNodes.keySet())) {
            removeTecton(component);
        }
        edges.clear();
        tectonNodes.clear();
        removeAll();

        if (tectons.isEmpty()) {
            repaint();
            return;
        }

        Random random = new Random();
        for (TectonView tecton : tectons) {
            SwingTecton tectonComponent = (SwingTecton) ViewRepository.getView(tecton);

            double x = random.nextDouble() * (WIDTH - 2 * TECTON_SIZE) + TECTON_SIZE;
            double y = random.nextDouble() * (HEIGHT - 2 * TECTON_SIZE) + TECTON_SIZE;
            addSwingTecton(tectonComponent, x, y);
        }

        for (TectonView tecton : tectons) {
            SwingTecton tectonComponent = (SwingTecton) ViewRepository.getView(tecton);

            List<TectonView> neighbors = tecton.getNeighboursViews();

            for (TectonView neighbor : neighbors) {
                SwingTecton neighborComponent = (SwingTecton) ViewRepository.getView(neighbor);
                addEdge(tectonComponent, neighborComponent);
            }
        }

        for (int i = 0; i < 10; i++) {
            updateLayout();
        }

        repaint();
    }

    /**
     * Set tectons received from procedual controller as nodes in the graph
     * This method is called by StartGameListener when a new game starts
     */
    public void SetTectons(List<TectonView> tectonsList) {
        tectons.clear();
        tectons.addAll(tectonsList);
        BuildGraph();
    }

    /**
     * Add a tecton component to the graph
     */
    public void addSwingTecton(SwingTecton tectonComponent, double x, double y) {
        // Create a node for this tecton component
        Node node = new Node(x, y);
        tectonNodes.put(tectonComponent, node);

        // Add the component to the panel
        JPanel panel = (JPanel)tectonComponent;
        panel.setBounds((int)x, (int)y, TECTON_SIZE, TECTON_SIZE);
        add(panel);

        repaint();
    }

    public void addTecton(Tecton tecton) {
        tectons.add(tecton);
        BuildGraph();
    }

    /**
     * Remove a tecton component from the graph
     */
    public void removeTecton(SwingTecton tectonComponent) {
        remove((JPanel) tectonComponent);
        tectonNodes.remove(tectonComponent);

        // Remove any edges connected to this component
        edges.removeIf(edge -> edge.involves(tectonComponent));

        repaint();
    }

    /**
     * Connect two tectons with an edge
     */
    public void addEdge(SwingTecton tecton1, SwingTecton tecton2) {
        if (tectonNodes.containsKey(tecton1) && tectonNodes.containsKey(tecton2)) {
            if (!edgeExists(tecton1, tecton2)) {
                edges.add(new Edge(tecton1, tecton2));
                repaint();
            }
        }
    }

    /**
     * Check if an edge already exists between two tecton components
     */
    private boolean edgeExists(SwingTecton tecton1, SwingTecton tecton2) {
        for (Edge edge : edges) {
            if ((edge.tecton1 == tecton1 && edge.tecton2 == tecton2) ||
                    (edge.tecton1 == tecton2 && edge.tecton2 == tecton1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2f));
        g2d.setColor(Color.GRAY);

        for (Edge edge : edges) {
            Node n1 = tectonNodes.get(edge.tecton1);
            Node n2 = tectonNodes.get(edge.tecton2);

            if (n1 != null && n2 != null) {
                g2d.drawLine(
                        (int)n1.x,  // Use the node's x position directly
                        (int)n1.y,  // Use the node's y position directly
                        (int)n2.x,  // Use the node's x position directly
                        (int)n2.y   // Use the node's y position directly
                );
            }
        }
    }


    /**
     * Update the force-directed layout
     */
    private void updateLayout() {
        for (Node node : tectonNodes.values()) {
            node.forceX = 0;
            node.forceY = 0;
        }

        for (Map.Entry<SwingTecton, Node> entry1 : tectonNodes.entrySet()) {
            Node node1 = entry1.getValue();

            for (Map.Entry<SwingTecton, Node> entry2 : tectonNodes.entrySet()) {
                if (entry1 == entry2) continue;

                Node node2 = entry2.getValue();

                double dx = node2.x - node1.x;
                double dy = node2.y - node1.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < 0.1) {
                    distance = 0.1;
                    dx = (Math.random() - 0.5) * 0.1;
                    dy = (Math.random() - 0.5) * 0.1;
                }

                double minDistance = TECTON_SIZE;

                double force;
                if (distance < minDistance) {
                    force = repulsionForce * 3 * Math.pow(minDistance / distance, 3);
                } else {
                    force = repulsionForce / (distance * distance);
                }

                double fx = force * dx / distance;
                double fy = force * dy / distance;

                node1.forceX -= fx;
                node1.forceY -= fy;
            }
        }

        for (Edge edge : edges) {
            Node node1 = tectonNodes.get(edge.tecton1);
            Node node2 = tectonNodes.get(edge.tecton2);

            if (node1 == null || node2 == null) continue;

            double dx = node2.x - node1.x;
            double dy = node2.y - node1.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < 0.1) {
                distance = 0.1;
                dx = (Math.random() - 0.5) * 0.1;
                dy = (Math.random() - 0.5) * 0.1;
            }

            double displacement = distance - springLength;
            double force = springConstant * displacement;

            double fx = force * dx / distance;
            double fy = force * dy / distance;

            node1.forceX += fx;
            node1.forceY += fy;
            node2.forceX -= fx;
            node2.forceY -= fy;
        }

        for (Node node : tectonNodes.values()) {
            node.velocityX = (node.velocityX + node.forceX) * damping;
            node.velocityY = (node.velocityY + node.forceY) * damping;

            node.x += node.velocityX;
            node.y += node.velocityY;

            if (node.x < TECTON_SIZE/2) {
                node.x = TECTON_SIZE/2;
                node.velocityX = Math.abs(node.velocityX) * 0.5; // Bounce with reduced velocity
            } else if (node.x > WIDTH - TECTON_SIZE/2) {
                node.x = WIDTH - TECTON_SIZE/2;
                node.velocityX = -Math.abs(node.velocityX) * 0.5; // Bounce with reduced velocity
            }

            if (node.y < TECTON_SIZE/2) {
                node.y = TECTON_SIZE/2;
                node.velocityY = Math.abs(node.velocityY) * 0.5;
            } else if (node.y > HEIGHT - TECTON_SIZE/2) {
                node.y = HEIGHT - TECTON_SIZE/2;
                node.velocityY = -Math.abs(node.velocityY) * 0.5;
            }
        }

        resolveOverlaps();

        for (Map.Entry<SwingTecton, Node> entry : tectonNodes.entrySet()) {
            SwingTecton tecton = entry.getKey();
            Node node = entry.getValue();

            if (tecton != null) {
                JPanel panel = (JPanel)tecton;
                panel.setBounds(
                        (int)(node.x - TECTON_SIZE/2),
                        (int)(node.y - TECTON_SIZE/2),
                        TECTON_SIZE,
                        TECTON_SIZE
                );
            }
        }

        repaint();
    }


    /**
     * Resolve any remaining overlaps by directly separating nodes
     */
    private void resolveOverlaps() {
        boolean overlapsExist = true;
        int iterations = 0;
        final int MAX_ITERATIONS = 5;

        while (overlapsExist && iterations < MAX_ITERATIONS) {
            overlapsExist = false;
            iterations++;

            for (Map.Entry<SwingTecton, Node> entry1 : tectonNodes.entrySet()) {
                Node node1 = entry1.getValue();

                for (Map.Entry<SwingTecton, Node> entry2 : tectonNodes.entrySet()) {
                    if (entry1 == entry2) continue;

                    Node node2 = entry2.getValue();

                    double dx = node2.x - node1.x;
                    double dy = node2.y - node1.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    double minDistance = TECTON_SIZE;
                    if (distance < minDistance) {
                        overlapsExist = true;

                        double separationDistance = (minDistance - distance) / 2.0 + 2.0; // Add small buffer

                        if (distance < 0.1) {
                            distance = 0.1;
                            dx = (Math.random() - 0.5) * 0.1;
                            dy = (Math.random() - 0.5) * 0.1;
                        }

                        double separationX = dx * separationDistance / distance;
                        double separationY = dy * separationDistance / distance;

                        node1.x -= separationX;
                        node1.y -= separationY;
                        node2.x += separationX;
                        node2.y += separationY;

                        node1.x = Math.max(TECTON_SIZE/2, Math.min(WIDTH - TECTON_SIZE/2, node1.x));
                        node1.y = Math.max(TECTON_SIZE/2, Math.min(HEIGHT - TECTON_SIZE/2, node1.y));
                        node2.x = Math.max(TECTON_SIZE/2, Math.min(WIDTH - TECTON_SIZE/2, node2.x));
                        node2.y = Math.max(TECTON_SIZE/2, Math.min(HEIGHT - TECTON_SIZE/2, node2.y));
                    }
                }
            }
        }
    }

    /**
     * Node class for force-directed graph layout
     */
    private static class Node {
        double x, y;
        double velocityX = 0;
        double velocityY = 0;
        double forceX = 0;
        double forceY = 0;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Edge class connecting two tectons
     */
    private static class Edge {
        SwingTecton tecton1;
        SwingTecton tecton2;

        Edge(SwingTecton t1, SwingTecton t2) {
            this.tecton1 = t1;
            this.tecton2 = t2;
        }

        boolean involves(SwingTecton tecton) {
            return tecton1 == tecton || tecton2 == tecton;
        }

        SwingTecton getOther(SwingTecton tecton) {
            return tecton == tecton1 ? tecton2 : tecton1;
        }
    }
}