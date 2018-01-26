/* Erik Riis
 * This class extends extends BlockBreakParent and sets up the Game Over scene which is called when the game is lost. 
 * A button is included that returns the player to the Main Menu so that they can play the game again.
 */

package game_evr4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GameOver extends BlockBreakParent{
	
	public GameOver(Stage stage) {
		myStage = stage;
	}
	
	public void setupFinal(int width, int height, Paint background) {
		animation.stop();
		animation.getKeyFrames().clear();
		
		StackPane root = new StackPane();
		myScene = new Scene(root, width, height, background);
		
		Label resultLabel = new Label("Game Over");
		resultLabel.setTranslateY(-50);
		resultLabel.setTranslateX(-10);
		resultLabel.setWrapText(true);
		root.getChildren().add(resultLabel);

		Button resultButton = new Button();
		resultButton.setText("Back to Main Menu");
		resultButton.setTranslateX(0);
		resultButton.setTranslateY(0);
		root.getChildren().add(resultButton);
		
		resultButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setScene(myStage);
			}
		});
		
		myStage.setScene(myScene);
		myStage.show();
	}
	
	protected void setScene(Stage stage){
		MainMenu mainMenu = new MainMenu();
		mainMenu.setupMain(stage);
	}
}
