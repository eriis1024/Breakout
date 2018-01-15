package game_evr4;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
//import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BlockBreak extends Application {
	public static final String TITLE = "Breakout (Erik Riis)";
	public static final int SIZE = 500;
	public static final int FRAMES_PER_SECOND = 70;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.WHITE;    
	public static final String BOUNCER_IMAGE = "ball.gif";
	public static int BOUNCER_SPEED_X = 0;
	public static int BOUNCER_SPEED_Y = 200;
	public static int BOUNCER_RADIUS = 4;
	public static int BOUNCER_SPEED_X1 = 0;
	public static int BOUNCER_SPEED_Y1 = 0;
	public static int BOUNCER_SPEED_X2 = 0;
	public static int BOUNCER_SPEED_Y2 = 0;
	public static final Paint MOVER_COLOR = Color.BLACK;
	public static final Paint MOVER_COLOR_LEFT = Color.GRAY;
	public static final Paint MOVER_COLOR_RIGHT = Color.GRAY;
	public static int MOVER_WIDTH = 50;
	public static final int MOVER_HEIGHT = 5;
	public static final int MOVER_SPEED = 19;
	public static final int UPGRADE_SPEED = 80;
	public static int LASER_SPEED = 0;

	// some things we need to remember during our game
	private Scene myScene;
	private Rectangle myMover;
	private Rectangle myMoverLeft;
	private Rectangle myMoverRight;
	private Rectangle newMover;
	private Rectangle myBlocks1;
	private Rectangle myBlocks2;
	private Rectangle myBlocks3;
	private Rectangle upgradeBlock1;
	private Rectangle upgradeBlock2;
	private Rectangle upgradeBlock3;
	private Circle myBouncer;
	private Circle myBouncer1;
	private Ellipse upgrade1;
	private Ellipse upgrade2;
	private Ellipse upgrade3;
	private Circle laser;
	private Rectangle cannon;
	ArrayList<Shape> blocks1 = new ArrayList<Shape>();
	ArrayList<Shape> blocks2 = new ArrayList<Shape>();
	ArrayList<Shape> blocks3 = new ArrayList<Shape>();
	ArrayList<Shape> upblocks = new ArrayList<Shape>();
	

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
		// attach scene to the stage and display it
		myScene = setupGame(SIZE, SIZE, BACKGROUND);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	// Create the game's "scene": what shapes will be in the game and their starting properties
	private Scene setupGame (int width, int height, Paint background) {
		// create one top level collection to organize the things in the scene
		Group root = new Group();
		// create a place to see the shapes
		Scene scene = new Scene(root, width, height, background);
		// make some shapes and set their properties

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
		upblocks.add(upgradeBlock1);
		root.getChildren().add(upgradeBlock1);
		
		upgrade1 = new Ellipse(255, 130, 12, 6);
		upgrade1.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade1);
		
		upgradeBlock2 = new Rectangle(100, 130, 40, 18);
		upgradeBlock2.setFill(Color.BLACK);
		upblocks.add(upgradeBlock2);
		root.getChildren().add(upgradeBlock2);
		
		upgrade2 = new Ellipse(104, 130, 12, 6);
		upgrade2.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade2);
		
		upgradeBlock3 = new Rectangle(358, 130, 40, 18);
		upgradeBlock3.setFill(Color.BLACK);
		upblocks.add(upgradeBlock3);
		root.getChildren().add(upgradeBlock3);
		
		upgrade3 = new Ellipse(374, 130, 12, 6);
		upgrade3.setFill(Color.TRANSPARENT);
		root.getChildren().add(upgrade3);
		
		
		// respond to input
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return scene;
	}

	// Change properties of shapes to animate them 
	private void step (double elapsedTime) {
		// update attributes
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
		} 
		
		
		//wall collisions for multiple ball upgrades
		if (myBouncer1.getCenterX() >= SIZE - 15) { 
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterX() <= 0) { 
			BOUNCER_SPEED_X1 = -BOUNCER_SPEED_X1;
		} else if (myBouncer1.getCenterY() <= 0) { 
			BOUNCER_SPEED_Y1 = -BOUNCER_SPEED_Y1;
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
		} else if (intersectLeft.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			BOUNCER_SPEED_X = -150;
		} else if (intersectRight.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			BOUNCER_SPEED_X = 150;
		} else if (intersectNew.getBoundsInLocal().getWidth() != -1 && newMover.getFill() == Color.BLACK) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y;
		}
		
		//collisions with upgrade1 balls and paddle
		Shape intersect1 = Shape.intersect(myMover, myBouncer1);
		Shape intersectLeft1 = Shape.intersect(myMoverLeft, myBouncer1);
		Shape intersectRight1 = Shape.intersect(myMoverRight, myBouncer1);
		Shape intersectNew1 = Shape.intersect(newMover, myBouncer);
		if (intersect1.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1;
		}
		if (intersectLeft1.getBoundsInLocal().getWidth() != -1) {
			BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
			BOUNCER_SPEED_X1 = -150;
		}
		if (intersectRight1.getBoundsInLocal().getWidth() != -1) {
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
			}
		}
		for (Shape i: blocks1) { //if ball hits a light blue block it disappears
			if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.TRANSPARENT);
			}
		}
		for (Shape i: blocks2) { //if ball hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
				i.setFill(Color.TRANSPARENT);
			}
		}
		for (Shape i: blocks2) { //if ball hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.LIGHTBLUE);
			} else if (Shape.intersect(myBouncer1, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				BOUNCER_SPEED_Y1 = - BOUNCER_SPEED_Y1; 
				i.setFill(Color.TRANSPARENT);
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
			}
		}
		
		//laser collisions
		if (laser.getCenterY() <= 0) { 
			laser.setFill(Color.BLACK);
			LASER_SPEED = 0;
			laser.setCenterX(cannon.getX() + 4);
			laser.setCenterY(cannon.getY() + 3);
		}
		for (Shape i: blocks1) { //if ball hits a light blue block it disappears
			if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) { 
				i.setFill(Color.TRANSPARENT);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
		}
		for (Shape i: blocks2) { //if ball hits a blue block it becomes light blue (must hit block twice before it disappears)
			if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.LIGHTBLUE && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.LIGHTBLUE);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			} else if (Shape.intersect(laser, i).getBoundsInLocal().getWidth() != -1 && i.getFill() != Color.TRANSPARENT) {
				i.setFill(Color.TRANSPARENT);
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
		}
		for (Shape i: blocks3) { //if ball hits a dark blue block it becomes blue (must hit block three times before it disappears)
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
				laser.setFill(Color.BLACK);
				LASER_SPEED = 0;
				laser.setCenterX(cannon.getX() + 4);
				laser.setCenterY(cannon.getY() + 3);
			}
		}
		
		//Multiple bouncers upgrade
		if (Shape.intersect(myBouncer, upgradeBlock1).getBoundsInLocal().getWidth() != -1 && upgradeBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock1.setFill(Color.TRANSPARENT);
			upgrade1.setFill(Color.RED);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock1).getBoundsInLocal().getWidth() != -1 && upgradeBlock1.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock1.setFill(Color.TRANSPARENT);
			upgrade1.setFill(Color.RED);
		}
		if (upgrade1.getFill() == Color.RED) {
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
			upgrade2.setFill(Color.PURPLE);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock2).getBoundsInLocal().getWidth() != -1 && upgradeBlock2.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock2.setFill(Color.TRANSPARENT);
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
		
		//paddle extension upgrade
		if (Shape.intersect(myBouncer, upgradeBlock3).getBoundsInLocal().getWidth() != -1 || Shape.intersect(myBouncer1, upgradeBlock3).getBoundsInLocal().getWidth() != -1 && upgradeBlock3.getFill() != Color.TRANSPARENT) {
			BOUNCER_SPEED_Y = - BOUNCER_SPEED_Y; 
			upgradeBlock3.setFill(Color.TRANSPARENT);
			upgradeBlock3.setStroke(Color.TRANSPARENT);
			upgrade3.setFill(Color.GREEN);
		}
		if (Shape.intersect(myBouncer1, upgradeBlock3).getBoundsInLocal().getWidth() != -1 || Shape.intersect(myBouncer1, upgradeBlock3).getBoundsInLocal().getWidth() != -1 && upgradeBlock3.getFill() != Color.TRANSPARENT) {
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
		
	
		//with images can only check bounding box
		//        if (myGrower.getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
		//            myGrower.setFill(HIGHLIGHT);
		//        }
		//        else {
		//            myGrower.setFill(GROWER_COLOR);
		//        }
	}

	// What to do each time a key is pressed
	private void handleKeyInput (KeyCode code) {
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
		}
	}

	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}
