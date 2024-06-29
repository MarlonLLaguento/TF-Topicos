
import agents.PokemonAgent;
import agents.PokemonAgent.PokemonType;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import agents.PokemonGUI;
import java.awt.Color;

public class MainContainer {
    public static void main(String[] args) {
        PokemonGUI gui = new PokemonGUI();
        PokemonAgent.setGui(gui);
        // Configurar y arrancar el main container
        jade.core.Runtime rt = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        AgentContainer mainContainer = rt.createMainContainer(profile);

        try {
            // Crear y arrancar los agentes Pokémon
            Object[][] pokemonData = {
                    {"Bulbasaur", PokemonType.GRASS},
                    {"Ivysaur", PokemonType.GRASS},
                    {"Venusaur", PokemonType.GRASS},
                    {"Charmander", PokemonType.FIRE},
                    {"Charmeleon", PokemonType.FIRE},
                    {"Charizard", PokemonType.FIRE},
                    {"Squirtle", PokemonType.WATER},
                    {"Wartortle", PokemonType.WATER},
                    {"Blastoise", PokemonType.WATER},
                    {"Caterpie", PokemonType.BUG},
                    {"Metapod", PokemonType.BUG},
                    {"Butterfree", PokemonType.BUG},
                    {"Weedle", PokemonType.BUG},
                    {"Kakuna", PokemonType.POISON},
                    {"Beedrill", PokemonType.POISON},
                    {"Pidgey", PokemonType.NORM},
                    {"Pidgeotto", PokemonType.NORM},
                    {"Pidgeot", PokemonType.NORM}


            };

            for (Object[] data : pokemonData) {
                String name = (String) data[0];
                PokemonType type = (PokemonType) data[1];
                AgentController pokemonAgent = mainContainer.createNewAgent(name, "agents.PokemonAgent", new Object[] {name, type});
                pokemonAgent.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
