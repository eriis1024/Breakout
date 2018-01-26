/* Erik Riis
 * This class extends application and launches the the Main Menu which then transitions to the first 
 * level of the game
 */

package game_evr4;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start (Stage stage) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.setupMain(stage);
	}

	public static void main (String[] args) {
		launch(args);
	}
}
