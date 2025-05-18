import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.View;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameFieldView extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final int TECTON_SIZE = 50;

    // Map to store nodes and their current positions
    private Map<SwingTecton, Node> tectonNodes = new HashMap<>();

    // Graph structure
    private List<Edge> edges = new ArrayList<>();

    // Physics parameters for force-directed layout
    private double repulsionForce = 800.0;  // Nodes push each other apart
    private double springLength = 200.0;    // Ideal edge length
    private double springConstant = 0.03;   // How stiff the edges are
    private double damping = 0.85;           // Damping factor for movement

    private Timer layoutTimer;

    public List<TectonView> tectons = new ArrayList<>();

    public GameFieldView() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        // Use null layout for absolute positioning
        setLayout(null);

        // Start layout simulation
        layoutTimer = new Timer(30, e -> updateLayout());
        layoutTimer.start();
    }

    public void BuildGraph() {
        // Clear existing nodes and edges
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

        // Add each tecton to the graph
        Random random = new Random();
        for (TectonView tecton : tectons) {
            // Get the SwingTecton from ViewRepository
            SwingTecton tectonComponent = (SwingTecton) ViewRepository.getView(tecton);

            // Add the component to the graph at a random initial position
            double x = random.nextDouble() * (WIDTH - 2 * TECTON_SIZE) + TECTON_SIZE;
            double y = random.nextDouble() * (HEIGHT - 2 * TECTON_SIZE) + TECTON_SIZE;
            addSwingTecton(tectonComponent, x, y);
        }

        // Create edges based on tecton relationships
        for (TectonView tecton : tectons) {
            SwingTecton tectonComponent = (SwingTecton) ViewRepository.getView(tecton);

            // Get neighbors using the TectonView interface's getNeighboursViews method
            List<TectonView> neighbors = tecton.getNeighboursViews();

            for (TectonView neighbor : neighbors) {
                // Get the SwingTecton for this neighbor
                SwingTecton neighborComponent = (SwingTecton) ViewRepository.getView(neighbor);
                addEdge(tectonComponent, neighborComponent);
            }
        }

        // Force an initial layout update
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
        // Only add if both tectons exist in the graph and no edge already exists
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

        // Draw edges
        g2d.setStroke(new BasicStroke(2f));
        g2d.setColor(Color.GRAY);

        for (Edge edge : edges) {
            Node n1 = tectonNodes.get(edge.tecton1);
            Node n2 = tectonNodes.get(edge.tecton2);

            if (n1 != null && n2 != null) {
                // Draw from center to center
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
        // Reset forces for all nodes
        for (Node node : tectonNodes.values()) {
            node.forceX = 0;
            node.forceY = 0;
        }

        // Apply repulsion forces between all nodes
        for (Map.Entry<SwingTecton, Node> entry1 : tectonNodes.entrySet()) {
            Node node1 = entry1.getValue();

            for (Map.Entry<SwingTecton, Node> entry2 : tectonNodes.entrySet()) {
                if (entry1 == entry2) continue;

                Node node2 = entry2.getValue();

                // Calculate distance between nodes
                double dx = node2.x - node1.x;
                double dy = node2.y - node1.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Avoid division by zero and give small random displacement to prevent perfect overlap
                if (distance < 0.1) {
                    distance = 0.1;
                    dx = (Math.random() - 0.5) * 0.1;
                    dy = (Math.random() - 0.5) * 0.1;
                }

                // Minimum distance to prevent overlap (diameter of a node)
                double minDistance = TECTON_SIZE;

                // Calculate repulsion force - make it much stronger when nodes are close to overlapping
                double force;
                if (distance < minDistance) {
                    // Strong repulsion when overlapping - cubic falloff for stronger effect
                    force = repulsionForce * 3 * Math.pow(minDistance / distance, 3);
                } else {
                    force = repulsionForce / (distance * distance);
                }

                // Apply force along the direction vector
                double fx = force * dx / distance;
                double fy = force * dy / distance;

                node1.forceX -= fx;
                node1.forceY -= fy;
            }
        }

        // Apply spring forces along edges
        for (Edge edge : edges) {
            Node node1 = tectonNodes.get(edge.tecton1);
            Node node2 = tectonNodes.get(edge.tecton2);

            if (node1 == null || node2 == null) continue;

            // Calculate distance between nodes
            double dx = node2.x - node1.x;
            double dy = node2.y - node1.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            // Avoid division by zero
            if (distance < 0.1) {
                distance = 0.1;
                dx = (Math.random() - 0.5) * 0.1;
                dy = (Math.random() - 0.5) * 0.1;
            }

            // Calculate spring force (proportional to difference from spring length)
            double displacement = distance - springLength;
            double force = springConstant * displacement;

            // Apply force along the direction vector
            double fx = force * dx / distance;
            double fy = force * dy / distance;

            node1.forceX += fx;
            node1.forceY += fy;
            node2.forceX -= fx;
            node2.forceY -= fy;
        }

        // Update velocities and positions
        for (Node node : tectonNodes.values()) {
            // Apply forces to update velocity (with damping)
            node.velocityX = (node.velocityX + node.forceX) * damping;
            node.velocityY = (node.velocityY + node.forceY) * damping;

            // Update position
            node.x += node.velocityX;
            node.y += node.velocityY;

            // Boundary constraints to keep nodes within the panel
            if (node.x < TECTON_SIZE/2) {
                node.x = TECTON_SIZE/2;
                node.velocityX = Math.abs(node.velocityX) * 0.5; // Bounce with reduced velocity
            } else if (node.x > WIDTH - TECTON_SIZE/2) {
                node.x = WIDTH - TECTON_SIZE/2;
                node.velocityX = -Math.abs(node.velocityX) * 0.5; // Bounce with reduced velocity
            }

            if (node.y < TECTON_SIZE/2) {
                node.y = TECTON_SIZE/2;
                node.velocityY = Math.abs(node.velocityY) * 0.5; // Bounce with reduced velocity
            } else if (node.y > HEIGHT - TECTON_SIZE/2) {
                node.y = HEIGHT - TECTON_SIZE/2;
                node.velocityY = -Math.abs(node.velocityY) * 0.5; // Bounce with reduced velocity
            }
        }

        // Add an explicit overlap resolution step
        resolveOverlaps();

        // Update actual component positions
        for (Map.Entry<SwingTecton, Node> entry : tectonNodes.entrySet()) {
            SwingTecton tecton = entry.getKey();
            Node node = entry.getValue();

            if (tecton != null) {
                // Position the component so that its center is at the node position
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
        final int MAX_ITERATIONS = 5; // Limit iterations to avoid infinite loops

        while (overlapsExist && iterations < MAX_ITERATIONS) {
            overlapsExist = false;
            iterations++;

            for (Map.Entry<SwingTecton, Node> entry1 : tectonNodes.entrySet()) {
                Node node1 = entry1.getValue();

                for (Map.Entry<SwingTecton, Node> entry2 : tectonNodes.entrySet()) {
                    if (entry1 == entry2) continue;

                    Node node2 = entry2.getValue();

                    // Calculate distance between node centers
                    double dx = node2.x - node1.x;
                    double dy = node2.y - node1.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // Check if nodes overlap
                    double minDistance = TECTON_SIZE;
                    if (distance < minDistance) {
                        overlapsExist = true;

                        // Calculate separation vector
                        double separationDistance = (minDistance - distance) / 2.0 + 2.0; // Add small buffer

                        // Avoid division by zero
                        if (distance < 0.1) {
                            distance = 0.1;
                            dx = (Math.random() - 0.5) * 0.1;
                            dy = (Math.random() - 0.5) * 0.1;
                        }

                        double separationX = dx * separationDistance / distance;
                        double separationY = dy * separationDistance / distance;

                        // Move nodes apart
                        node1.x -= separationX;
                        node1.y -= separationY;
                        node2.x += separationX;
                        node2.y += separationY;

                        // Keep nodes within bounds
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
        double x, y;            // Position
        double velocityX = 0;   // Velocity
        double velocityY = 0;
        double forceX = 0;      // Current forces
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