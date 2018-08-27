package com.awasicek;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameScene extends Scene {
	static final double WIDTH = 1200;
	static final double HEIGHT = 800;
	Pane root;
	boolean hasExited = false;
	Player player = new Player();
	double widthOffset = player.getImageWidthOffset();
	double heightOffset = player.getImageHeightOffset();
	GameObjectManager gameObjectManager;
	Timeline tweetSpawner;
	Timeline checkGameState;
	Timeline mainTimer;

	public GameScene(Pane root) {
		super(root, WIDTH, HEIGHT);
		this.root = root;
		
		// white background
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// Game Object Manager handles most of the logic for the tweets, notes, and player (TODO manage more player logic in this manager)
		// including collision detection, "drawing", and death of game objects
		gameObjectManager = new GameObjectManager(this, player);
		
		// timer that handles spawning of subsequent tweets
		tweetSpawner = new Timeline(new KeyFrame(Duration.millis(3000), e -> gameObjectManager.manageTweets()));
		tweetSpawner.setCycleCount(Animation.INDEFINITE);
		tweetSpawner.play();		
		
		// timer that checks victory/fail conditions and winds down timers when detected
		checkGameState = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			int tweetCount = gameObjectManager.getTweetCount();
			boolean isPlayerAlive = player.getIsAlive();
			if((tweetCount == 0 || !isPlayerAlive ) && !hasExited) {
				if(isPlayerAlive)
				{
					TweetFighter.victoryState = true;
				}
				else
				{
					TweetFighter.victoryState = false;
				}
				hasExited = true;
				TweetFighter.window.setScene(new FinalScene(new Pane()));
				checkGameState.stop();
				mainTimer.stop();
				tweetSpawner.stop();
			}
		}));
		
		// timer that handles player boundary checking and also calls the update method of the Game Object Manager so as to
		// draw all necessary objects once every sixtieth of a second
		mainTimer = new Timeline(new KeyFrame(Duration.millis(1000 / 60), e -> {
			double x = player.getX();
			double y = player.getY();
			if (x > WIDTH) 
			{
				player.setX(WIDTH - widthOffset);
			} 
			else if (x < 0) 
			{
				player.setX(0 - widthOffset);
			}
			if (y > HEIGHT) 
			{
				player.setY(HEIGHT - heightOffset);
			} 
			else if (y < 0)
			{
				player.setY(0 - heightOffset);
			}
			player.update();
			gameObjectManager.update();
		}));
		
		// keep the timers going indefinitely (until the checkGameState timer shuts them down)
		mainTimer.setCycleCount(Animation.INDEFINITE);
		mainTimer.play();
		checkGameState.setCycleCount(Animation.INDEFINITE);
		checkGameState.play();
		
		gameObjectManager.manageTweets(); // launch the first tweet
		
		// set up the key event handler on the root to handle player control of the player object
		root.requestFocus();
		root.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent e)
			{
				switch(e.getCode())
				{
					case UP:
						player.goUp();
						System.out.println("pressed up, y = " + String.valueOf(player.getY()));
						System.out.println("graphics y = " + player.getPlayerGfx().getY());
						break;
					
					case DOWN:
						player.goDown();
						System.out.println("pressed down, y = " + String.valueOf(player.getY()));
						System.out.println("graphics y = " + player.getPlayerGfx().getY());
						break;
					
					case RIGHT:
						player.goRight();
						System.out.println("pressed right, x = " + String.valueOf(player.getX()));
						System.out.println("graphics x = " + player.getPlayerGfx().getX());
						break;
						
					case LEFT:
						player.goLeft();
						System.out.println("pressed left, x = " + String.valueOf(player.getX()));
						System.out.println("graphics x = " + player.getPlayerGfx().getX());
						break;
						
					case SPACE:
						gameObjectManager.addNote(new NoteOfJustice(player.getX(), player.getY()));
						System.out.println("pressed space");
						break;
				
					default:
						break;
				}
			}
		});
		
		// add the player
		root.getChildren().add(player.getPlayerGfx());
		
	}

	public Pane getPane()
	{
		return root;
	}

}
