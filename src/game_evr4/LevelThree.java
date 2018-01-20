package game_evr4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelThree extends BlockBreakParent {

	public LevelThree (Stage stage) {
		myStage = stage;
	}

	public void setupGame (int width, int height, Paint background) {
		// create one top level collection to organize the things in the scene
		Group root = new Group();
		// create a place to see the shapes
		myScene = new Scene(root, width, height, background);
		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		myScene.setOnMouseClicked(e -> handleMouseInput(e.getButton()));

		//create Bouncer
		myBouncer = new Circle(width / 2, height / 2 + 230, BOUNCER_RADIUS);
		myBouncer.setFill(MOVER_COLOR);

		//create Bouncers for upgrade 1
		myBouncer1 = new Circle(width / 2, height / 2 + 230, BOUNCER_RADIUS);
		myBouncer1.setFill(Color.TRANSPARENT);

		//create paddle
		myMover = new Rectangle(width / 2 - 25, height / 2 + 240, MOVER_WIDTH, MOVER_HEIGHT);
		myMover.setFill(MOVER_COLOR);
		myMoverLeft = new Rectangle (width / 2 - 35, height / 2 + 240, 15, MOVER_HEIGHT);
		myMoverLeft.setFill(MOVER_COLOR_LEFT);
		myMoverRight = new Rectangle (width / 2 + 25, height / 2 + 240, 15, MOVER_HEIGHT);
		myMoverRight.setFill(MOVER_COLOR_RIGHT);

		//create laser and canon
		cannon = new Rectangle(width / 2, height / 2 + 225, 8, 15);
		cannon.setFill(Color.TRANSPARENT);
		laser = new Circle(width / 2 + 4, height / 2 + 228, 3);
		laser.setFill(Color.TRANSPARENT);

		//create new paddle
		newMover = new Rectangle(myMover.getX() - 40, myMover.getY(), MOVER_WIDTH + 80, MOVER_HEIGHT);
		newMover.setFill(Color.TRANSPARENT);

		// order added to the group is the order in which they are drawn
		root.getChildren().add(myBouncer);
		root.getChildren().add(myMover);
		root.getChildren().add(myMoverLeft);
		root.getChildren().add(myMoverRight);
		root.getChildren().add(myBouncer1);
		root.getChildren().add(cannon);
		root.getChildren().add(laser);
		root.getChildren().add(newMover);

		//Set up different color blocks on the screen
		blocks1.clear();
		blocks2.clear();
		blocks3.clear();

		for (int i = 3; i < SIZE - 5; i = i + 45) {
			myBlocks3 = new Rectangle(i, 80, 43, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}
		for (int i = 3; i < SIZE - 5; i = i + 45) {
			myBlocks3 = new Rectangle(i, 100, 43, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}
		for (int i = 3; i < SIZE - 5; i = i + 45) {
			myBlocks3 = new Rectangle(i, 120, 43, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}
		for (int i = 3; i < SIZE - 5; i = i + 45) {
			myBlocks3 = new Rectangle(i, 140, 43, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}
		for (int i = 3; i < SIZE - 5; i = i + 45) {
			myBlocks3 = new Rectangle(i, 160, 43, 18);
			myBlocks3.setFill(Color.DARKBLUE);
			blocks3.add(myBlocks3);
			root.getChildren().add(myBlocks3);
		}

		upgradeBlock1 = new Rectangle(3, 180, 43, 18);
		upgradeBlock1.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock1);
		upgrade1 = new Ellipse(12, 180, 12, 6);
		upgrade1.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade1);

		upgradeBlock2 = new Rectangle(228, 180, 43, 18);
		upgradeBlock2.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock2);
		upgrade2 = new Ellipse(232, 180, 12, 6);
		upgrade2.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade2);

		upgradeBlock3 = new Rectangle(SIZE - 47, 180, 43, 18);
		upgradeBlock3.setFill(Color.BLACK);
		root.getChildren().add(upgradeBlock3);
		upgrade3 = new Ellipse(SIZE - 47, 130, 12, 6);
		upgrade3.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade3);

		bombBlock1 = new Rectangle(93, 180, 43, 18);
		bombBlock1.setFill(Color.BLACK);
		root.getChildren().add(bombBlock1);
		bomb1 = new Ellipse(97, 180, 12, 6);
		bomb1.setFill(Color.TRANSPARENT);
		root.getChildren().add(bomb1);

		bombBlock2 = new Rectangle(363, 180, 43, 18);
		bombBlock2.setFill(Color.BLACK);
		root.getChildren().add(bombBlock2);
		bomb2 = new Ellipse(354, 180, 12, 6);
		bomb2.setFill(Color.TRANSPARENT);
		root.getChildren().add(bomb2);

		//label "click to play"
		mouseLabel.setTranslateY(300);
		mouseLabel.setTranslateX(180);
		mouseLabel.setWrapText(true);
		root.getChildren().add(mouseLabel);

		// respond to input
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		animation.stop();
		animation.getKeyFrames().clear();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(myFrame);
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
		if (count >= 59) {
			transitionToResult();
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

	private void transitionToResult() {
		Result result = new Result(myStage);
		result.setupResult(SIZE, SIZE, BACKGROUND);
	}

	private void transitionToGameOver() {
		GameOver GameOver = new GameOver(myStage);
		GameOver.setupFinal(SIZE, SIZE, BACKGROUND);
	}

	// What to do each time a key is pressed
	protected static void handleKeyInput (KeyCode code) {
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
		} else if (code == KeyCode.SPACE) { //cheat code - slow down speed
			SECOND_DELAY = 0.6 / FRAMES_PER_SECOND;
		} else if (code == KeyCode.COMMAND) { //cheat code - restore speed
			SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
		}
	}
}


