package agents;

import java.awt.Color;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import java.util.Random;

public class PokemonAgent extends Agent {
    private String pokemonName;
    private int health = 100;
    private int x, y; // Posiciones en el mapa
    private Random rand = new Random();
    private static PokemonGUI gui;
    private PokemonType type;

    public static void setGui(PokemonGUI gui){
        PokemonAgent.gui = gui;
    }

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            pokemonName = (String) args[0];
            type = (PokemonType) args[1];
        }

        x = rand.nextInt(30);
        y = rand.nextInt(30);
        gui.updatePosition(pokemonName, x, y, type.getColor());
        gui.addAgent(this); // Register agent with the GUI

        addBehaviour(new TickerBehaviour(this, 1000) {
            protected void onTick() {
                moveRandomly(gui);
                gui.updatePosition(pokemonName, x, y, type.getColor());

                findAndBattleOpponent();
            }
        });
    }

    private void moveRandomly(PokemonGUI gui) {
        int p_x = x;
        int p_y = y;

        x = (x + rand.nextInt(3) - 1 + 30) % 30; 
        y = (y + rand.nextInt(3) - 1 + 30) % 30;

        if (x == 0 || x > gui.getWidth()) x = p_x;

        if (y == 0 || y > gui.getHeight()) y = p_y;

        System.out.println(pokemonName + " se mueve a la posición (" + x + ", " + y + ")");
        gui.updatePosition(pokemonName, x, y, type.getColor());
    }

    private void findAndBattleOpponent() {
        PokemonAgent opponent = gui.findNearbyAgent(this);
        if (opponent != null) {
            System.out.println("///////////////////////////////");
            System.out.println(pokemonName + " ha encontrado un oponente!");
            System.out.println("///////////////////////////////");
            battle(opponent);
        }
    }

    private void battle(PokemonAgent opponent) {
        int baseDamage = rand.nextInt(20) + 1;
        int damage = baseDamage;

        if (this.type.getStrongAgainst() == opponent.type) {
            damage += 3;
            System.out.println("==============================");
            System.out.println(this.pokemonName + " es fuerte contra " + opponent.pokemonName);
            System.out.println("==============================");
        } else if (this.type.getWeakAgainst() == opponent.type) {
            damage -= 3;
            System.out.println("==============================");
            System.out.println(this.pokemonName + " es debil contra " + opponent.pokemonName);
            System.out.println("==============================");
        }

        opponent.health -= damage;
        System.out.println("***********************************");
        System.out.println(this.pokemonName + " ha causado " + damage + " de daño a " + opponent.pokemonName + ". Salud restante: " + opponent.health);
        System.out.println("***********************************");

        if (opponent.health <= 0) {
            System.out.println("-------------------------------");
            System.out.println(opponent.pokemonName + " ha sido derrotado!");
            opponent.doDelete(); 
            System.out.println("-------------------------------");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public enum PokemonType {
        WATER(Color.BLUE),
        FIRE(Color.RED),
        GRASS(Color.GREEN),
        ELECTRIC(Color.YELLOW),
        BUG(Color.DARK_GRAY),
        POISON(Color.MAGENTA),
        NORM(Color.LIGHT_GRAY);

        private Color color;
        private PokemonType strongAgainst;
        private PokemonType weakAgainst;

        PokemonType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public PokemonType getStrongAgainst() {
            return strongAgainst;
        }

        public PokemonType getWeakAgainst() {
            return weakAgainst;
        }

        static {
            WATER.strongAgainst = FIRE;
            WATER.weakAgainst = GRASS;

            FIRE.strongAgainst = GRASS;
            FIRE.weakAgainst = WATER;

            GRASS.strongAgainst = ELECTRIC;
            GRASS.weakAgainst = FIRE;

            ELECTRIC.strongAgainst = WATER;
            ELECTRIC.weakAgainst = GRASS;

            BUG.strongAgainst = GRASS;
            BUG.weakAgainst = NORM;

            POISON.strongAgainst = NORM;
            POISON.weakAgainst = ELECTRIC;

            NORM.strongAgainst = BUG;
            NORM.weakAgainst = POISON;
        }
    }
}