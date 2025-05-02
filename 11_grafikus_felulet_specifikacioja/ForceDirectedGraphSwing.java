import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ForceDirectedGraphSwing extends JPanel {

    private Point pendingNewTecton = null;
    private boolean waitingForTectonClick = false;
    private Node pendingConnectNode = null;

    static class Node {
        double x, y;
        double vx, vy; // velocity
        List<Node> neighbors = new ArrayList<>();
        Color color = Color.PINK;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        boolean hasMushroomBody = false;
        boolean hasMycelium = false;
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();

        Node addNode(double x, double y) {
            Node n = new Node(x, y);
            nodes.add(n);
            return n;
        }
    }

    Graph graph = new Graph();
    Random rand = new Random();

    public ForceDirectedGraphSwing() {
        // initial node
        Node first = graph.addNode(400, 300);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Node clickedNode = null;
                int width = 36, height = 36;
                for (Node n : graph.nodes) {
                    int left = (int) n.x - width / 2, top = (int) n.y - height / 2;
                    if (e.getX() >= left && e.getX() <= left + width &&
                            e.getY() >= top && e.getY() <= top + height) {
                        clickedNode = n;
                        break;
                    }
                }

                // Left-click (split/connect-to-space mode)
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!waitingForTectonClick) {
                        if (clickedNode != null) {
                            // Split (add neighbor near this tecton)
                            double angle = rand.nextDouble() * 2 * Math.PI; // Itt lehet szimplan 0-a is, akkor mindig jobb oldalrol kezdi a berakast
                            double dist = 60;
                            double nx = clickedNode.x + Math.cos(angle) * dist;
                            double ny = clickedNode.y + Math.sin(angle) * dist;
                            Node newNode = graph.addNode(nx, ny);
                            newNode.hasMushroomBody = rand.nextBoolean();
                            newNode.hasMycelium = rand.nextBoolean();

                            newNode.neighbors.add(clickedNode);
                            clickedNode.neighbors.add(newNode);
                        } else {
                            // Clicked empty space: go to pending mode
                            pendingNewTecton = new Point(e.getX(), e.getY());
                            waitingForTectonClick = true;
                        }
                    } else {
                        if (clickedNode != null && pendingNewTecton != null) {
                            // Place new tecton at pending location, connect to clicked node
                            Node newNode = graph.addNode(pendingNewTecton.x, pendingNewTecton.y);
                            newNode.hasMushroomBody = rand.nextBoolean();
                            newNode.hasMycelium = rand.nextBoolean();

                            newNode.neighbors.add(clickedNode);
                            clickedNode.neighbors.add(newNode);
                        }
                        // If they click empty space again, just update pending location
                        pendingNewTecton = (clickedNode == null) ? new Point(e.getX(), e.getY()) : null;
                        waitingForTectonClick = (clickedNode == null);
                    }
                    // Reset pending connect mode if left clicked (optional, user-friendly)
                    pendingConnectNode = null;
                }

                // Right-click (connect-two-tectons-as-neighbours mode)
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (clickedNode != null) {
                        if (pendingConnectNode == null) {
                            // First right-click: select first node
                            pendingConnectNode = clickedNode;
                        } else {
                            if (pendingConnectNode != clickedNode) {
                                // Connect them both ways if they're not already neighbours
                                if (!pendingConnectNode.neighbors.contains(clickedNode))
                                    pendingConnectNode.neighbors.add(clickedNode);
                                if (!clickedNode.neighbors.contains(pendingConnectNode))
                                    clickedNode.neighbors.add(pendingConnectNode);
                            }
                            pendingConnectNode = null; // Reset after connecting
                        }
                    } else {
                        // Right clicked empty space, cancel connect mode
                        pendingConnectNode = null;
                    }
                }
            }
        });

        // Animation timer
        javax.swing.Timer t = new javax.swing.Timer(33, e -> {
            applyForces();
            repaint();
        });
        t.start();
    }

    // Simple force directed algorithm
    void applyForces() {
        double centerX = getWidth() / 2.0, centerY = getHeight() / 2.0;
        for (Node n : graph.nodes) {
            // Repulsion from every other node
            double fx = 0, fy = 0;
            for (Node m : graph.nodes) {
                if (n == m) continue;
                double dx = n.x - m.x, dy = n.y - m.y, d2 = dx * dx + dy * dy;
                if (d2 == 0) continue;
                double dist = Math.sqrt(d2);
                double repulse = 3000 / d2;
                fx += dx / dist * repulse;
                fy += dy / dist * repulse;
            }
            // Attraction (spring) to neighbors
            for (Node neighbor : n.neighbors) {
                double dx = neighbor.x - n.x, dy = neighbor.y - n.y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                double desired = 50;
                double spring = (dist - desired) * 0.05;
                if (dist > 0) {
                    fx += dx / dist * spring;
                    fy += dy / dist * spring;
                }
            }
            // Weak force toward center to prevent drifting away
            fx += (centerX - n.x) * 0.0005;
            fy += (centerY - n.y) * 0.0005;

            // Update velocity and position
            n.vx = (n.vx + fx) * 0.85;
            n.vy = (n.vy + fy) * 0.85;
        }
        for (Node n : graph.nodes) {
            n.x += n.vx;
            n.y += n.vy;
        }
    }

    // Rendering
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Draw edges
        g2.setStroke(new BasicStroke(2));
        Set<String> drawn = new HashSet<>();
        for (Node n : graph.nodes) {
            for (Node neigh : n.neighbors) {
                String key = System.identityHashCode(n) + "-" + System.identityHashCode(neigh);
                String keyRev = System.identityHashCode(neigh) + "-" + System.identityHashCode(n);
                if (!drawn.contains(key) && !drawn.contains(keyRev)) {
                    g2.setColor(Color.BLACK);
                    g2.drawLine((int) n.x, (int) n.y, (int) neigh.x, (int) neigh.y);
                    drawn.add(key);
                }
            }
        }
        // Draw nodes
        for (Node n : graph.nodes) {
            g2.setColor(n.color);
            g2.fillRect((int)n.x - 18, (int)n.y - 18, 36, 36);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRect((int)n.x - 18, (int)n.y - 18, 36, 36);

            // Draw mushroomBody triangle if present
            if (n.hasMushroomBody) {
                Polygon p = new Polygon();
                int cx = (int)n.x;
                int cy = (int)n.y + 5;
                int halfWidth = 10;
                int heightTri = 12;
                p.addPoint(cx, cy - heightTri);
                p.addPoint(cx - halfWidth, cy + halfWidth);
                p.addPoint(cx + halfWidth, cy + halfWidth);
                g2.setColor(new Color(200, 0, 100));
                g2.fillPolygon(p);
                g2.setColor(Color.BLACK);
                g2.drawPolygon(p);
            }
            // Draw mycelium circle if present
            if (n.hasMycelium) {
                int r = 8;
                g2.setColor(new Color(180, 220, 255));
                g2.fillOval((int)n.x - r, (int)n.y - r - 10, r*2, r*2);
                g2.setColor(Color.BLUE);
                g2.drawOval((int)n.x - r, (int)n.y - r - 10, r*2, r*2);
            }
        }


    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Force-Directed Graph (Tecton Splitter)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 700);
        f.setContentPane(new ForceDirectedGraphSwing());
        f.setVisible(true);
    }
}
