package game_evr4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Result extends BlockBreakParent{
	
	public Result(Stage stage) {
		myStage = stage;
	}
	
	public void setupResult(int width, int height, Paint background) {
		
		animation.stop();
		animation.getKeyFrames().clear();
		
		StackPane root = new StackPane();
		myScene = new Scene(root, width, height, background);
		
		Label winLabel = new Label("You won!");
		winLabel.setTranslateY(-50);
		winLabel.setTranslateX(-10);
		winLabel.setWrapText(true);
		root.getChildren().add(winLabel);

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
