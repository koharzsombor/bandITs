import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Az elkepzeles az, hogy JComponentkent kezelem a node-okat, igy mivel tobb fajta
 * tecton is letezik tudom egyszerre kezelni oket mint node-ok. Ezekre rarajzolni mas objektumokat
 * amik nem node-ok (vagyis nem tectonok) nem tudtam kitalalni eddig
 */
public class GameFieldView extends JPanel {

    public static final int TECTON_SIZE = 80; // Ugyan az mint a tectonoknak
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Force parameters
    private static final double REPULSION = 8000; // Ennyivel taszitjak egymast
    private static final double ATTRACTION = 0.05; // Ennyivel vonzak egymast
    private static final double DAMPING = 0.85; // Hogy ne orokre mozogjanak
    private static final double MAX_VELOCITY = 5.0; // Mozgasi sebesseg maximum

    // Node data type
    static class Node {
        double x, y;
        double vx, vy;
        List<Node> neighbors = new ArrayList<>();
        JComponent component;

        Node(double x, double y, JComponent component) {
            this.x = x;
            this.y = y;
            this.vx = 0;
            this.vy = 0;
            this.component = component;
        }
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();
        Map<JComponent, Node> componentToNodeMap = new HashMap<>();
    }

    private final Graph graph;
    private Timer animationTimer;
    private List<TectonView> tectons;


    public GameFieldView() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        graph = new Graph();

