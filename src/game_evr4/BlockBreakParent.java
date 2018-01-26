/* Erik Riis
 * This class contains all of the constants that each class uses and is meant to provide all of 
 * the functionality that is common to each level. This includes setting up the scene and every element 
 * that is common to each level and a step method which is called in each level to animate the objects 
 * within the scene.
 */

package game_evr4;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class BlockBreakParent {
	protected final String TITLE = "Breakout (Erik Riis)"; 
	protected final int SIZE = 500;
	protected final int FRAMES_PER_SECOND = 70;
	protected final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	protected double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	protected final Paint BACKGROUND = Color.WHITE;    
	protected int BOUNCER_SPEED_X = 0;
	protected int BOUNCER_SPEED_Y = 180;
	protected int BOUNCER_RADIUS = 6;
	protected int BOUNCER_SPEED_X1 = 0;
	protected int BOUNCER_SPEED_Y1 = 0;
	protected int BOUNCER_SPEED_X2 = 0;
	protected int BOUNCER_SPEED_Y2 = 0;
	protected final Paint MOVER_COLOR = Color.BLACK;
	protected final Paint MOVER_COLOR_LEFT = Color.GRAY;
	protected final Paint MOVER_COLOR_RIGHT = Color.GRAY;
	protected final Paint BLOCK_COLOR_1 = Color.LIGHTBLUE;
	protected final Paint BLOCK_COLOR_2 = Color.BLUE;
	protected final Paint BLOCK_COLOR_3 = Color.DARKBLUE;
	protected final Paint UPGRADE_BLOCK_COLOR = Color.BLACK;
	protected int MOVER_WIDTH = 50;
	protected final int MOVER_HEIGHT = 5;
	protected final int MOVER_SPEED = 19;
	protected final int UPGRADE_SPEED = 80;
	protected int LASER_SPEED = 0;

	// some things we need to remember during our game
	protected Scene myScene;
	protected Stage myStage;
	protected KeyFrame myFrame;
	protected static Timeline animation; 
	protected Rectangle myMover;
	protected Rectangle myMoverLeft;
	protected Rectangle myMoverRight;
	protected Rectangle newMover;
	protected Rectangle myBlocks1;
	protected Rectangle myBlocks2;
	protected Rectangle myBlocks3;
	protected Rectangle upgradeBlock1;
	protected Rectangle upgradeBlock2;
	protected Rectangle upgradeBlock3;
	protected Rectangle bombBlock1;
	protected Rectangle bombBlock2;
	protected Rectangle bombBlock3;
	protected Ellipse bomb1;
	protected Ellipse bomb2;
	protected Ellipse bomb3;
	protected Circle myBouncer;
	protected Circle myBouncer1;
	protected Ellipse upgrade1;
	protected Ellipse upgrade2;
	protected Ellipse upgrade3;
	protected Circle laser;
	protected Rectangle cannon;
	ArrayList<Shape> blocks1 = new ArrayList<Shape>();
	ArrayList<Shape> blocks2 = new ArrayList<Shape>();
	ArrayList<Shape> blocks3 = new ArrayList<Shape>();
	protected int count = 0;
	protected Rectangle myBlock;
	protected Label mouseLabel = new Label("Click the screen to play");

	public void setupScene (int width, int height, Paint background) {
		
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
	}

	protected void step (double elapsedTime) {
		//wall collisions for bouncer
		if (myBouncer.getCenterX() >= SIZE - 15) { //bounce off of right boundary
			BOUNCER_SPEED_X = -BOUNCER_SPEED_X;
		} else if (myBouncer.getCenterX() <= 0) { //bounce off of left boundary
			BOUNCER_SPEED_X = -BOUNCER_SPEED_X;
		} else if (myBouncer.getCenterY() <= 0) { //bounce off of top boundary
			BOUNCER_SPEED_Y = -BOUNCER_SPEED_Y;
		} else if (myBouncer.getCenterX() >= SIZE - 15 && myBouncer.getCenterX() <= 0) {
			BOUNCER_SPEED_Y = -BOUNCER_SPEED_Y;
			BOUNCER_SPEED_X = -BOUNCER_SPEED_X;
		} else if (myBouncer.getCenterX() <= 0 && myBouncer.getCenterY() <= 0) {
			BOUNCER_SPEED_Y = -BOUNCER_SPEED_Y;
			BOUNCER_SPEED_X = -BOUNCER_SPEED_X;
		} else if (myBouncer.getCenterY() >= SIZE + 5) {
			BOUNCER_SPEED_X = 0;
			BOUNCER_SPEED_Y = 0;
			myBouncer.setFill(Color.TRANSPARENT); //set transparent if bouncer goes below the screen 
		}

		//wall collisions for second bouncer
		if (myBouncer1.getCenterX() >= SIZE - 15) { 
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterX() <= 0) { 
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterY() <= 0) { 
			BOUNCER_SPEED_Y1 = -BOUNCER_SPEED_Y1;
		} else if (myBouncer1.getCenterX() >= SIZE - 15 && myBouncer1.getCenterX() <= 0) {
			BOUNCER_SPEED_Y1 = -BOUNCER_SPEED_Y1;
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterX() <= 0 && myBouncer1.getCenterY() <= 0) {
			BOUNCER_SPEED_Y1 = -BOUNCER_SPEED_Y1;
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterY() >= SIZE + 5) {
			BOUNCER_SPEED_X1 = 0;
			BOUNCER_SPEED_Y1 = 0;
			myBouncer1.setFill(Color.TRANSPARENT);
		}

		//move ball as time elapses
		myBouncer.setCenterY(myBouncer.getCenterY() + BOUNCER_SPEED_Y * elapsedTime);
		myBouncer.setCenterX(myBouncer.getCenterX() + BOUNCER_SPEED_X * elapsedTime);
		myBouncer1.setCenterY(myBouncer1.getCenterY() + BOUNCER_SPEED_Y1 * elapsedTime);
		myBouncer1.setCenterX(myBouncer1.getCenterX() + BOUNCER_SPEED_X1 * elapsedTime);
		laser.setCenterY(laser.getCenterY() - LASER_SPEED * elapsedTime);

		// collisions with paddle
		Shape intersect = Shape.intersect(myMover, myBouncer);
		Shape intersectLeft = Shape.intersect(myMoverLeft, myBouncer);
		Shape intersectRight = Shape.intersect(myMoverRight, myBouncer);
		Shape intersectNew = Shape.intersect(newMover, myBouncer);
		if (intersect.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y;
		} else if (intersectLeft.getBoundsInLocal().getWidth() != -1 && myMoverLeft.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			BOUNCER_SPEED_X = -150;
		} else if (intersectRight.getBoundsInLocal().getWidth() != -1 && myMoverRight.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			BOUNCER_SPEED_X = 150;
		} else if (intersectNew.getBoundsInLocal().getWidth() != -1 && newMover.getFill() == Color.BLACK) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y;
		}

		//collisions with upgrade1 balls and paddle
		Shape intersect1 = Shape.intersect(myMover, myBouncer1);
		Shape intersectLeft1 = Shape.intersect(myMoverLeft, myBouncer1);
		Shape intersectRight1 = Shape.intersect(myMoverRight, myBouncer1);
		Shape intersectNew1 = Shape.intersect(newMover, myBouncer1);
		if (intersect1.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1;
		}
		if (intersectLeft1.getBoundsInLocal().getWidth() != -1 && myMoverLeft.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
			BOUNCER_SPEED_X1 = -150;
		}
		if (intersectRight1.getBoundsInLocal().getWidth() != -1 && myMoverRight.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
			BOUNCER_SPEED_X1 = 150;
		}
		if (intersectNew1.getBoundsInLocal().getWidth() != -1 && newMover.getFill() == Color.BLACK) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
		}

		//collisions with blocks
		for (Shape i: blocks1) { //if ball hits a light blue block it disappears
			if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		for (Shape i: blocks1) { //if ball hits a light blue block it disappears
			if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		for (Shape i: blocks2) { //if ball hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		for (Shape i: blocks2) { //if ball hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		for (Shape i: blocks3) { //if ball hits a dark blue block it becomes blue (must hit block three times before it disappears)
			if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.BLUE && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.BLUE);
			} else if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		for (Shape i: blocks3) { //if ball hits a dark blue block it becomes blue (must hit block three times before it disappears)
			if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.BLUE && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.BLUE);
			} else if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.TRANSPARENT);
				count++;
			}
		}

		//Multiple bouncers upgrade
		if (Shape.intersect(myBouncer, upgradeBlock1).getBoundsInLocal().getWidth() != -1 && upgradeBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock1.setFill(Color.TRANSPARENT);
			count++;
			upgrade1.setFill(Color.VIOLET);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock1).getBoundsInLocal().getWidth() != -1 && upgradeBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock1.setFill(Color.TRANSPARENT);
			count++;
			upgrade1.setFill(Color.VIOLET);
		}
		if (upgrade1.getFill() == Color.VIOLET) {
			upgrade1.setCenterY(upgrade1.getCenterY() + UPGRADE_SPEED * elapsedTime);
		}
		Shape up1intersect = Shape.intersect(myMover, upgrade1);
		Shape up1intersectnew = Shape.intersect(newMover, upgrade1);
		if (up1intersect.getBoundsInLocal().getWidth() != -1 && upgrade1.getFill() != Color.TRANSPARENT) {
			upgrade1.setFill(Color.TRANSPARENT);
			myBouncer1.setFill(Color.BLACK);
			myBouncer1.setCenterX(myBouncer.getCenterX());
			myBouncer1.setCenterY(myBouncer.getCenterY());
			BOUNCER_SPEED_Y1 = BOUNCER_SPEED_Y;
			BOUNCER_SPEED_X1 = 200;
		}
		if (up1intersectnew.getBoundsInLocal().getWidth() != -1 && upgrade1.getFill() != Color.TRANSPARENT) {
			upgrade1.setFill(Color.TRANSPARENT);
			myBouncer1.setFill(Color.BLACK);
			myBouncer1.setCenterX(myBouncer.getCenterX());
			myBouncer1.setCenterY(myBouncer.getCenterY());
			BOUNCER_SPEED_Y1 = BOUNCER_SPEED_Y;
			BOUNCER_SPEED_X1 = 200;
			if (myBouncer1.getCenterY() <= -SIZE) {
				myBouncer1.setFill(Color.TRANSPARENT);
			}
		}

		//paddle laser upgrade
		if (Shape.intersect(myBouncer, upgradeBlock2).getBoundsInLocal().getWidth() != -1 && upgradeBlock2.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock2.setFill(Color.TRANSPARENT);
			count++;
			upgrade2.setFill(Color.PURPLE);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock2).getBoundsInLocal().getWidth() != -1 && upgradeBlock2.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock2.setFill(Color.TRANSPARENT);
			count++;
			upgrade2.setFill(Color.PURPLE);
		}
		if (upgrade2.getFill() == Color.PURPLE) {
			upgrade2.setCenterY(upgrade2.getCenterY() + UPGRADE_SPEED * elapsedTime);
		}
		Shape up2intersect = Shape.intersect(myMover, upgrade2);
		Shape up2intersectnew = Shape.intersect(newMover, upgrade2);
		if (up2intersect.getBoundsInLocal().getWidth() != -1 && upgrade2.getFill() != Color.TRANSPARENT) {
			upgrade2.setFill(Color.TRANSPARENT);
			cannon.setFill(Color.BLACK);
			laser.setFill(Color.BLACK);
		}
		if (up2intersectnew.getBoundsInLocal().getWidth() != -1 && upgrade2.getFill() != Color.TRANSPARENT) {
			upgrade2.setFill(Color.TRANSPARENT);
			cannon.setFill(Color.BLACK);
			laser.setFill(Color.BLACK);
		}

		//laser collisions
		if (laser.getCenterY() <= 0) { 
			laser.setFill(Color.BLACK);
			LASER_SPEED = 0;
			laser.setCenterX(cannon.getX() + 4);
			laser.setCenterY(cannon.getY() + 3);
		}
		for (Shape i: blocks1) { //if laser hits a light blue block it disappears
			if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) { 
				i.setFill(Color.TRANSPARENT);
				count++;
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
		}
		for (Shape i: blocks2) { //if laser hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.LIGHTBLUE);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			} else if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.TRANSPARENT);
				count++;
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
		}
		for (Shape i: blocks3) { //if laser hits a dark blue block it becomes blue (must hit block three times before it disappears)
			if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.BLUE && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.BLUE);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			} else if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {	
				i.setFill(Color.LIGHTBLUE);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			} else if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.TRANSPARENT);
				count++;
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
			if (Shape.intersect(laser, upgradeBlock1).getBoundsInLocal().getWidth() != -1 && upgradeBlock1.getFill() != Color.TRANSPARENT) {
				upgradeBlock1.setFill(Color.TRANSPARENT);
				count++;
			}
			if (Shape.intersect(laser, upgradeBlock3).getBoundsInLocal().getWidth() != -1 && upgradeBlock3.getFill() != Color.TRANSPARENT) {
				upgradeBlock3.setFill(Color.TRANSPARENT);
				count++;
			}
		}
		if (Shape.intersect(laser, bombBlock1).getBoundsInLocal().getWidth() != -1 && bombBlock1.getFill() != Color.TRANSPARENT) {
			bombBlock1.setFill(Color.TRANSPARENT);
			bomb1.setFill(Color.RED);
			count++;
		}
		if (Shape.intersect(laser, bombBlock2).getBoundsInLocal().getWidth() != -1 && bombBlock1.getFill() != Color.TRANSPARENT) {
			bombBlock2.setFill(Color.TRANSPARENT);
			bomb2.setFill(Color.RED);
			count++;
		}

		//bomb upgrade
		if (Shape.intersect(myBouncer, bombBlock1).getBoundsInLocal().getWidth() != -1 && bombBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			bombBlock1.setFill(Color.TRANSPARENT);
			bomb1.setFill(Color.RED);
			count++;
		}
		if (Shape.intersect(myBouncer1, bombBlock1).getBoundsInLocal().getWidth() != -1 && bombBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
			bombBlock1.setFill(Color.TRANSPARENT);
			bomb1.setFill(Color.RED);
			count++;
		}
		if (bomb1.getFill() == Color.RED) {
			bomb1.setCenterY(bomb1.getCenterY() + UPGRADE_SPEED * elapsedTime);
		}
		if (Shape.intersect(myBouncer, bombBlock2).getBoundsInLocal().getWidth() != -1 && bombBlock2.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			bombBlock2.setFill(Color.TRANSPARENT);
			bomb2.setFill(Color.RED);
			count++;
		}
		if (Shape.intersect(myBouncer1, bombBlock2).getBoundsInLocal().getWidth() != -1 && bombBlock2.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
			bombBlock2.setFill(Color.TRANSPARENT);
			bomb2.setFill(Color.RED);
			count++;
		}
		if (bomb2.getFill() == Color.RED) {
			bomb2.setCenterY(bomb2.getCenterY() + UPGRADE_SPEED * elapsedTime);
		}


		//paddle extension upgrade
		if (Shape.intersect(myBouncer, upgradeBlock3).getBoundsInLocal().getWidth() != -1 && upgradeBlock3.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock3.setFill(Color.TRANSPARENT);
			upgradeBlock3.setStroke(Color.TRANSPARENT);
			upgrade3.setFill(Color.GREEN);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock3).getBoundsInLocal().getWidth() != -1 && upgradeBlock3.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock3.setFill(Color.TRANSPARENT);
			upgrade3.setFill(Color.GREEN);
		}
		if (upgrade3.getFill() == Color.GREEN) {
			upgrade3.setCenterY(upgrade3.getCenterY() + UPGRADE_SPEED * elapsedTime);
		}
		Shape up3intersect = Shape.intersect(myMover, upgrade3);
		Shape up3intersectnew = Shape.intersect(newMover, upgrade3);
		if (up3intersect.getBoundsInLocal().getWidth() != -1 && upgrade3.getFill() != Color.TRANSPARENT) {
			upgrade3.setFill(Color.TRANSPARENT);
			myMover.setFill(Color.TRANSPARENT);
			myMoverLeft.setFill(Color.TRANSPARENT);
			myMoverRight.setFill(Color.TRANSPARENT);
			newMover.setFill(Color.BLACK);
		}
		if (up3intersectnew.getBoundsInLocal().getWidth() != -1 && upgrade3.getFill() != Color.TRANSPARENT) {
			upgrade3.setFill(Color.TRANSPARENT);
			myMover.setFill(Color.TRANSPARENT);
			myMoverLeft.setFill(Color.TRANSPARENT);
			myMoverRight.setFill(Color.TRANSPARENT);
			newMover.setFill(Color.BLACK);
		}

		//new paddle warp
		if (newMover.getX() >= SIZE) {
			newMover.setX(-128);
			cannon.setX(-67);
		} else if(newMover.getX() <= -130) {
			newMover.setX(SIZE - 1);
			cannon.setX(564);
		}
	}
}