package window;

import gamelogic.CityValidator;
import gamelogic.MainGame;
import gamelogic.Player;

import javax.swing.*;


public class GameWindow extends JFrame {
    private final MainGame game;
    //private final ComponentsGameWindow components;
    //private WelcomeWindow welcomeWindow;

    public GameWindow(String playerName) {

        String[] cities = CityValidator.loadCities("src/main/resources/cities.txt");
        game = new MainGame(new Player(playerName), cities);
        //components = new ComponentsGameWindow();
        initComponents();
    }

    private void initComponents() {
        ComponentsGameWindow.createFrame();

        ComponentsGameWindow.getButton().addActionListener(e -> {
            String userInput = ComponentsGameWindow.getUserInput();
            if (userInput.equalsIgnoreCase("z") || userInput.equalsIgnoreCase("здаюсь")){
                ComponentsGameWindow.closeGameWindow();
                RecordWindow recordWindow = new RecordWindow();
                return;
            }
            boolean isCityAvailable = game.isCityAvailable(userInput);

            if (isCityAvailable) {
                String aiCity = game.getRandomCity(userInput.charAt(userInput.length() - 1));
                ComponentsGameWindow.setComputerResponse(aiCity.substring(0, 1).toUpperCase() + aiCity.substring(1));
                game.addCity(userInput, aiCity);
            } else {
                ComponentsGameWindow.showMessage("Такого міста нема","Міста нема");
            }
        });
    }
}