        // 30 fps
        animationTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyForces();
                updatePositions();
                repaint(); // itt csak a osszekoto vonalak rajzolasara
            }
        });
    }

    public Node addTecton(JComponent tectonComponent, double x, double y) {
        // Check if it's already in the graph
        if (graph.componentToNodeMap.containsKey(tectonComponent)) {
            return graph.componentToNodeMap.get(tectonComponent);
        }

        Node node = new Node(x, y, tectonComponent);
        graph.nodes.add(node);
        graph.componentToNodeMap.put(tectonComponent, node);

        // Add the component to the panel if not already added
        if (tectonComponent.getParent() != this) {
            add(tectonComponent);
        }

        // Position the component
        updateTectonPosition(node);

        return node;
    }



    /**
     * Connect two JComponent nodes in the graph. Meg nincs hasznalva mivel nem tudom
     * mikor fog letezni az osszes swing node amiket tudok majd osszekotni
     */
    public void connectTectons(JComponent tectonComponent1, JComponent tectonComponent2) {
        Node node1 = graph.componentToNodeMap.get(tectonComponent1);
        Node node2 = graph.componentToNodeMap.get(tectonComponent2);

        if (node1 != null && node2 != null) {
            // Only add if not already connected
            if (!node1.neighbors.contains(node2)) {
                node1.neighbors.add(node2);
            }
            if (!node2.neighbors.contains(node1)) {
                node2.neighbors.add(node1);
            }
        }
    }


    /**
     * Update the position of a JComponent on the panel
     */
    public void updateTectonPosition(Node node) {
        if (node.component != null) {
            // Get the component's size
            int componentWidth = node.component.getPreferredSize().width;
            int componentHeight = node.component.getPreferredSize().height;

            // If the component hasn't set a preferred size, use a default
            if (componentWidth <= 0 || componentHeight <= 0) {
                componentWidth = TECTON_SIZE;
                componentHeight = TECTON_SIZE;
            }

            // Position the component with its center at (x,y)
            node.component.setBounds(
                    (int)(node.x - componentWidth / 2),
                    (int)(node.y - componentHeight / 2),
                    componentWidth,
                    componentHeight
            );
        }
    }


    /**
     * The controllers will call this method once they have created
     * all the necessary Node objects and set up their relationships.
     * This method will replace the current graph with the provided nodes.
     */
    public void setGraphData(List<Node> newNodes) {
        // Clear existing components from the panel
        removeAll();

        // Clear our existing graph data
        graph.nodes.clear();
        graph.componentToNodeMap.clear();

        // Add the new nodes
        for (Node node : newNodes) {
            // Add the component to the panel
            if (node.component != null) {
                add(node.component);

                // Update our internal mappings
                graph.nodes.add(node);
                graph.componentToNodeMap.put(node.component, node);

                // Set the initial position
                updateTectonPosition(node);
            }
        }

        // Force revalidation and repaint
        revalidate();
        repaint();
    }

    /**
     * Start the force-directed graph animation
     */
    public void startAnimation() {
        if (!animationTimer.isRunning()) {
            animationTimer.start();
        }
    }

    /**
     * Stop the animation
     */
    public void stopAnimation() {
        if (animationTimer.isRunning()) {
            animationTimer.stop();
        }
    }

    /**
     * Calculate forces between nodes to create a force-directed layout
     */
    private void applyForces() {
        List<Node> nodes = graph.nodes;
        int n = nodes.size();

        // Reset velocities to zero
        for (Node node : nodes) {
            node.vx = 0;
            node.vy = 0;
        }

        // Apply repulsive forces between all pairs of nodes
        for (int i = 0; i < n; i++) {
            Node nodeA = nodes.get(i);

            for (int j = i + 1; j < n; j++) {
                Node nodeB = nodes.get(j);

                // Calculate distance between nodes
                double dx = nodeB.x - nodeA.x;
                double dy = nodeB.y - nodeA.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Prevent division by zero
                if (distance == 0) {
                    distance = 0.1;
                    dx = 0.1;
                    dy = 0;
                }

                // Calculate repulsive force (inverse square law)
                double force = REPULSION / (distance * distance);

                // Apply force to both nodes in opposite directions
                double fx = (dx / distance) * force;
                double fy = (dy / distance) * force;

                nodeA.vx -= fx;
                nodeA.vy -= fy;
                nodeB.vx += fx;
                nodeB.vy += fy;
            }
        }

        // Apply attractive forces between connected nodes
        for (Node node : nodes) {
            for (Node neighbor : node.neighbors) {
                // Calculate distance between nodes
                double dx = neighbor.x - node.x;
                double dy = neighbor.y - node.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Prevent division by zero
                if (distance == 0) {
                    distance = 0.1;
                    dx = 0.1;
                    dy = 0;
                }

                // Calculate attractive force (linear with distance)
                double force = ATTRACTION * distance;

                // Apply force to node (neighbor gets the same force from its loop)
                double fx = (dx / distance) * force;
                double fy = (dy / distance) * force;

                node.vx += fx;
                node.vy += fy;
            }
        }
    }

    /**
     * Update the positions of all nodes based on calculated velocities
     */
    private void updatePositions() {
        // Apply velocities to positions with damping
        for (Node node : graph.nodes) {
            // Apply damping to velocity
            node.vx *= DAMPING;
            node.vy *= DAMPING;

            // Limit maximum velocity
            double speed = Math.sqrt(node.vx * node.vx + node.vy * node.vy);
            if (speed > MAX_VELOCITY) {
                node.vx = (node.vx / speed) * MAX_VELOCITY;
                node.vy = (node.vy / speed) * MAX_VELOCITY;
            }

            // Update position
            node.x += node.vx;
            node.y += node.vy;

            // Keep nodes within bounds
            node.x = Math.max(0, Math.min(WIDTH - TECTON_SIZE, node.x));
            node.y = Math.max(0, Math.min(HEIGHT - TECTON_SIZE, node.y));

            // Update the JComponent component position
            updateTectonPosition(node);
        }
    }

    /**
     * Paint the edges between connected nodes
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw edges
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        for (Node node : graph.nodes) {
            if (node.component != null) {
                int x1 = (int)node.x;
                int y1 = (int)node.y;

                for (Node neighbor : node.neighbors) {
                    if (neighbor.component != null) {
                        int x2 = (int)neighbor.x;
                        int y2 = (int)neighbor.y;
                        g2d.drawLine(x1, y1, x2, y2);
                    }
                }
            }
        }
    }

}

    public void SetTectons(List<TectonView> tectons) {
        this.tectons = tectons;
    }
}

