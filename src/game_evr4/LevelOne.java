/* This class is part of my masterpiece.
 * 
 * Erik Riis
 * 
 * The class LevelOne sets up the first level of my game and depends on the BlockBreakParent class. The class
 * sets the scene by calling setupScene from the parent class and adding specific block configurations. There is also
 * a method called move that calls step from the parent class in order to animate the objects along with conditions to 
 * transition to other levels and scenes. Finally, this class includes keycode commands specific to this level.
 * 
 * I believe this class is well designed because it extends the BlockBreakParent class from which this class gets the 
 * a lot of information about what objects to include in the scene (common to all levels), and how these objects should be animated
 * from the step method. Have that code located in another class rather than in each level class is important to keep the design organized
 * and avoid duplicate code. Furthermore, the helper methods that I included while refactoring helped to decrease the length of what had been
 * only a couple methods in this class, which also supports better design and a more understandable code structure. I added each upgrade block
 * individually because each upgrade has a different functionality and are coded in the step method (called by move) to have different behaviors.
 */

package game_evr4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	
	Group root = new Group();
	
	public LevelOne(Stage stage) {
		myStage = stage;
	}

	/* The setupGame method initializes a scene and keyframe for level one and calls setupScene from BlockBreakParent.
	 * It adds every object to the level and configures the different color blocks and upgrade blocks in a specific configuration.
	 * The method also sets a click to play label and sets the animation timeline.
	 */
	public void setupGame (int width, int height, Paint background) {
		myScene = new Scene(root, width, height, background);
		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> move(SECOND_DELAY));
		myScene.setOnMouseClicked(e -> handleMouseInput(e.getButton()));
		
		setupScene(SIZE, SIZE, BACKGROUND);
		
		root.getChildren().add(myBouncer);
		root.getChildren().add(myMover);
		root.getChildren().add(myMoverLeft);
		root.getChildren().add(myMoverRight);
		root.getChildren().add(myBouncer1);
		root.getChildren().add(cannon); 
		root.getChildren().add(laser); 
		root.getChildren().add(newMover);

		for (int i = 100; i < SIZE - 100; i = i + 43) {
			setBlock(i, 190, BLOCK_COLOR_1);
			setBlock(i, 170, BLOCK_COLOR_2);
			setBlock(i, 150, BLOCK_COLOR_3);
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

		setLabel(mouseLabel);
		root.getChildren().add(mouseLabel);

		animation = new Timeline();
		animation.getKeyFrames().clear();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(myFrame);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myStage.setScene(myScene);
		myStage.show();
	}
	
	/* This is a helper method that gets called in setupGame to configure the different color blocks. The method takes input
	 * x and y coordinates and a color and sets blocks in the scene based off of these parameters
	 */
	private void setBlock(int x, int y, Paint color) {
		myBlock = new Rectangle(x, y, 40, 18);
		myBlock.setFill(color);
		root.getChildren().add(myBlock);
		if (color == BLOCK_COLOR_1) {
			blocks1.add(myBlock);
		} else if (color == BLOCK_COLOR_2) {
			blocks2.add(myBlock);
		} else if (color == BLOCK_COLOR_3) {
			blocks3.add(myBlock);
		}
	}
	
	/* This is a helper method that gets called in setupGame to set the "click to play label." The method 
	 * sets the x and y coordinates of any input label and creates a wrap text effect.
	 */
	private void setLabel(Label label) {
		label.setTranslateY(300);
		label.setTranslateX(180);
		label.setWrapText(true);
	}
	
	/* This method creates the functionality of the "click to play" label. It is called in setupGame so that if the 
	 * scene is clicked by the mouse, then the animation begins and the level "starts." 
	 */
	private void handleMouseInput(MouseButton button) {
		animation.play();
		mouseLabel.setTextFill(Color.TRANSPARENT);
	}

	/* The move method calls the step method from BlockBreakParent in order to add the properties of how each object 
	 * should behave in the level when animated. There are also various conditions set forth that if met cause the level 
	 * to end and move on to the next level or to the Game Over scene.
	 */
	protected void move (double elapsedTime) {
		int numBlocks = 25;
		double t = elapsedTime;
		step(t);
		if (count >= numBlocks) {
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

	/* This method is called in the move method if all of the blocks in the level have been hit. It causes the scene to transition
	 * to Level Two.
	 */
	private void transitionToNextLevel() {
		count = 0;
		BOUNCER_SPEED_X = 0;
		BOUNCER_SPEED_Y = 180;
		LevelTwo levelTwo = new LevelTwo(myStage);
		levelTwo.setupGame(SIZE, SIZE, BACKGROUND);
	}

	/* This method is called in the move method if there are no more balls in play or the paddle gets hit by a "bomb."
	 *  It causes the scene to transition to the Game Over menu.
	 */
	private void transitionToGameOver() {
		GameOver GameOver = new GameOver(myStage);
		GameOver.setupFinal(SIZE, SIZE, BACKGROUND);
	}

	//This method sets each of the key commands including the cheat keys
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