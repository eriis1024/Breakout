package game_evr4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelOne extends BlockBreakParent {

	public LevelOne(Stage stage) {
		myStage = stage;
	}

	public void setupGame (int width, int height, Paint background) {
		// create one top level collection to organize the things in the scene
		Group root = new Group();
		// create a place to see the shapes
		myScene = new Scene(root, width, height, background);
		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> move(SECOND_DELAY));
		myScene.setOnMouseClicked(e -> handleMouseInput(e.getButton()));
		
		//add components that are common of every level
		setupScene(SIZE, SIZE, BACKGROUND);
		root.getChildren().add(myBouncer);
		root.getChildren().add(myMover);
		root.getChildren().add(myMoverLeft);
		root.getChildren().add(myMoverRight);
		root.getChildren().add(myBouncer1);
		root.getChildren().add(cannon);
		root.getChildren().add(laser);
		root.getChildren().add(newMover);

		//Set up different color blocks on the screen
		for (int i = 100; i < SIZE - 100; i = i + 43) {
			myBlocks1 = new Rectangle(i, 190, 40, 18);
			myBlocks1.setFill(Color.LIGHTBLUE);
			blocks1.add(myBlocks1);
			root.getChildren().add(myBlocks1);
		}
		for (int i = 100; i < SIZE - 100; i = i + 43) {
			myBlocks2 = new Rectangle(i, 170, 40, 18);
			myBlocks2.setFill(Color.BLUE);
			blocks2.add(myBlocks2);
			root.getChildren().add(myBlocks2);
		}
		for (int i = 100; i < SIZE - 100; i = i + 43) {
			myBlocks3 = new Rectangle(i, 150, 40, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}

		upgradeBlock1 = new Rectangle(229, 130, 40, 18);
		upgradeBlock1.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock1);
		upgrade1 = new Ellipse(255, 130, 12, 6);
		upgrade1.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade1);

		upgradeBlock2 = new Rectangle(100, 130, 40, 18);
		upgradeBlock2.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock2);
		upgrade2 = new Ellipse(104, 130, 12, 6);
		upgrade2.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade2);

		upgradeBlock3 = new Rectangle(358, 130, 40, 18);
		upgradeBlock3.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock3);
		upgrade3 = new Ellipse(374, 130, 12, 6);
		upgrade3.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade3);

		bombBlock1 = new Rectangle(165, 130, 40, 18);
		bombBlock1.setFill(Color.BLACK);
		root.getChildren().add(bombBlock1);
		bomb1 = new Ellipse(169, 130, 12, 6);
		bomb1.setFill(Color.TRANSPARENT);
		root.getChildren().add(bomb1);

		bombBlock2 = new Rectangle(292, 130, 40, 18);
		bombBlock2.setFill(Color.BLACK);
		root.getChildren().add(bombBlock2);
		bomb2 = new Ellipse(304, 130, 12, 6);
		bomb2.setFill(Color.TRANSPARENT);
		root.getChildren().add(bomb2);

		//label "click to play"
		mouseLabel.setTranslateY(300);
		mouseLabel.setTranslateX(180);
		mouseLabel.setWrapText(true);
		root.getChildren().add(mouseLabel);

		animation = new Timeline();
		animation.getKeyFrames().clear();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(myFrame);
		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> move(SECOND_DELAY));
		// respond to input
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myStage.setScene(myScene);
		myStage.show();
	}

	private void handleMouseInput(MouseButton button) {
		animation.play();
		mouseLabel.setTextFill(Color.TRANSPARENT);
	}

	// Change properties of shapes to animate them 
	protected void move (double elapsedTime) {
		double t = elapsedTime;
		step(t);
		if (count >= 25) {
			transitionToNextLevel();
		}
		if (myBouncer.getFill() == Color.TRANSPARENT && myBouncer1.getFill() == Color.TRANSPARENT) {
			transitionToGameOver();
		} else if (Shape.intersect(myMover, bomb1).getBoundsInLocal().getWidth() != -1 && bomb1.getFill() != Color.TRANSPARENT) {
			transitionToGameOver();
		} else if (Shape.intersect(newMover, bomb1).getBoundsInLocal().getWidth() != -1 && bomb1.getFill() != Color.TRANSPARENT) {
			transitionToGameOver();
		} else if (Shape.intersect(myMover, bomb2).getBoundsInLocal().getWidth() != -1 && bomb2.getFill() != Color.TRANSPARENT) {
			transitionToGameOver();
		} else if (Shape.intersect(newMover, bomb2).getBoundsInLocal().getWidth() != -1 && bomb2.getFill() != Color.TRANSPARENT) {
			transitionToGameOver();
		}
	}

	private void transitionToNextLevel() {
		count = 0;
		BOUNCER_SPEED_X = 0;
		BOUNCER_SPEED_Y = 180;
		LevelTwo levelTwo = new LevelTwo(myStage);
		levelTwo.setupGame(SIZE, SIZE, BACKGROUND);
	}

	private void transitionToGameOver() {
		GameOver GameOver = new GameOver(myStage);
		GameOver.setupFinal(SIZE, SIZE, BACKGROUND);
	}


	// What to do each time a key is pressed
	protected void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			myMover.setX(myMover.getX() + MOVER_SPEED);
			myMoverLeft.setX(myMoverLeft.getX() + MOVER_SPEED);
			myMoverRight.setX(myMoverRight.getX() + MOVER_SPEED);
			newMover.setX(newMover.getX() + MOVER_SPEED);
			cannon.setX(cannon.getX() + MOVER_SPEED);
			if (laser.getFill() == Color.BLACK || laser.getFill() == Color.TRANSPARENT) {
				laser.setCenterX(laser.getCenterX() + MOVER_SPEED);
			}
		} else if (code == KeyCode.LEFT) {
			myMover.setX(myMover.getX() - MOVER_SPEED);
			myMoverLeft.setX(myMoverLeft.getX() - MOVER_SPEED);
			myMoverRight.setX(myMoverRight.getX() - MOVER_SPEED);
			newMover.setX(newMover.getX() - MOVER_SPEED);
			cannon.setX(cannon.getX() - MOVER_SPEED);
			if (laser.getFill() == Color.BLACK || laser.getFill() == Color.TRANSPARENT) {
				laser.setCenterX(laser.getCenterX() - MOVER_SPEED);
			}
		} else if (code == KeyCode.UP) {
			if (laser.getFill() == Color.BLACK) {
				laser.setFill(Color.RED);
			}
			if (laser.getFill() == Color.RED) {
				LASER_SPEED = 300;	
			}
		} else if (code == KeyCode.ALT) { //cheat code - skip to next level
			transitionToNextLevel();
		} else if (code == KeyCode.SPACE) { //cheat code - slow down speed 
			SECOND_DELAY = 0.6 / FRAMES_PER_SECOND;
		} else if (code == KeyCode.COMMAND) { //cheat code - restore speed
			SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
		}
	}
}
