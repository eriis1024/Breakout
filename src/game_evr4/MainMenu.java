/* Erik Riis
 * This class extends extends BlockBreakParent and sets up the Main Menu scene which includes directions for playing the game
 * as well as a button that when clicked begins the first level
 */

package game_evr4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenu extends BlockBreakParent {

	public void setupMain(Stage stage){
		
		StackPane root = new StackPane();
		myScene = new Scene(root, SIZE, SIZE, Color.WHITE);
		
		Label playLabel = new Label("Click below to play BlockBreak! The goal of the game is to clear each level "
				+ "of all the different color blocks that appear. Blue blocks may need to be hit more than once, and "
				+ "black blocks drop upgrades that can help clear the levels. Pink upgrades create an extra ball, purple "
				+ "upgrades allow the player to shoot lasers from the paddle to hit blocks, and green upgrades widen "
				+ "the paddle and allows it to warp from one side of the screen to the other. But be careful, sometimes "
				+ "red 'bombs' are dropped that will automatically end the game. (Cheat codes: 'ALT' - skip to the next level, "
				+ "'SPACE' - slows down time, 'COMMAND' - restores time back to normal");
		playLabel.setTranslateY(-100);
		playLabel.setTranslateX(5);
		playLabel.setPrefWidth(450);
		playLabel.setWrapText(true);
		root.getChildren().add(playLabel);
		
		Label dirLabel = new Label("Keyboard commads: Use the 'left/right' arrow keys to move the paddle and the 'up' arrow key to shoot lasers");
		dirLabel.setTranslateY(100);
		dirLabel.setTranslateX(5);
		dirLabel.setPrefWidth(450);
		dirLabel.setWrapText(true);
		root.getChildren().add(dirLabel);
		
		Button playButton = new Button();
		playButton.setText("Play");
		playButton.setTranslateX(0);
		playButton.setTranslateY(25);
		root.getChildren().add(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setScene(stage);
			}
		});

		stage.setScene(myScene);
		stage.show();
	}
	
	protected void setScene(Stage stage) {
		LevelOne LevelOne = new LevelOne(stage);
		LevelOne.setupGame(SIZE, SIZE, BACKGROUND);
	}
}

	

