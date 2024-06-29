package agents;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Point;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class PokemonGUI extends JFrame {
    private final int SIZE = 30; 
    private final int SCALE = 20; 
    private ConcurrentHashMap<String, ColoredPoint> positions = new ConcurrentHashMap<>();
    private List<PokemonAgent> agents = new ArrayList<>();

    public PokemonGUI() {
        setTitle("Pokemon Arena");
        setSize(SIZE * SCALE + 50, SIZE * SCALE + 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        positions = new ConcurrentHashMap<>();
        setVisible(true);
    }

    public void updatePosition(String pokemonName, int x, int y, Color color) {
        positions.put(pokemonName, new ColoredPoint(x, y, color));
        repaint();
    }

    public void removePokemon(String pokemonName) {
        positions.remove(pokemonName);
        repaint();
    }
    public void addAgent(PokemonAgent agent) {
        agents.add(agent);
    }

    public PokemonAgent findNearbyAgent(PokemonAgent currentAgent) {
        for (PokemonAgent agent : agents) {
            if (!agent.equals(currentAgent) && Math.abs(agent.getX() - currentAgent.getX()) <= 1 && Math.abs(agent.getY() - currentAgent.getY()) <= 1) {
                return agent;
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Map.Entry<String, ColoredPoint> entry : positions.entrySet()) {
            ColoredPoint point = entry.getValue();
            g.setColor(point.color);
            g.fillOval(point.x * SCALE, point.y * SCALE, SCALE, SCALE);
            g.setColor(Color.BLACK);
            g.drawString(entry.getKey(), point.x * SCALE, point.y * SCALE);
        }
    }
    public static class ColoredPoint extends Point {
        public Color color;

        public ColoredPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }
    }
}
